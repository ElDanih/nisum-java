package com.nisum.usecase.request;

import com.nisum.model.User.User;
import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RegisterUseCase {
    private final RequestGateway requestGateway;

    public Response register(Request request){
        return requestGateway.register(request);
    }

    public boolean existsByEmail(String email) {
        return requestGateway.existsByEmail(email);
    }

    public List<User> getAllUsers() {
        return requestGateway.getAllUsers();
    }

    public boolean inactivateUserByEmail(String email){
        return requestGateway.inactivateUserByEmail(email);
    }



}
