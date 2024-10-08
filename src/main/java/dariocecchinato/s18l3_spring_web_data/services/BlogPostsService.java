package dariocecchinato.s18l3_spring_web_data.services;

import dariocecchinato.s18l3_spring_web_data.entities.Autore;
import dariocecchinato.s18l3_spring_web_data.entities.BlogPost;
import dariocecchinato.s18l3_spring_web_data.entities.PayloadBodyBlogPost;
import dariocecchinato.s18l3_spring_web_data.exceptions.NotFoundException;
import dariocecchinato.s18l3_spring_web_data.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;

    @Autowired
    private AutoriService autoriService;

    public Page<BlogPost> findAllBlogs (int page, int size, String sortBy){
        if(page > 10) page = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostsRepository.findAll(pageable);
    }

    public BlogPost saveBlogPost(PayloadBodyBlogPost body){

        Autore autore = autoriService.findAutoreById(UUID.fromString(body.getAutoreId()));
        BlogPost blogPost = new BlogPost(body.getCategoria(),body.getTitolo(),body.getCover(),body.getContenuto(), body.getTempoDiLettura());
        blogPost.setAutore(autore);
        blogPost.setCover("https://fastly.picsum.photos/id/848/200/300.jpg?hmac=cNClhUSP4IM6ZT6RTqdeCOLWYEJYBNXaqdflgf_EqD8");

        return blogPostsRepository.save(blogPost);
    }

    public BlogPost findById(UUID blogPostId){
        return this.blogPostsRepository.findById(blogPostId).orElseThrow(()->new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate (UUID blogPostId, PayloadBodyBlogPost body){
        BlogPost found = this.blogPostsRepository.findById(blogPostId).orElseThrow(()->new NotFoundException(blogPostId));
        found.setTitolo(body.getTitolo());
        found.setCategoria(body.getCategoria());
        found.setCover("https://fastly.picsum.photos/id/848/200/300.jpg?hmac=cNClhUSP4IM6ZT6RTqdeCOLWYEJYBNXaqdflgf_EqD8");
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());
        return found;
    }

    public void findByIdAndDelete (UUID blogPostId){
       BlogPost found= this.blogPostsRepository.findById(blogPostId).orElseThrow(()->new NotFoundException(blogPostId));
       blogPostsRepository.delete(found);
    }
}
