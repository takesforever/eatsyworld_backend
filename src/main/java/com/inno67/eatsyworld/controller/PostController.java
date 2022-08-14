package com.inno67.eatsyworld.controller;


import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.dto.PostResponseDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.security.UserDetailsImpl;
import com.inno67.eatsyworld.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    //로그인 후 가능할 수 있도록 추후 생성된 class 추가
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PostResponseDto createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody PostRequestDto requestDto
           // @RequestPart MultipartFile imageFile
            ){
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        User user = userDetails.getUser();
        return postService.writePost(requestDto, user);
    }
}
