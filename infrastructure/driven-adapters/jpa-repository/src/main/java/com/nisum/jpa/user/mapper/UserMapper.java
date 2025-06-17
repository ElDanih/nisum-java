package com.nisum.jpa.user.mapper;

import com.nisum.jpa.phone.data.PhoneData;
import com.nisum.jpa.user.data.Role;
import com.nisum.jpa.user.data.UserData;
import com.nisum.jwt.service.JwtService;
import com.nisum.model.request.Phone;
import com.nisum.model.request.Request;
import com.nisum.model.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserData modelToData(Request request) {
        return UserData.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(jwtService.getToken(request))
                .phones(getPhonesList(request.getPhones()))
                .build();
    }

    private List<PhoneData> getPhonesList(List<Phone> phones) {
        return phones.stream()
                .map(phone -> PhoneData.builder()
                        .number(phone.getNumber())
                        .cityCode(phone.getCityCode())
                        .contryCode(phone.getContryCode())
                        .build())
                .toList();
    }

    public Response getResponse(UserData userData) {
        return Response.builder()
                .id(userData.getId())
                .created(userData.getCreated())
                .modified(userData.getModified())
                .lastLogin(userData.getLastLogin())
                .token(userData.getToken())
                .isActive(userData.isActive())
                .build();
    }
}
