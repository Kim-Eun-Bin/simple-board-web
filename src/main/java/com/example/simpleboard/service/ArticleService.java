package com.example.simpleboard.service;

import com.example.simpleboard.dto.ArticleForm;
import com.example.simpleboard.entity.Article;
import com.example.simpleboard.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article createArticle(ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }
}
