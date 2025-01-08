package JuanJose.ForoHub.service.User;

import JuanJose.ForoHub.dto.auth.AuthLoginRequest;
import JuanJose.ForoHub.dto.auth.AuthResponse;
import JuanJose.ForoHub.entities.ForumUser;
import JuanJose.ForoHub.repository.UserRepository;
import JuanJose.ForoHub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ForumUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        String role = "ROLE_" + user.getProfile().getName();

        authorityList.add(new SimpleGrantedAuthority(role));
        user.getProfile().getPermissions().forEach(permission ->
                authorityList.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(user.getEmail(), user.getPassword(), authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest userRequest) {
        String username = userRequest.email();
        String password = userRequest.password();
        System.out.println(userRequest.email()+ " - " + userRequest.password());
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

      return new AuthResponse(
                username, accessToken, "User logged successfully", true);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null){
            throw  new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(), userDetails.getAuthorities());
    }
}
