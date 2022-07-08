package com.facebook.facebookclone.model.dao;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;
    private String mail;
    private String name;
    private String lastname;
    private Integer age;
    private String description;
    private String work;
    private String hobby;
    private Boolean single;
    private String sex;
    private String activatedCode;

    @OneToMany(mappedBy = "user1",cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Friend> friends;

    @OneToMany(mappedBy = "user2",cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Friend> friends1;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<User> blockUsers;


    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.LAZY)
    private List<Product> products;


    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.LAZY)
    private List<Post> posts;


}
