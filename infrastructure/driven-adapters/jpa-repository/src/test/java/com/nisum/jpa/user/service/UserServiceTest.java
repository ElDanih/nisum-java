package com.nisum.jpa.user.service;

import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.mapper.UserMapper;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.model.request.Request;
import com.nisum.model.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        Request request = new Request();
        UserData userData = new UserData();
        Response response = new Response();

        when(userMapper.modelToData(request)).thenReturn(userData);
        when(userRepository.save(userData)).thenReturn(userData);
        when(userMapper.getResponse(userData)).thenReturn(response);

        Response result = userService.register(request);

        assertEquals(response, result);
        verify(userMapper).modelToData(request);
        verify(userRepository).save(userData);
        verify(userMapper).getResponse(userData);
    }

    @Test
    void testExistsByEmail() {
        String email = "test@example.com";
        when(userRepository.existsByUsername(email)).thenReturn(true);

        boolean result = userService.existsByEmail(email);

        assertTrue(result);
        verify(userRepository).existsByUsername(email);
    }
}