package com.example.simpleboard.controller;

import com.example.simpleboard.dto.ArticleForm;
import com.example.simpleboard.entity.Article;
import com.example.simpleboard.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // logging
@Controller
@RequestMapping("/test")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public String findAll(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "articles/index";
    }

    @GetMapping("/init")
    public void initArticles() {
        ArticleForm form = new ArticleForm(1L, "title-A", "content-A");
        ArticleForm form2 = new ArticleForm(2L, "title-B", "content-B");

        articleService.createArticle(form);
        articleService.createArticle(form2);
    }

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles")
    public String createAriticle(ArticleForm form) {
        log.info(form.toString());
        return "redirect:/test/articles";
    }

    @GetMapping("/articles/{id}")
    public String showArticles(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id);

        model.addAttribute("article", article);
        model.addAttribute("comments", article.getComments());
        return "articles/show";
    }

    @GetMapping("/articles/edit/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        Article target = articleService.findById(id);
        model.addAttribute("article", target);
        return "articles/edit";
    }
}
