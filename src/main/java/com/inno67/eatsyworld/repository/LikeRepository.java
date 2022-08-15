package com.inno67.eatsyworld.repository;

import com.inno67.eatsyworld.model.Like;
import com.inno67.eatsyworld.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndPost(Long userId, Post post);

    int countByPost(Long postId);

}