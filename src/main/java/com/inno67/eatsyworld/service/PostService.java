package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.dto.GeneralPostResponseDto;
import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.dto.PostResponseDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.LikeRepository;
import com.inno67.eatsyworld.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final StorageService storageService;
    private final LikeRepository likeRepository;

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
    // 게시글 목록 리스트 조회
    public List<GeneralPostResponseDto> getPostsList() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<GeneralPostResponseDto> listContents = new ArrayList<>();
        for (Post post : posts) {
            GeneralPostResponseDto generalResponseDto = GeneralPostResponseDto.builder()
                    .post(post)
                    .build();
            listContents.add(generalResponseDto);
        }
        return listContents;
    }

    public List<GeneralPostResponseDto> getMyPostList(User user) {
        List<Post> posts = postRepository.findAllByUserOrderByCreatedAtDesc(user);
        List<GeneralPostResponseDto> listContents = new ArrayList<>();
        for (Post post : posts) {
            GeneralPostResponseDto mypageResponseDto = GeneralPostResponseDto.builder()
                    .post(post)
                    .build();
            listContents.add(mypageResponseDto);
        }
        return listContents;
    }
}
