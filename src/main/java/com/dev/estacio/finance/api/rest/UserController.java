package com.dev.estacio.finance.api.rest;

import com.dev.estacio.finance.mapper.UserMapper;
import com.dev.estacio.finance.model.dto.request.LoginRequest;
import com.dev.estacio.finance.model.dto.request.UserRequest;
import com.dev.estacio.finance.model.dto.response.UserLoginResponse;
import com.dev.estacio.finance.model.dto.response.UserResponse;
import com.dev.estacio.finance.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest user) {
        return status(CREATED).body(service.save(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Long userId) {
        return ok(UserMapper.instance.toResponse(service.findById(userId)));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable("id") Long userId,
                               @Valid @RequestBody UserRequest user) {
        return service.update(userId, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long userId) {
        service.delete(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var id = service.login(loginRequest.email(), loginRequest.password());
        return ok(id);
    }

}
