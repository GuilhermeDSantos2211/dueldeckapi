package com.dueldeckapi.mapper;

import com.dueldeckapi.dto.User.UserResponseDTO;
import com.dueldeckapi.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO mapUserDTO(UserEntity userEntity);
}
