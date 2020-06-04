package com.codeacademy.tbrblogass.repositories;

import com.codeacademy.tbrblogass.controller.Blog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BlogsDao {
    private List<Blog> blogs;

    public BlogsDao() {
        this.blogs = buildBlogs();
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

    public Optional<Blog> getBlog(Long id) {

        return blogs.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    public boolean deleteBlog(Long id) {
        List<Blog> newList = blogs.stream()
                .filter(b -> !b.getId().equals(id))
                .collect(Collectors.toList());

        boolean deleted = newList.size() != blogs.size();
        blogs = newList;

        return deleted;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public Blog updateBlog(Blog blog) {
        getBlog(blog.getId()).ifPresent(existingBlog -> {
            existingBlog.setDescription(blog.getDescription());
            existingBlog.setTitle(blog.getTitle());
        });

        return blog;
    }

    public Blog createBlog(Blog blog) {
        Long maxId = blogs.stream()
                .mapToLong(Blog::getId)
                .max().orElse(0L);

        blog.setId(maxId + 1);
        blogs.add(blog);

        return blog;
    }
}
