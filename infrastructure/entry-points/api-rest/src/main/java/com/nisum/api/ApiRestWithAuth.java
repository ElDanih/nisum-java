package com.nisum.api;

import com.nisum.model.User.Email;
import com.nisum.usecase.request.RegisterUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRestWithAuth {

    private final RegisterUseCase registerUseCase;

    @GetMapping(path = "/users")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(registerUseCase.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(path = "/inactivate")
    public ResponseEntity<?> inactivateUser(@RequestBody Email email) {
        boolean inactivated = registerUseCase.inactivateUserByEmail(email.getEmail());
        if (inactivated) {
            return new ResponseEntity<>("Usuario desactivado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No fue posible desactivar el usuario", HttpStatus.NOT_FOUND);
        }

    }
}
