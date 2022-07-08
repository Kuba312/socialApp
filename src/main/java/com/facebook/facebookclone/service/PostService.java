package com.facebook.facebookclone.service;

import com.facebook.facebookclone.model.dao.Post;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.model.dao.VisibilityOfPost;
import com.facebook.facebookclone.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    void deletePostById(Long id);

    List<PostRepository.UsersWhoCanSee> getPosts();

    Page<Post> getPage(Pageable pageable);


    Post getPostById(Long id);

}
