package com.facebook.facebookclone.service.impl;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.repository.FriendRepository;
import com.facebook.facebookclone.repository.UserRepository;
import com.facebook.facebookclone.service.FriendService;
import com.facebook.facebookclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    @Override
    public void addFriend(User user) {
        User currentUser = userService.getCurrentUser();
        User userFriend = userService.getUserById(user.getId());

        if (userFriend.getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("You can not add yourself!");
        }
        List<User> blockUsers = currentUser.getBlockUsers();
        for (User blockUser : blockUsers) {
            if (blockUser.getId().equals(userFriend.getId())) {
                throw new IllegalArgumentException("This user has been blocked!");
            }
        }
        friendRepository.save(Friend.builder()
                .user1(currentUser)
                .user2(userFriend)
                .build());
    }

    @Override
    public void deleteFriendFromList(Long friendId) {
        friendRepository.deleteById(friendId);
    }

    @Override
    public void acceptFriendRequest(Long friendId) {
        User currentUser = userService.getCurrentUser();
        Friend friend = friendRepository
                .findById(friendId).orElseThrow(() -> new EntityNotFoundException("user doesn't exist"));
        if (!friend.getUser2().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException();
        }
        friend.setAccepted(true);
        friendRepository.save(friend);
    }

    @Override
    public void blockFriend(Long friendId) {
        User currentUser = userService.getCurrentUser();
        List<Friend> friends = currentUser.getFriends();
        List<User> blockUsers = currentUser.getBlockUsers();

        Friend friend = friendRepository
                .findById(friendId)
                .orElseThrow(() -> new EntityNotFoundException("friend doesn't exist"));
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).getUser2().getId().equals(friend.getUser2().getId())) {
                friends.remove(friends.get(i));
                currentUser.setFriends(friends);
                blockUsers.add(friend.getUser2());
            }
        }
        friendRepository.delete(friend);
    }


    @Override
    public void deleteUserFromBlockList(Long id) {
        User currentUser = userService.getCurrentUser();
        List<User> blockUsers = currentUser.getBlockUsers();
        User user = userService.getUserById(id);

        blockUsers.remove(user);

        userRepository.save(currentUser);
    }

}















