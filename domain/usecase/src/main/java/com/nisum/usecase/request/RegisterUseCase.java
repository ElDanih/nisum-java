package com.nisum.usecase.request;

import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUseCase {
    private final RequestGateway requestGateway;

    public Response register(Request request){
        return requestGateway.register(request);
    }

    public boolean existsByEmail(String email) {
        return requestGateway.existsByEmail(email);
    }
}
