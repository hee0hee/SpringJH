package com.example.spring_jh.repository;

import com.example.spring_jh.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();

    List<Post> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);
}
