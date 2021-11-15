package com.example.simpleboard.dto;

import com.example.simpleboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ArticleForm {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .id(null)
                .title(title)
                .content(content)
                .build();
    }
}
