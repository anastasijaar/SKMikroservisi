package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Integer>{

	//Selekcija leta po imenu aviona
	@Query("select l from Let l where l.avion.nazivAviona like :ime")
	Let selectFlightByPlaneName(String ime);
	
	//Selekcija letova gde kapacitet putnika u avionu nije pun
	@Query("select l from Let l where l.avion.kapacitet not in l.avion.trenutnoPutnika")
	List<Let> selectAllFlightWithFreeSpace(); 
	
	Let findByIdLeta(int idLeta);
}
