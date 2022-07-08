package com.facebook.facebookclone.repository;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.User;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {





    Optional<User> findByLogin(String login);

    List<User> findAllByLogin(String login);

    Optional<User> findByMail(String mail);

    Optional<User> findByActivatedCode(String activatedCode);

}
