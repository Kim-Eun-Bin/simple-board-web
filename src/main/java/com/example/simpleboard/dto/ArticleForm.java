package com.example.simpleboard.dto;

import com.example.simpleboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public ArticleForm(Article entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

    public Article toEntity() {
        return Article.builder()
                .id(null)
                .title(title)
                .content(content)
                .build();
    }
}
