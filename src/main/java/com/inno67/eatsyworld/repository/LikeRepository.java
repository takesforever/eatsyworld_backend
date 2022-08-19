package com.inno67.eatsyworld.repository;

import com.inno67.eatsyworld.model.Like;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);

    int countByPost(Post post);
}