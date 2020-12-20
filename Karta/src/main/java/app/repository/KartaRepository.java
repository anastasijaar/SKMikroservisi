package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Karta;

@Repository
public interface KartaRepository extends JpaRepository<Karta, Integer>{

}
