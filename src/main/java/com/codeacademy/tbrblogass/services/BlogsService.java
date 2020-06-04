package com.codeacademy.tbrblogass.services;

import com.codeacademy.tbrblogass.controller.Blog;
import com.codeacademy.tbrblogass.controller.BlogNotFoundException;
import com.codeacademy.tbrblogass.repositories.BlogsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogsService {

    private BlogsDao blogsDao;

    public BlogsService(BlogsDao blogsDao) {
        this.blogsDao = blogsDao;
    }

    public Blog getBlog(Long id) {
        return  blogsDao.getBlog(id)
                .orElseThrow(() -> new BlogNotFoundException("Product with id: " + id + " was not found"));
    }

    public Blog createOrUpdateProduct(Blog blog) {

        if (blog.getId() != null) {
            return blogsDao.updateBlog(blog);
        } else {
            return blogsDao.createBlog(blog);
        }
    }

    public List<Blog> deleteBlog(Long id) {
        blogsDao.deleteBlog(id);
        return blogsDao.getBlogs();
    }

    public List<Blog> getAllBlogs(){
        return blogsDao.getBlogs();
    }
}
