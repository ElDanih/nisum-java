package com.nisum.jpa.user.service;

import com.nisum.jpa.phone.data.PhoneData;
import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.jwt.JwtService;
import com.nisum.model.login.Login;
import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserService implements RequestGateway {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public Response register(Request request) {

        UserData userData = userRepository.save(UserData.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(jwtService.getToken(request))
                .isActive(true)
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
        return userRepository.existsByEmail(email);
    }

    @Override
    public Response login(Login login) {
        return null;
    }


}
