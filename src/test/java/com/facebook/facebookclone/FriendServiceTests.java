package com.facebook.facebookclone;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.Role;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.repository.FriendRepository;
import com.facebook.facebookclone.repository.RoleRepository;
import com.facebook.facebookclone.repository.UserRepository;
import com.facebook.facebookclone.service.UserService;
import com.facebook.facebookclone.service.impl.FriendServiceImpl;
import com.facebook.facebookclone.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FriendServiceTests {



    @InjectMocks
    private FriendServiceImpl friendServiceImpl;


    @Mock
    private UserService userService;


    @Mock
    private FriendRepository friendRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;




    @Test
    void addFriendTest() {
        User currentUser = new User();
        User friendUser = new User();


        currentUser.setId(3L);
        currentUser.setBlockUsers(Collections.emptyList());
        friendUser.setId(4L);

        Mockito.when(userService.getCurrentUser()).thenReturn(currentUser);
        Mockito.when(userService.getUserById(4L)).thenReturn(friendUser);

        friendServiceImpl.addFriend(friendUser);

        Mockito.verify(friendRepository,Mockito.times(1))
                .save(Friend.builder().user1(currentUser).user2(friendUser).build());


    }
}
