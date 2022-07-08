package com.facebook.facebookclone.config;

import com.facebook.facebookclone.model.dao.Role;
import com.facebook.facebookclone.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration

public class AppConfig {


    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
            Optional<Role> roleCreator = roleRepository.findByName("ROLE_CREATOR");

            if (roleUser.isEmpty()) {
                roleRepository.save(Role.builder().name("ROLE_USER")
                        .build());
            }
            if (roleCreator.isEmpty()) {
                roleRepository.save(Role.builder().name("ROLE_CREATOR")
                        .build());
            }
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





//    @Bean
//    public AuditorAware<String> auditorAwareProvider() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return () -> Optional.ofNullable(authentication != null ? authentication.getName() : null);
//    }

}
