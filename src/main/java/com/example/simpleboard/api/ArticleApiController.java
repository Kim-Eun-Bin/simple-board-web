package com.example.simpleboard.api;

import com.example.simpleboard.dto.ArticleForm;
import com.example.simpleboard.entity.Article;
import com.example.simpleboard.repository.ArticleRepository;
import com.example.simpleboard.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/api/articles") // Post 요청이 "/api/articles" url로 온다면, 메소드 수행!
    public void create(@RequestBody ArticleForm form) { // JSON 데이터를 받아옴!
        articleService.createArticle(form);
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id) {
        Article entity = articleService.findById(id);
        return new ArticleForm(entity);
    }

    @PutMapping("/api/articles/{id}")
    public void update(@PathVariable Long id, @RequestBody ArticleForm form) {
        articleService.updateArticle(id, form);
    }

    @DeleteMapping("/api/articles/{id}")
    public void delete(@PathVariable Long id) {
        articleService.deleteById(id);
    }
}
