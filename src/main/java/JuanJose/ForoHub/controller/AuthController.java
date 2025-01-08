package JuanJose.ForoHub.controller;


import JuanJose.ForoHub.dto.User.CreateUserDTO;
import JuanJose.ForoHub.dto.User.DetailsResponseUserDTO;
import JuanJose.ForoHub.dto.auth.AuthLoginRequest;
import JuanJose.ForoHub.dto.auth.AuthResponse;
import JuanJose.ForoHub.service.User.UserDetailsServiceImpl;
import JuanJose.ForoHub.service.User.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;

    public AuthController (UserDetailsServiceImpl userDetailsService, UserService userService){
        this.userDetailsService=userDetailsService;
        this.userService=userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
    @PostMapping("/signUp")
    public ResponseEntity<DetailsResponseUserDTO> singUp(@RequestBody @Valid CreateUserDTO data,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        DetailsResponseUserDTO user = userService.createUser(data);
        URI url = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(url).body(user);
    }

}
