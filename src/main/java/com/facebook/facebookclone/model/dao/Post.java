package com.facebook.facebookclone.model.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String post;

    @Enumerated(EnumType.STRING)
    private VisibilityOfPost visibility;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
