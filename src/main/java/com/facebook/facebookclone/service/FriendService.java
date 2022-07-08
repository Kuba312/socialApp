package com.facebook.facebookclone.service;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.User;

public interface FriendService {

    void acceptFriendRequest(Long id);

    void deleteFriendFromList(Long id);

    void addFriend(User user);

    void blockFriend(Long id);

    void deleteUserFromBlockList(Long id);

}
