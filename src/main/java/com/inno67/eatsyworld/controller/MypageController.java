package com.inno67.eatsyworld.controller;

import com.inno67.eatsyworld.dto.GeneralPostResponseDto;
import com.inno67.eatsyworld.security.UserDetailsImpl;
import com.inno67.eatsyworld.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class MypageController {
    private final PostService postService;


    @GetMapping("/api/mypage")
    public List<GeneralPostResponseDto> getMyList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails.getUser() == null) {
            throw new NullPointerException(
                    "로그인이 필요합니다."
            );
        }
        return postService.getMyPostList(userDetails.getUser());
    }
}
