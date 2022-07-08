package com.facebook.facebookclone.controller;

import com.facebook.facebookclone.mapper.UserMapper;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dto.UserDto;
import com.facebook.facebookclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.userToUserDto(userService.createUser(userMapper.userDtoToUser(userDto)));
    }


    @GetMapping
    public UserDto getCurrentUser() {
        return userMapper.userToUserWithBlockFriend(userService.getCurrentUser());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get-users")
    public List<UserDto> getUsers() {
        return userMapper.listOfUserToListUserDto(userService.getUsers());
    }


    @DeleteMapping("/delete")
    public void deleteFriendsById(@RequestParam Long id) {
        userService.deleteUserById(id);
    }


    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.userToUserDtoWithFriend(userService.getUserById(id));
    }

    @GetMapping("/page")
    public Page<UserDto> getPageUser(@RequestParam Integer page, @RequestParam Integer size) {
        return userService.getPage(PageRequest.of(page, size))
                .map(userMapper::userToUserDto);
    }
}
