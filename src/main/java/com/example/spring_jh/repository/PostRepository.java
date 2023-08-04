package com.example.spring_jh.repository;

import com.example.spring_jh.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Repository

public interface PostRepository extends JpaRepository<Post, Long> {

//    List<Post> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);
//    List<Post> findAllByOrderByPostListDesc();
//    Optional<Post> findById(Long id);

}
