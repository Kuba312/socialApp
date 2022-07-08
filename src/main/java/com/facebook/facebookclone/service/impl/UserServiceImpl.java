package com.facebook.facebookclone.service.impl;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dto.RemindPassword;
import com.facebook.facebookclone.repository.FriendRepository;
import com.facebook.facebookclone.repository.RoleRepository;
import com.facebook.facebookclone.repository.UserRepository;
import com.facebook.facebookclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public User createUser(User user) {
        if (Character.isLowerCase(user.getName().charAt(0))
                || Character.isLowerCase(user.getLastname().charAt(0))) {
            user.setName(user.getName().substring(0, 1).toUpperCase()
                    + user.getName().substring(1));
            user.setLastname(user.getLastname().substring(0, 1).toUpperCase()
                    + user.getLastname().substring(1));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER")
                .ifPresent(role -> user.setRoles(Collections.singleton(role)));

        return userRepository.save(user);
    }


    @Override
    public User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(login).orElseThrow(UserServiceImpl::get);
    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserServiceImpl::get);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void remindPassword(RemindPassword remindPassword) {
        userRepository.findByMail(remindPassword.getMail())
                .ifPresent(user -> {
                    user.setActivatedCode(UUID.randomUUID().toString());
                    userRepository.save(user);
                });

    }

    @Override
    public void restartPassword(String activatedCode, RemindPassword remindPassword) {
        userRepository.findByActivatedCode(activatedCode)
                .ifPresent(user -> {
                    user.setPassword(passwordEncoder.encode(remindPassword.getPassword()));
                    userRepository.save(user);
                });
    }


    private static EntityNotFoundException get() {
        return new EntityNotFoundException("User doesn't exist");
    }
}
