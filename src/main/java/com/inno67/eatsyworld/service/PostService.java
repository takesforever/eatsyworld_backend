package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.dto.PostResponseDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final StorageService storageService;

    //S3 이미지
    @Transactional
    public void writePost(PostRequestDto requestDto, User user, MultipartFile imagefile) {
        String imgUrl = "";
        if(imagefile != null) {
            imgUrl = storageService.uploadFile(imagefile);
        }
        Post post = new Post(requestDto, user, imgUrl);
        postRepository.save(post);
    }
}
