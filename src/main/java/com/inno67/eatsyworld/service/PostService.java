package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.dto.PostResponseDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sun.net.www.MimeTable;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //로그인 후 작성 가능하게 추후에 내용 추가
    @Transactional
    public Post writePost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }
}
