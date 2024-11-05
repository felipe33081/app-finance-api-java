package com.dev.estacio.finance.service;

import com.dev.estacio.finance.exception.ObjectNotNullException;
import com.dev.estacio.finance.exception.UserAlreadyExistsException;
import com.dev.estacio.finance.exception.UserInvalidException;
import com.dev.estacio.finance.mapper.UserMapper;
import com.dev.estacio.finance.model.User;
import com.dev.estacio.finance.model.dto.request.UserRequest;
import com.dev.estacio.finance.model.dto.response.UserLoginResponse;
import com.dev.estacio.finance.model.dto.response.UserResponse;
import com.dev.estacio.finance.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public UserResponse save(UserRequest userRequest) {
        var user = UserMapper.instance.toEntity(userRequest);

        if (!isNull(repository.findByName(user.getName())) || !isNull(repository.findByName(user.getName()))) {
            throw new UserAlreadyExistsException("Usuário já existe. Cadastre um novo usuário!");
        }

        return UserMapper.instance.toResponse(repository.save(user));
    }

    public List<UserResponse> findAll() {
        return UserMapper.instance.toResponseList(repository.findAll());
    }

    public User findById(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new ObjectNotNullException("Usuário não encontrado com ID: " + userId));
    }

    @Transactional
    public UserResponse update(Long userId, UserRequest userRequest) {
        var user = UserMapper.instance.toEntity(userRequest);
        user.setId(userId);
        return UserMapper.instance.toResponse(repository.save(user));
    }

    @Transactional
    public void delete(Long userId) {
        repository.deleteById(userId);
    }

    public UserLoginResponse login(String email, String password) {
        var user = repository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new UserInvalidException("Usuário ou senha incorreta."));
        return new UserLoginResponse(user.getId());
    }

}
