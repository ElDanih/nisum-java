package com.nisum.jwt.config;


import com.nisum.jpa.user.repository.UserRepository;
import com.nisum.jpa.user.service.UserService;
import com.nisum.model.exception.ErrorCode;
import com.nisum.model.exception.RequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.nisum.jwt.config.TokenResources.*;


public class JwtValidationFilter extends BasicAuthenticationFilter {

    private final UserService userService;

    public JwtValidationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if(Objects.isNull(header) || !header.startsWith(PREFIX_TOKEN)){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            List<GrantedAuthority> authorities = List
                    .of(new SimpleGrantedAuthority(claims.get("role", String.class)));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);


            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

            //Actualizar el último inicio de sesión del usuario
            userService.updateLastLogin(username);

        } catch (Exception e) {
            throw new RequestException(ErrorCode.ERROR_401.getErrorCode(), ErrorCode.ERROR_401.getErrorMessage()) ;
        }


    }
}
