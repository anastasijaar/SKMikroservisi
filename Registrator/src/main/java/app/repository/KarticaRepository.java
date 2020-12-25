package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Kartica;

@Repository
public interface KarticaRepository extends JpaRepository<Kartica, Long> {

}
