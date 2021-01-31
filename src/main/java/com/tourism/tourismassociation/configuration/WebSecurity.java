package com.tourism.tourismassociation.configuration;

import com.tourism.tourismassociation.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, SecurityConstants.LANDMARKS_URL).permitAll() //settings /landmarks endpoint as public
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL, SecurityConstants.RATINGS_URL).permitAll() //setting signup and ratings public
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()));

        //Should disable security for swagger here

        //Disabling security for h2-console
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
