//package com.apiDelivery.api.core.config;
//
//import com.apiDelivery.api.core.securityJwt.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig  {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//
//        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
//                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//
//        encoders.put("pbkdf2", pbkdf2Encoder);
//        DelegatingPasswordEncoder passwordEncoder =
//                new DelegatingPasswordEncoder("pbkdf2", encoders);
//        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//        return passwordEncoder;
//    }
//
//    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
//        throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//}
