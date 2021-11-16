package com.example.simpleboard.service;

import com.example.simpleboard.dto.ArticleForm;
import com.example.simpleboard.entity.Article;
import com.example.simpleboard.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article createArticle(ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }

    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Article이 없습니다."));
    }

    public void updateArticle(Long id, ArticleForm form) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Article이 없습니다."));

        article.setId(form.getId());
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());

        articleRepository.save(article);
    }
}
