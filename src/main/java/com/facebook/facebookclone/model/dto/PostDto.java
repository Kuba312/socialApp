package com.facebook.facebookclone.model.dto;

import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dao.VisibilityOfPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostDto {


    private Long id;

    private String post;

    private VisibilityOfPost visibility;

    private UserDto addedBy;
}

