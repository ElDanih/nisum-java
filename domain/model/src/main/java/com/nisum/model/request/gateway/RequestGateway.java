package com.nisum.model.request.gateway;

import com.nisum.model.User.User;
import com.nisum.model.request.Request;
import com.nisum.model.response.Response;

import java.util.List;

public interface RequestGateway {
    Response register(Request request);
    boolean existsByEmail(String email);
    List<User> getAllUsers();
    boolean inactivateUserByEmail(String email);
}
