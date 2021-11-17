package com.example.simpleboard.repository;

import com.example.simpleboard.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
