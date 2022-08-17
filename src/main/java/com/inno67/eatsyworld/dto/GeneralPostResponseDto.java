package com.inno67.eatsyworld.dto;

import com.inno67.eatsyworld.model.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GeneralPostResponseDto {
    private final Long post_id;
    private final String title;
    private final String imgUrl;

    @Builder
    public GeneralPostResponseDto(Post post){
        this.post_id = post.getPost_id();
        this.title = post.getTitle();
        this.imgUrl = post.getImgUrl();
    }
}
