package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.model.Like;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.LikeRepository;
import com.inno67.eatsyworld.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    // 게시글 좋아요 여부 확인
    private boolean isNotAlreadyLikePost(User user, Post post) {
        return likeRepository.findByUserAndPost(user, post).isEmpty();
    }

    // 게시글 좋아요 등록
    public boolean likePost(User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        if (isNotAlreadyLikePost(user, post)) {
            likeRepository.save(new Like(user, post));
            return true;
        }
        return false;
    }

    // 게시글 좋아요 취소
    public void disLikePost(User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Like like = likeRepository.findByUserAndPost(user, post).orElseThrow();
        likeRepository.delete(like);
    }

}