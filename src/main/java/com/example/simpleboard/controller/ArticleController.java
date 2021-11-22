package com.example.simpleboard.controller;

import com.example.simpleboard.dto.ArticleForm;
import com.example.simpleboard.dto.SessionUser;
import com.example.simpleboard.entity.Article;
import com.example.simpleboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j // logging
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // HTTP 통신의 Session 객체
    private final HttpSession httpSession;

    @GetMapping("/articles")
    public String findAll(Model model) {
        model.addAttribute("articles", articleService.findAll());

        // 세션에서 키가 'user'인 객체를 가져와 이를 캐스팅 (Object -> SessionUser)
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

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
    public String newArticle(Model model) {
        // 세션에서 키가 'user'인 객체를 가져와 이를 캐스팅 (Object -> SessionUser)
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

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

        // 세션에서 키가 'user'인 객체를 가져와 이를 캐스팅 (Object -> SessionUser)
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "articles/show";
    }

    @GetMapping("/articles/edit/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        Article target = articleService.findById(id);
        model.addAttribute("article", target);

        // 세션에서 키가 'user'인 객체를 가져와 이를 캐스팅 (Object -> SessionUser)
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "articles/edit";
    }
}
