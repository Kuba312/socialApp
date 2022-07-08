package com.facebook.facebookclone.model.dto;


import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.Product;
import com.facebook.facebookclone.model.dao.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

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

   private Set<UserDto> friends;
   private Set<UserDto> friendsUser1;

    private Set<Product> products;


    private List<UserDto> blockUsers;
}
