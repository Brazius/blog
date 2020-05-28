package com.codeacademy.tbrblogass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

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
                .orElseThrow(() -> new BlogNotFoundExeption("Blog width id: " + id + "was not found"));

        model.addAttribute("blog", blog);
        return "blogpage";
    }

    public String getAllBlogs(Model model) {
        model.addAttribute("blogs", blogs);
        return "bloglist";
    }

    private List<Blog> buildBlogs() {
        Blog blog1 = new Blog();
        blog1.setId(1L);
        blog1.setTitle("Maistas");
        blog1.setDescription("Maistas yra bet kuri substancija, vartojama gyvų organizmų" +
                " kaip pagrindinis energijos ir maistingų (mitybinių) medžiagų šaltinis ir" +
                " dažniausiai yra augalinės arba gyvulinės kilmės. ");

        Blog blog2 = new Blog();
        blog2.setId(2L);
        blog2.setTitle("Kelionės");
        blog2.setDescription("kelionės siekiant pailsėti, patirti naujų įspūdžių ar pasilinksminti." +
                " Nors pastaruoju metu terminas labiau reiškia išvykimą iš savo gyvenamosios ar " +
                "darbinės aplinkos ribų. ");

        Blog blog3 = new Blog();
        blog3.setId(3L);
        blog3.setTitle("Muzika");
        blog3.setDescription("Tai sudėtingos formos kūriniai, išreiškiami per natūraliai išgaunamo " +
                "dirgiklio, dažniausiai garso, kombinacijų ir modelių konstrukciją. Muzikos funkcijos:" +
                " estetinė, meninė, komunikacinė, pramoginė, apeiginė. Nuo kultūros ir socialinio " +
                "konteksto priklauso, kaip mes skirstome muziką.");

        return Arrays.asList(blog1, blog2, blog3);


    }
}
