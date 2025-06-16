package com.nisum.jpa.user.service;

import com.nisum.jpa.phone.data.PhoneData;
import com.nisum.jpa.user.data.Role;
import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.jwt.service.JwtService;
import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserService implements RequestGateway {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Response register(Request request) {

        UserData userData = userRepository.save(UserData.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .role(Role.USER)
                .token(jwtService.getToken(request))
                .phones(request.getPhones().stream().map(phone ->
                        PhoneData.builder()
                                .number(phone.getNumber())
                                .citycode(phone.getCitycode())
                                .contrycode(phone.getContrycode())
                                .build()
                ).toList())
                .build());
        return Response.builder()
                .id(userData.getId())
                .created(userData.getCreated())
                .modified(userData.getModified())
                .lastLogin(userData.getLastLogin())
                .token(userData.getToken())
                .isActive(userData.isActive())
                .build();

    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByUsername(email);
    }
}
