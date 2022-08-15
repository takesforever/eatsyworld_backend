package com.inno67.eatsyworld.controller;


import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.security.UserDetailsImpl;
import com.inno67.eatsyworld.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart PostRequestDto requestDto,
            @RequestPart(required = false) MultipartFile imageFile
            ){
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        User user = userDetails.getUser();
        postService.writePost(requestDto, user, imageFile);
    }
}
