package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.dto.PostResponseDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final StorageService storageService;

    //S3 이미지
    @Transactional
    public PostResponseDto writePost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        postRepository.save(post);

        return PostResponseDto.builder()
                .post(post)
                .build();
    }
}
