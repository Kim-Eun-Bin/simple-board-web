package com.example.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ArticleForm {
    private String title;
    private String content;
}
