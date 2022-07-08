package com.facebook.facebookclone.service.impl;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.Post;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dao.VisibilityOfPost;
import com.facebook.facebookclone.repository.PostRepository;
import com.facebook.facebookclone.repository.UserRepository;
import com.facebook.facebookclone.service.PostService;
import com.facebook.facebookclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public Post createPost(Post post) {
        User currentUser = userService.getCurrentUser();
        post.setUser(currentUser);
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostRepository.UsersWhoCanSee> getPosts() {
        return postRepository.getPostForCurrentUser(userService.getCurrentUser().getId());
    }

    @Override
    public Page<Post> getPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }


    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with id: " + id + " doesn't exist"));
    }


}
