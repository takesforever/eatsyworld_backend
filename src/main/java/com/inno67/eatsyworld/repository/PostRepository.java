package com.inno67.eatsyworld.repository;

import com.inno67.eatsyworld.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
