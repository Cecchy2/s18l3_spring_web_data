package dariocecchinato.s18l3_spring_web_data.repositories;

import dariocecchinato.s18l3_spring_web_data.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutoriRepository extends JpaRepository<Autore, UUID> {

    boolean existsByEmail(String email);

    Optional<Autore> findByEmail(String email);
}
