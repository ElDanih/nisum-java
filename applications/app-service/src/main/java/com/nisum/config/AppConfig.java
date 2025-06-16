package com.nisum.config;

import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.jpa.user.service.UserService;
import com.nisum.jwt.JwtService;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.usecase.request.RegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RequestGateway requestGateway(UserRepository userRepository, JwtService jwtService) {
        return new UserService(userRepository, jwtService);
    }

    @Bean
    public RegisterUseCase registerUseCase(RequestGateway requestGateway) {
        return new RegisterUseCase(requestGateway);
    }
}
