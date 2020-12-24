package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Karta;

@Repository
public interface KartaRepository extends JpaRepository<Karta, Integer>{

	//Selekcija karte po idLet-a
	@Query("select k from Karta k where k.idLeta like :idLeta")
	Karta selectCardByPlaneID(int idLeta);
	
	boolean existsByIdLeta(int idLeta);
	
	//Selekcija svih idUsera koji su kupili kartu za odredjeni let
	@Query("select k.idUser from Karta k where k.idLeta like :idLeta")
	List<Integer> findByIdUseraByIdLeta(int idLeta);
}
