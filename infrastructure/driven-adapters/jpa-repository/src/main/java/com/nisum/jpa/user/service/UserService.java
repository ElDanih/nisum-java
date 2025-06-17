package com.nisum.jpa.user.service;

import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.mapper.UserMapper;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.model.User.User;
import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::dataToModel).toList();
    }

    @Transactional
    @Override
    public boolean inactivateUserByEmail(String email) {
        return userRepository.inactivateUserByUsername(email) > 0;
    }

    @Transactional
    public void updateLastLogin(String username) {
        userRepository.updateLastLoginByUsername(username);
    }

}
