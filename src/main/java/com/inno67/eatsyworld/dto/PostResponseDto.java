package com.inno67.eatsyworld.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inno67.eatsyworld.model.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long post_id;
    private String username;
    private String title;
    private String product;
    private String store;
    private String contents;
    private String imgUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private int LikeNum;

    @Builder
    public PostResponseDto(Post post, int likeNum){
        this.post_id = post.getPost_id();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.product = post.getProduct();
        this.store = post.getStore();
        this.contents = post.getContents();
        this.imgUrl = post.getImgUrl();
        this.createdAt = post.getCreatedAt();
        this.LikeNum = likeNum;
    }
}
