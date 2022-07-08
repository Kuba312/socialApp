package com.facebook.facebookclone.controller;

import com.facebook.facebookclone.mapper.PostMapper;
import com.facebook.facebookclone.model.dao.Post;
import com.facebook.facebookclone.model.dao.VisibilityOfPost;
import com.facebook.facebookclone.model.dto.PostDto;
import com.facebook.facebookclone.model.dto.UserDto;
import com.facebook.facebookclone.repository.PostRepository;
import com.facebook.facebookclone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postMapper.postToPostDtoWithUser(postService.createPost(postMapper.postDtoToPost(postDto)));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    void deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id){
        return postMapper.postToPostDtoWithUser(postService.getPostById(id));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PostRepository.UsersWhoCanSee> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/page")
    public Page<PostDto> getPageUser(@RequestParam Integer page, @RequestParam Integer size) {
        return postService.getPage(PageRequest.of(page, size))
                .map(postMapper::postToPostDto);
    }
}
