package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.dto.GeneralPostResponseDto;
import com.inno67.eatsyworld.dto.PostRequestDto;
import com.inno67.eatsyworld.dto.PostResponseDto;
import com.inno67.eatsyworld.model.Post;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.LikeRepository;
import com.inno67.eatsyworld.repository.PostRepository;
import com.inno67.eatsyworld.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final StorageService storageService;
    private final LikeRepository likeRepository;

    //S3 이미지
    @Transactional
    public PostResponseDto writePost(PostRequestDto requestDto, User user, MultipartFile imagefile) {
        String imgUrl = "";
        if(imagefile != null) {
            imgUrl = storageService.uploadFile(imagefile);
        }
        Post post = new Post(requestDto, user, imgUrl);
        postRepository.save(post);
        return PostResponseDto.builder()
                .post(post)
                .build();
    }

    // 게시글 목록 리스트 조회
    public List<GeneralPostResponseDto> getPostsList() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<GeneralPostResponseDto> listContents = new ArrayList<>();
        for (Post post : posts) {
            GeneralPostResponseDto postGeneralResponseDto = GeneralPostResponseDto.builder()
                    .post(post)
                    .build();
            listContents.add(postGeneralResponseDto);
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



    @Transactional
    public String updatePost(Long postId, PostRequestDto requestDto, User user, MultipartFile imagefile) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (post.getUser().getUser_id().equals(user.getUser_id())) {
            String imgUrl = "";
            if(imagefile != null) {
                imgUrl = storageService.uploadFile(imagefile);
            }else{
                imgUrl = post.getImgUrl();
            }
            post.update(requestDto, imgUrl);
            return ("게시글을 수정했습니다.");
        } else {
            return ("게시글의 작성자가 아닙니다.");
        }
    }
    public void deletePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (post.getUser().getUser_id().equals(user.getUser_id())){
            postRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
    }
}
