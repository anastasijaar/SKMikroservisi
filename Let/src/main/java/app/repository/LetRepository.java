package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Integer>{

	//Selekcija leta po imenu aviona
	@Query("select l from Let l where l.avion.nazivAviona like :ime")
	Let selectFlightByPlaneName(String ime);
	
	Let findByIdLeta(int idLeta);
}
