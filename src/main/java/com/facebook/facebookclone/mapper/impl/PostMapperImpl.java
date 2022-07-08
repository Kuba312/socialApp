package com.facebook.facebookclone.mapper.impl;


import com.facebook.facebookclone.mapper.PostMapper;
import com.facebook.facebookclone.mapper.UserMapper;
import com.facebook.facebookclone.model.dao.Post;
import com.facebook.facebookclone.model.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapperImpl implements PostMapper {

    private final UserMapper userMapper;

    @Override
    public PostDto postToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .post(post.getPost())
                .visibility(post.getVisibility())
                .build();
    }

    @Override
    public Post postDtoToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .post(postDto.getPost())
                .visibility(postDto.getVisibility())
                .build();
    }

    @Override
    public PostDto postToPostDtoWithUser(Post post) {
        PostDto postDto = postToPostDto(post);
        postDto.setAddedBy(userMapper.userToUserDto(post.getUser()));

        return postDto;
    }

}
