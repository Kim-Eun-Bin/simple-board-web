package com.example.simpleboard.controller;

import com.example.simpleboard.dto.ArticleForm;
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
        model.addAttribute("article", articleService.findById(id));
        return "articles/show";
    }
}
