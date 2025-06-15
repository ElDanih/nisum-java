package com.nisum.jpa.user.service;

import com.nisum.jpa.phone.data.PhoneData;
import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService implements RequestGateway {

    private final UserRepository userRepository;

    @Override
    public Response register(Request request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserData userData = userRepository.save(UserData.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(UUID.randomUUID().toString())
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
}
