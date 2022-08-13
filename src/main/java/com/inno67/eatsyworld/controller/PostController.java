package com.inno67.eatsyworld.controller;


import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    //로그인 후 가능할 수 있도록 추후 생성된 class 추가
    @PostMapping
    public Post createPost(
            //@AuthenticationPrincipal,
            @RequestBody PostRequestDto requestDto
            ){
        return postService.writePost(requestDto);
    }

}
