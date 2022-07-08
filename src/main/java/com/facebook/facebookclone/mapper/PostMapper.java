package com.facebook.facebookclone.mapper;

import com.facebook.facebookclone.model.dao.Post;
import com.facebook.facebookclone.model.dto.PostDto;

public interface PostMapper {

    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto postDto);

    PostDto postToPostDtoWithUser(Post post);
}
