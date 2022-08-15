package com.inno67.eatsyworld.dto;

import com.inno67.eatsyworld.model.User;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String product;
    private String store;
    private String contents;
}
