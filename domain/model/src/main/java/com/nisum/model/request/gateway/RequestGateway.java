package com.nisum.model.request.gateway;

import com.nisum.model.request.Request;
import com.nisum.model.response.Response;

public interface RequestGateway {
    Response register(Request request);
    boolean existsByEmail(String email);
}
