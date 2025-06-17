package com.nisum.jpa.user.service;

import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.mapper.UserMapper;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService implements RequestGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Response register(Request request) {
        UserData userData = userRepository.save(userMapper.modelToData(request));
        return userMapper.getResponse(userData);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByUsername(email);
    }
}
