package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.model.Like;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.repository.LikeRepository;
import com.inno67.eatsyworld.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
//
//    private final LikeRepository likeRepository;
//    private final PostRepository postRepository;
//
//    // 게시글 좋아요 여부 확인
//    private boolean isNotAlreadyLikePost(Long userId, Post post) {
//        return likeRepository.findByUserIdAndPost(userId, post).isEmpty();
//    }
//
//    // 게시글 좋아요 등록
//    public boolean likePost(Long userId, Long postId) {
//        Post post = postRepository.findById(postId).orElseThrow();
//
//        if (isNotAlreadyLikePost(userId, post)) {
//            likeRepository.save(new Like(userId, post));
//            return true;
//        }
//        return false;
//    }
//
//    // 게시글 좋아요 취소
//    public void disLikePost(Long userId, Long postId) {
//        Post post = postRepository.findById(postId).orElseThrow();
//        Like like = likeRepository.findByUserIdAndPost(userId, post).orElseThrow();
//        likeRepository.delete(like);
//    }

}