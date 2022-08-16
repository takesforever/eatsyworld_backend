package com.inno67.eatsyworld.dto;

import com.inno67.eatsyworld.model.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long post_id;
    private final String username;
    private final String title;
    private final String product;
    private final String store;
    private final String contents;
    private final String imgUrl;
    private final LocalDateTime createdAt;
    private final int LikeNum;

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
