package com.example.simpleboard.service;

import com.example.simpleboard.dto.CommentForm;
import com.example.simpleboard.entity.Article;
import com.example.simpleboard.entity.Comment;
import com.example.simpleboard.repository.ArticleRepository;
import com.example.simpleboard.repository.CommentRepository;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public Long createComment(Long articleId, CommentForm form) {
        Comment comment = form.toEntity();
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글을 작성할 Article이 없습니다."));
        comment.stickTo(article);
        return commentRepository.save(comment).getId();
    }

    public Long updateComment(Long id, CommentForm form) {
        Comment comment = form.toEntity();
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        target.setContent(comment.getContent());

        return commentRepository.save(target).getId();
    }
}
