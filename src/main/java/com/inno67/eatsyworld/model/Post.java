package com.inno67.eatsyworld.model;

import com.inno67.eatsyworld.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
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

    @Column (nullable = false)
    private String imgUrl;

    @Column
    private LocalDateTime createdAt;

    public Post(PostRequestDto requestDto, User user){
        this.user = user;
        this.title = requestDto.getTitle();
        this.product = requestDto.getProduct();
        this.store = requestDto.getStore();
        this.contents = requestDto.getContents();
        //this.imgUrl = imgUrl;
    }

//    protected Post() {}
//
//    public Post(Long user_id, String title, String product, String store, String contents){
//        this.user = user_id;
//        this.title = title;
//        this.product = product;
//        this.store = store;
//        this.contents = contents;
//    }
//
//    public static Post create(Long user_id, String title, String product, String store, String contents) {
//        return new Post(user_id,title, product, store, contents);
    }
