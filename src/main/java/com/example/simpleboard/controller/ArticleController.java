package com.example.simpleboard.controller;

import com.example.simpleboard.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // logging
@Controller
@RequestMapping("/test")
public class ArticleController {

    @GetMapping("/articles")
    public String index(Model model) {
//        model.addAttribute("msg", "안녕하세요, 반갑습니다!");
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
}
