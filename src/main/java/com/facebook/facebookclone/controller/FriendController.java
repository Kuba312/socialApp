package com.facebook.facebookclone.controller;

import com.facebook.facebookclone.mapper.UserMapper;
import com.facebook.facebookclone.model.dto.UserDto;
import com.facebook.facebookclone.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {


    private final UserMapper userMapper;
    private final FriendService friendService;


    @PostMapping("/add")
    public void addFriend(@RequestBody UserDto userDto) {
        friendService.addFriend(userMapper.userDtoToUser(userDto));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/list-of-friends/delete/{friendId}")
    public void deleteFriendFromList(@PathVariable Long friendId) {
        friendService.deleteFriendFromList(friendId);
    }


    @PostMapping("/accept-request")
    public void acceptFriendRequest(@RequestParam Long friendId){

        friendService.acceptFriendRequest(friendId);
    }

    @PostMapping("block-friend/{id}")
    public void blockFriend(@PathVariable Long id) {
        friendService.blockFriend(id);
    }


    @DeleteMapping("/block-list/delete/{id}")
    public void deleteUserFromBlockList(@PathVariable Long id) {
        friendService.deleteUserFromBlockList(id);
    }
}


