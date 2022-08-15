package com.inno67.eatsyworld.controller;

import com.inno67.eatsyworld.security.UserDetailsImpl;
import com.inno67.eatsyworld.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    // 게시글 좋아요 등록
    @PostMapping("/posts/{postsId}")
    public ResponseEntity<String> likePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postsId) {
        boolean result = false;
        if (userDetails != null) {
            result = likeService.likePost(userDetails.getUser(), postsId);
        }
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.OK);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/posts/{postsId}")
    public ResponseEntity<String> disLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postsId) {
        if (userDetails != null) {
            likeService.disLikePost(userDetails.getUser(), postsId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
