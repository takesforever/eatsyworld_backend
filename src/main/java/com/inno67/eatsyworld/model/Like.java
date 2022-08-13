package com.inno67.eatsyworld.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Post post;


    public Like(Long userId, Post post) {
        this.userId = userId;
        this.post = post;
    }
}