package com.nisum.jpa.user.mapper;

import com.nisum.jpa.phone.data.PhoneData;
import com.nisum.jpa.user.data.UserData;
import com.nisum.jwt.service.JwtService;
import com.nisum.model.request.Phone;
import com.nisum.model.request.Request;
import com.nisum.model.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testModelToData() {
        Request request = new Request();
        request.setName("John Doe");
        request.setUsername("johndoe");
        request.setPassword("password123");
        request.setPhones(List.of(
                new Phone("123456789", "1", "57")
        ));

        LocalDateTime now = LocalDateTime.now();
        String encodedPassword = "encodedPassword123";
        String token = "jwtToken";

        Mockito.when(passwordEncoder.encode("password123")).thenReturn(encodedPassword);
        Mockito.when(jwtService.getToken(any(Request.class))).thenReturn(token);

        UserData userData = userMapper.modelToData(request);

        assertEquals("John Doe", userData.getName());
        assertEquals("johndoe", userData.getUsername());
        assertEquals(encodedPassword, userData.getPassword());
        assertEquals(token, userData.getToken());
        assertEquals(1, userData.getPhones().size());
        PhoneData phoneData = userData.getPhones().get(0);
        assertEquals("123456789", phoneData.getNumber());
        assertEquals("1", phoneData.getCityCode());
        assertEquals("57", phoneData.getContryCode());
    }

    @Test
    void testGetResponse() {
        UUID userId = UUID.randomUUID();
        UserData userData = UserData.builder()
                .id(userId)
                .created(LocalDateTime.of(2023, 1, 1, 12, 0))
                .modified(LocalDateTime.of(2023, 6, 1, 12, 0))
                .lastLogin(LocalDateTime.of(2023, 6, 15, 15, 30))
                .token("jwtToken")
                .isActive(true)
                .build();

        Response response = userMapper.getResponse(userData);

        assertEquals(userId, response.getId());
        assertEquals("jwtToken", response.getToken());
        assertTrue(response.isActive());
    }



}