package com.codeacademy.tbrblogass.controller;


import com.codeacademy.tbrblogass.services.BlogsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private BlogsService blogsService;

    public BlogController(BlogsService blogsService) { this.blogsService = blogsService; }

    @GetMapping("/{id}")
    public String getBlog(@PathVariable Long id, Model model) {
        Blog blog = blogsService.getBlog(id);
        model.addAttribute("blog", blog);
        return "blogpage";
    }

    @GetMapping("/blog/{id}")
    public String getUpdateBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogsService.getBlog(id);
        model.addAttribute("blog", blog);
        return "blogform";
    }

    @GetMapping("/blog")
    public String createBlogForm(Model model){
        model.addAttribute("blog", new Blog());
        return "blogform";
    }

    @GetMapping("/blog/{id}/delete")
    public String deleteBlog(@PathVariable Long id, Model model) {
        List<Blog> blogs = blogsService.deleteBlog(id);
        model.addAttribute("blogs", blogs);
        return "bloglist";
    }

    @PostMapping("/blog")
    public String submitBlog(@ModelAttribute Blog blog, Model model) {
        Blog newBlog = blogsService.createOrUpdateProduct(blog);
        model.addAttribute("blog", newBlog);
        return "blogpage";
    }


    @GetMapping
    public String getAllBlogs(Model model) {
        List<Blog> blogs = blogsService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "bloglist";
    }

    }
