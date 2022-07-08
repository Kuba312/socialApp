package com.facebook.facebookclone;

import com.facebook.facebookclone.model.dao.Role;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.repository.RoleRepository;
import com.facebook.facebookclone.repository.UserRepository;
import com.facebook.facebookclone.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {


    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;


    @Test
    void createUser() {
        //given
        User user = new User();
        user.setPassword("kuba123");
        user.setName("kuba");
        user.setLastname("michalak");

        Mockito.when(passwordEncoder.encode("kuba123")).thenReturn("cokolwiek");
        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(new Role(1L, "ROLE_USER")));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());

        //when
        User result = userServiceImpl.createUser(user);

        //then
        assertEquals(new User(), result);

    }

    @Test
    void getCurrentUserTest() {
        //given
        User user = new User();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        Mockito.when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        //when
        User result = userServiceImpl.getCurrentUser();

        //then
        assertEquals(user, result);
    }

    @Test
    void getUsersTest() {
        //given
        User user = new User();
        List<User> users = new ArrayList<>();

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        Mockito.when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findAll()).thenReturn(users);

        //when
        List<User> result = userServiceImpl.getUsers();

        //then
        assertEquals(users, result);

    }

    @Test
    void deleteUserByIdTest() {
        //given
        User user = new User();
        user.setId(1L);
        //when
        userServiceImpl.deleteUserById(1L);
        //then
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void getUserById() {
        //given
        User user = new User();
        user.setId(1L);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when
        User result = userServiceImpl.getUserById(1L);

        //then
        assertEquals(user, result);

    }


    @Test
    void getPageTest(){



    }
}
