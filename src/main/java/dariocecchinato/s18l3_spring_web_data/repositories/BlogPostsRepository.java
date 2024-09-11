package dariocecchinato.s18l3_spring_web_data.repositories;

import dariocecchinato.s18l3_spring_web_data.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, UUID> {
    Optional<BlogPost> findById (UUID blogPostId);

    BlogPost findByTitolo (String titolo);


}
