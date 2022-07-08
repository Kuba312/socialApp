package com.facebook.facebookclone.service;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dto.RemindPassword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User createUser(User user);


    User getCurrentUser();

    List<User> getUsers();


    void deleteUserById(Long id);



    User getUserById(Long id);

    Page<User> getPage(Pageable pageable);

    void remindPassword(RemindPassword remindPassword);

    void restartPassword(String activatedCode, RemindPassword remindPassword);


}
