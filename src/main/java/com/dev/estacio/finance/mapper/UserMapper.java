package com.dev.estacio.finance.mapper;

import com.dev.estacio.finance.model.User;
import com.dev.estacio.finance.model.dto.request.UserRequest;
import com.dev.estacio.finance.model.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(Iterable<User> users);

}
