package com.blog.blogingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.blogingproject.model.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
