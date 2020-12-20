package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.User_Kartica;

@Repository
public interface User_KarticaRepository extends JpaRepository<User_Kartica, Integer> {

}
