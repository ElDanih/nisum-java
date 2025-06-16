package com.nisum.config;

import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.jpa.user.service.UserService;
import com.nisum.jwt.service.JwtService;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.usecase.request.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestGateway requestGateway(UserRepository userRepository, PasswordEncoder passwordEncoder
            , JwtService jwtService) {
        return new UserService(userRepository, passwordEncoder, jwtService);
    }

    @Bean
    public RegisterUseCase registerUseCase(RequestGateway requestGateway) {
        return new RegisterUseCase(requestGateway);
    }
}
