package com.example.simpleboard.api;

import com.example.simpleboard.dto.CommentForm;
import com.example.simpleboard.entity.Comment;
import com.example.simpleboard.repository.CommentRepository;
import com.example.simpleboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/comments/{articleId}")
    public Long createComment(@PathVariable Long articleId, @RequestBody CommentForm form) {
        return commentService.createComment(articleId, form);
    }
}
