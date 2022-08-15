package com.inno67.eatsyworld.model;

import com.inno67.eatsyworld.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false)
    private String product;

    @Column (nullable = false)
    private String store;

    @Column (nullable = false)
    private String contents;

    private String imgUrl;

    public Post(PostRequestDto requestDto, User user, String imgUrl){
        this.user = user;
        this.title = requestDto.getTitle();
        this.product = requestDto.getProduct();
        this.store = requestDto.getStore();
        this.contents = requestDto.getContents();
        this.imgUrl = imgUrl;
    }
}
