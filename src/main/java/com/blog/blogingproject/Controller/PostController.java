package com.blog.blogingproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.blogingproject.model.Post;
import com.blog.blogingproject.repository.PostRepo;


@Controller
public class PostController {

    @Autowired
    PostRepo repo;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listPosts",repo.findAll());
        return "index"; 
    }

    @GetMapping("/new")
    public String newPost(Model model){
        model.addAttribute("post",new Post());
        return "newPost";
    }
    @PostMapping("/save")
    public String  SavePost(@ModelAttribute("post") Post post){
        repo.save(post);
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable int id, Model model) {
        Post post = repo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable int id, @ModelAttribute("post") Post post) {
        post.setId(id);
        repo.save(post);
        return "redirect:/";
    }
   
    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable int id, Model model) {
        Post post = repo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        return "deletePost";
    }

   
    @GetMapping("/delete/confirm/{id}")
    public String deletePost(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
