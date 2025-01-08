package JuanJose.ForoHub.config;

import JuanJose.ForoHub.config.filter.JwtTokenValidator;
import JuanJose.ForoHub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(crsf -> crsf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

     */


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(crsf -> crsf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    //public endpoints
                    http.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                    //private endpoints
                    http.requestMatchers(HttpMethod.GET, "/api/categories/**").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/subcategories/**").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/courses/**").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/topics/**").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/responses/**").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN","DEVELOPER");
                    http.requestMatchers(HttpMethod.GET, "/api/users/{id}/topics").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/users/{id}/topics/participate").authenticated();
                    http.requestMatchers(HttpMethod.GET, "/api/dashboard/**").hasAuthority("VIEW_REPORTS");
                    http.requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("CREATE_CATEGORY");
                    http.requestMatchers(HttpMethod.POST, "/api/subcategories/**").hasAuthority("CREATE_SUB_CATEGORY");
                    http.requestMatchers(HttpMethod.POST, "/api/courses/**").hasAuthority("CREATE_COURSE");
                    http.requestMatchers(HttpMethod.POST, "/api/topics/**").authenticated();
                    http.requestMatchers(HttpMethod.POST, "/api/responses/**").authenticated();
                    http.requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority("EDIT_CATEGORY");
                    http.requestMatchers(HttpMethod.PUT, "/api/subcategories/**").hasAuthority("EDIT_SUBCATEGORY");
                    http.requestMatchers(HttpMethod.PUT, "/api/courses/**").hasAuthority("EDIT_COURSE");
                    http.requestMatchers(HttpMethod.PUT, "/api/topics/{id}").hasAnyAuthority("EDIT_OWN_TOPIC","EDIT_ANY_TOPIC");
                    http.requestMatchers(HttpMethod.PUT, "/api/responses/**").hasAnyAuthority("EDIT_OWN_COMMENT","EDIT_ANY_COMMENT");
                    http.requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("DELETE_CATEGORY");
                    http.requestMatchers(HttpMethod.DELETE, "/api/subcategories/**").hasAuthority("DELETE_SUBCATEGORY");
                    http.requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasAuthority("DELETE_COURSE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/topics/{id}").hasAuthority("DELETE_ANY_TOPIC");
                    http.requestMatchers(HttpMethod.DELETE, "/api/responses/**").hasAuthority("DELETE_ANY_RESPONSE");
                    //Only Developer endPoints
                    http.requestMatchers(HttpMethod.GET, "/api/profiles/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.GET, "/api/permissions/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.POST, "/api/profiles/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.POST, "/api/permissions/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.POST, "/api/profile-permission").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.PUT, "/api/profiles/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.PUT, "/api/permissions/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasAuthority("EDIT_USER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/profiles/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/permissions/**").hasRole("DEVELOPER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAuthority("DELETE_USER");

                    //non specification endpoint
                    http.anyRequest().denyAll();//.authenticated()
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);//userDetailsService());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
    /*
    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetails = new ArrayList<>();

        userDetails.add(User.withUsername("mañeco")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build());

        userDetails.add(User.withUsername("ñañela")
                .password("1234")
                .roles("USER")
                .authorities("READ")
                .build());
        return new InMemoryUserDetailsManager(userDetails);
    }
     */

