package com.facebook.facebookclone.repository;

import com.facebook.facebookclone.model.dao.Friend;
import com.facebook.facebookclone.model.dao.Post;
import com.facebook.facebookclone.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "select  p.post, f.user1_id as whoCanSeeId, p.user_id as whoAdded " +
            "from friend f " +
            "join post p " +
            "on p.user_id = " +
            "case " +
            "when p.user_id = f.user1_id then f.user1_id " +
            "when p.user_id = f.user2_id then f.user2_id " +
            "else 0 " +
            "end = 1 " +
            "where p.visibility = 'ONLY_FRIENDS' " +
            "and f.user1_id is not null " +
            "and f.user1_id=?1 " +
            "union " +
            "select  p.post, f.user2_id as whoCanSeeId, p.user_id as whoAdded " +
            "from friend f " +
            "join post p " +
            "on p.user_id = " +
            "case " +
            "when p.user_id = f.user1_id then f.user1_id " +
            "when p.user_id = f.user2_id then f.user2_id " +
            "else 0 " +
            "end = 1 " +
            "where p.visibility = 'ONLY_FRIENDS' " +
            "and f.user2_id is not null " +
            "and f.user2_id=?1 " +
            "union " +
            "select p.post,u.id as whoCanSee, p.user_id as whoAdded " +
            "from user u " +
            "join post p " +
            "on  u.id = p.user_id " +
            "where p.visibility = 'PUBLIC' " +
            "or (p.visibility = 'ONLY_I' and p.user_id =?1)"
            , nativeQuery = true)
    List<UsersWhoCanSee> getPostForCurrentUser(Long userId);


    interface UsersWhoCanSee {
        String getPost();

        void setPost(String post);

        Long getWhoCanSeeId();

        void setWhoCanSeeId(Long whoCanSeeId);

        Long getWhoAdded();

        void setWhoAdded(Long whoAdded);

    }

}
