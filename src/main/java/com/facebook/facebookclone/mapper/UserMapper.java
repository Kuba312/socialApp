package com.facebook.facebookclone.mapper;

import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dto.UserDto;

import java.util.List;

public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDtoWithFriend(User user);

    List<UserDto> listOfUserToListUserDto(List<User> users);

    UserDto userToUserWithBlockFriend(User user);
}
