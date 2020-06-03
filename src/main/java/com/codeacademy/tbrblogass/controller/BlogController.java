package com.codeacademy.tbrblogass.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private List<Blog> blogs;

    public BlogController() { this.blogs = buildBlogs(); }

    @GetMapping("/{id}")
    public String getBlog(@PathVariable Long id, Model model) {
        Blog blog = blogs.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BlogNotFoundException("Blog width id: " + id + "was not found"));

        model.addAttribute("blog", blog);
        return "blogpage";
    }

    @GetMapping("/blog/{id}")
    public String updateBlog(@PathVariable Long id, Model model) {
        Blog blog = blogs.stream()
                .filter(b-> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BlogNotFoundException("Blog width id: " + id + "was not found"));
        model.addAttribute("blog", blog);
        return "blogform";
    }

    @GetMapping("/blog")
    public String createBlog(Model model){
        model.addAttribute("blog", new Blog());
        return "blogform";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteBlog(@PathVariable Long id, Model model) {
        blogs = blogs.stream()
                .filter(b -> !b.getId().equals(id))
                .collect(Collectors.toList());
        model.addAttribute("blogs", blogs);
        return "bloglist";
    }

    @PostMapping("/blog")
    public String submitBlog(@ModelAttribute Blog blog) {
        List<Blog> newBlogs = blogs.stream()
                .filter(b -> !b.getId().equals(blog.getId()))
                .collect(Collectors.toList());
        newBlogs.add(blog);
        blogs = newBlogs;

        return "blogpage";
    }


    @GetMapping
    public String getAllBlogs(Model model) {
        model.addAttribute("blogs", blogs);
        return "bloglist";
    }

    private List<Blog> buildBlogs() {
        Blog blog1 = new Blog();
        blog1.setId(1L);
        blog1.setTitle("Maistas");
        blog1.setDescription("Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolores provident distinctio " +
                "ab cum molestiae quasi impedit voluptatem ad possimus pariatur. Nam tenetur voluptas perspiciatis " +
                "impedit. Dignissimos voluptates consectetur nemo atque aliquam minus, laboriosam eius et accusantium " +
                "nobis non nulla sit amet ducimus deleniti dolores accusamus, quia ab officiis, quo minima! Ea fugit " +
                "quae voluptatibus magnam eos iure error? Minima, alias sequi ipsa dolorum ratione beatae, ab," +
                " excepturi dolor pariatur provident odit repellat tempora architecto. Quibusdam ab distinctio rem " +
                "quae, laboriosam qui placeat! Nisi dolores amet vero officiis repellat, excepturi modi aliquam quasi" +
                " voluptate, unde, dolorum ipsam eaque! Iste, consequatur aliquid. ");

        Blog blog2 = new Blog();
        blog2.setId(2L);
        blog2.setTitle("Kelionės");
        blog2.setDescription("Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolores provident distinctio " +
                "ab cum molestiae quasi impedit voluptatem ad possimus pariatur. Nam tenetur voluptas perspiciatis " +
                "impedit. Dignissimos voluptates consectetur nemo atque aliquam minus, laboriosam eius et accusantium " +
                "nobis non nulla sit amet ducimus deleniti dolores accusamus, quia ab officiis, quo minima! Ea fugit " +
                "quae voluptatibus magnam eos iure error? Minima, alias sequi ipsa dolorum ratione beatae, ab," +
                " excepturi dolor pariatur provident odit repellat tempora architecto. Quibusdam ab distinctio rem " +
                "quae, laboriosam qui placeat! Nisi dolores amet vero officiis repellat, excepturi modi aliquam quasi" +
                " voluptate, unde, dolorum ipsam eaque! Iste, consequatur aliquid. ");

        Blog blog3 = new Blog();
        blog3.setId(3L);
        blog3.setTitle("Muzika");
        blog3.setDescription("Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolores provident distinctio " +
                "ab cum molestiae quasi impedit voluptatem ad possimus pariatur. Nam tenetur voluptas perspiciatis " +
                "impedit. Dignissimos voluptates consectetur nemo atque aliquam minus, laboriosam eius et accusantium " +
                "nobis non nulla sit amet ducimus deleniti dolores accusamus, quia ab officiis, quo minima! Ea fugit " +
                "quae voluptatibus magnam eos iure error? Minima, alias sequi ipsa dolorum ratione beatae, ab," +
                " excepturi dolor pariatur provident odit repellat tempora architecto. Quibusdam ab distinctio rem " +
                "quae, laboriosam qui placeat! Nisi dolores amet vero officiis repellat, excepturi modi aliquam quasi" +
                " voluptate, unde, dolorum ipsam eaque! Iste, consequatur aliquid. ");

        List<Blog> blogs = new ArrayList<>();
        blogs.add(blog1);
        blogs.add(blog2);
        blogs.add(blog3);

        return blogs;


    }
}
