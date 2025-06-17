package com.nisum.jwt.service;

import com.nisum.jpa.user.data.Role;
import com.nisum.jpa.user.data.UserData;
import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.model.exception.ErrorCode;
import com.nisum.model.exception.RequestException;
import com.nisum.model.request.Request;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.nisum.jwt.config.TokenResources.*;

@Service
@RequiredArgsConstructor
public class JwtService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userDataOptional = userRepository.findByUsername(username);
        if(userDataOptional.isEmpty()){
            throw new RequestException(ErrorCode.ERROR_404.getErrorCode(),
                ErrorCode.ERROR_404.getErrorMessage().concat( " ->" + username));
        }

        UserData userData = userDataOptional.orElseThrow();

        /*List<GrantedAuthority> authorities = userDataOptional.get().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());*/

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userData.getRole().name()));

        return new User(userData.getUsername(), userData.getPassword(), userData.isActive(),
                true, true, true, authorities);
    }

    public String getToken(Request request) {
        Claims claims = Jwts.claims()
                .add("role", Role.USER.name())
                .add("username", request.getUsername())
                .build();
        return  Jwts.builder()
                .subject(request.username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3_600_000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }
}
