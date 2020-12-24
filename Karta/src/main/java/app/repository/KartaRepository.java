package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Karta;

@Repository
public interface KartaRepository extends JpaRepository<Karta, Integer>{

	//Selekcija karte po idLet-a
	@Query("select k from Karta k where k.idLeta like :idLeta")
	Karta selectCardByPlaneID(int idLeta);
	
}
