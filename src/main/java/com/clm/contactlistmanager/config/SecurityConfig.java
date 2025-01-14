// @Configuration
// @EnableMethodSecurity(prePostEnabled = true)
// public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        var manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("Daniel")
//            .password(passwordEncoder().encode("1234"))
//            .roles("ADMIN", "USER")
//            .build());
//        manager.createUser(User.withUsername("James")
//            .password(passwordEncoder().encode("123"))
//            .roles("USER")
//            .build());
//        return manager;
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     http.cors(cors -> cors.configurationSource(request -> {
//         CorsConfiguration config = new CorsConfiguration();
//         config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//         config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         config.setAllowCredentials(true);
//         config.setAllowedHeaders(Arrays.asList("*"));
//         return config;
//     }))
//     .csrf(csrf -> csrf.disable())
//     .authorizeHttpRequests(auth -> auth
//         .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/**")).hasRole("ADMIN")
//         .requestMatchers(antMatcher(HttpMethod.PUT, "/api/**")).hasRole("ADMIN")
//         .requestMatchers(antMatcher("/api/**")).hasAnyRole("ADMIN", "USER")
//         .anyRequest().authenticated()
//     )
//     .formLogin(Customizer.withDefaults());

//     return http.build();
//     }
// }
// package com.clm.contactlistmanager.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
// import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// @Configuration
// @EnableMethodSecurity(prePostEnabled = true)
// public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        var manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("Daniel")
//            .password(passwordEncoder().encode("1234"))
//            .roles("ADMIN", "USER")
//            .build());
//        manager.createUser(User.withUsername("James")
//            .password(passwordEncoder().encode("123"))
//            .roles("USER")
//            .build());
//        return manager;
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/**")).hasRole("ADMIN")
//                .requestMatchers(antMatcher(HttpMethod.PUT, "/api/**")).hasRole("ADMIN")
//                .requestMatchers(antMatcher("/api/**")).hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
//            )
//            .formLogin(Customizer.withDefaults());

//        return http.build();
//    }
// }




package com.clm.contactlistmanager.config;

import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

   @Bean
   public UserDetailsService userDetailsService() {
       var manager = new InMemoryUserDetailsManager();
       manager.createUser(User.withUsername("Daniel")
           .password(passwordEncoder().encode("1234"))
           .roles("ADMIN", "USER")
           .build());
       manager.createUser(User.withUsername("James")
           .password(passwordEncoder().encode("123"))
           .roles("USER")
           .build());
       return manager;
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setUserDetailsService(userDetailsService());
       provider.setPasswordEncoder(passwordEncoder());
       return provider;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.cors(cors -> cors.configurationSource(request -> {
           CorsConfiguration config = new CorsConfiguration();
           config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
           config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
           config.setAllowedHeaders(Arrays.asList("*"));
           config.setAllowCredentials(true);
           config.setExposedHeaders(Arrays.asList("Location"));
           return config;
       }))
       .csrf(csrf -> csrf.disable())
       .authorizeHttpRequests(auth -> auth
           .requestMatchers(antMatcher("/api/v1/contacts")).permitAll()  // Add this line before other matchers
           .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/**")).hasRole("ADMIN")
           .requestMatchers(antMatcher(HttpMethod.PUT, "/api/**")).hasRole("ADMIN")
           .requestMatchers(antMatcher("/api/**")).hasAnyRole("ADMIN", "USER")
           .anyRequest().authenticated()
       )
       .formLogin(form -> form
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/api/v1/contacts", true)
            .permitAll()
        );

        return http.build();
    }
}