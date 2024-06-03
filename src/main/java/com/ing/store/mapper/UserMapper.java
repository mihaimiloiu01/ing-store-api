package com.ing.store.mapper;

import com.ing.store.dto.SignupDTO;
import com.ing.store.dto.UserDTO;
import com.ing.store.entity.authentication.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDTO toUserDto(User user);

  @Mapping(target = "password", ignore = true)
  User signUpToUser(SignupDTO signUpDto);
}
