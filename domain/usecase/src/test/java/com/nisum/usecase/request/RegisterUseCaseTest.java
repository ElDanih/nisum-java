package com.nisum.usecase.request;

import com.nisum.model.request.Request;
import com.nisum.model.request.gateway.RequestGateway;
import com.nisum.model.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterUseCaseTest {

    @InjectMocks
    private RegisterUseCase registerUseCase;
    @Mock
    private RequestGateway requestGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        Request request = new Request();
        Response expectedResponse = new Response();
        when(requestGateway.register(any(Request.class))).thenReturn(expectedResponse);

        Response actualResponse = registerUseCase.register(request);

        assertEquals(expectedResponse, actualResponse);
        verify(requestGateway, times(1)).register(request);
    }

    @Test
    void testExistsByEmail() {
        String email = "test@example.com";
        when(requestGateway.existsByEmail(email)).thenReturn(true);

        boolean exists = registerUseCase.existsByEmail(email);

        assertTrue(exists);
        verify(requestGateway, times(1)).existsByEmail(email);
    }


}