package com.inno67.eatsyworld.repository;

import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByUserOrderByCreatedAtDesc(User user);
}
