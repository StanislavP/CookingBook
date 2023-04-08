package com.example.CookingBook.config;

import com.example.CookingBook.enums.UserRoles;
import com.example.CookingBook.repository.UserRepository;
import com.example.CookingBook.services.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                .requestMatchers("/" , "/fonts/**","/scss/**","/error/**" )
                    .permitAll()
                .requestMatchers("/product/add", "/product/delete/**", "/product/edit/**").hasRole(UserRoles.MODERATOR.name())
                .requestMatchers("/admin/**").hasRole(UserRoles.ADMIN.name())
                .requestMatchers( "/auth/login/**","/auth/login-error", "/auth/register","/auth/confirm-account")
                    .anonymous()
                .anyRequest()
                    .authenticated()
                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me")
                    .key("uniqueAndSecret")
                .and()
                    .formLogin()
                    .loginPage("/auth/login")
                    .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                    .defaultSuccessUrl("/",true)
                    .failureForwardUrl("/auth/login-error")
                .and().logout()//configure logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                .and()
                    .securityContext()
                    .securityContextRepository(securityContextRepository);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}
