package com.facebook.facebookclone.mapper.impl;

import com.facebook.facebookclone.mapper.UserMapper;
import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .name(user.getName())
                .lastname(user.getLastname())
                .mail(user.getMail())
                .age(user.getAge())
                .id(user.getId())
                .description(user.getDescription())
                .single(user.getSingle())
                .sex(user.getSex())
                .work(user.getWork())
                .hobby(user.getHobby())
                .build();
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .mail(userDto.getMail())
                .age(userDto.getAge())
                .id(userDto.getId())
                .description(userDto.getDescription())
                .single(userDto.getSingle())
                .sex(userDto.getSex())
                .work(userDto.getWork())
                .hobby(userDto.getHobby())
                .build();
    }

    @Override
    public UserDto userToUserDtoWithFriend(User user) {
        UserDto userDto = userToUserDto(user);
        userDto.setFriends(user.getFriends().stream()
                .filter(Friend::isAccepted)
                .map(friend -> userToUserDto(friend.getUser2()))
                .collect(Collectors.toSet()));
        userDto.getFriends().addAll(user.getFriends1()
                .stream()
                .filter(Friend::isAccepted)
                .filter(friend -> !friend.getUser1().getId().equals(userDto.getId()))
                .map(friend -> userToUserDto(friend.getUser1()))
                .collect(Collectors.toSet()));

        return userDto;
    }

    @Override
    public List<UserDto> listOfUserToListUserDto(List<User> users) {
        return users
                .stream()
                .map(this::userToUserWithBlockFriend)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto userToUserWithBlockFriend(User user) {
        UserDto userDto = userToUserDtoWithFriend(user);
        userDto.setBlockUsers(user.getBlockUsers().stream()
                .map(this::userToUserDtoWithFriend)
                .collect(Collectors.toList()));
        return userDto;
    }

}
