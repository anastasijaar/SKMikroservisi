package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Avion;
import app.entities.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Integer>{

	//Selekcija leta po imenu aviona
	@Query("select l from Let l where l.avion.nazivAviona like :ime")
	Let selectFlightByPlaneName(String ime);
	
	//Selekcija letova gde kapacitet putnika u avionu nije pun
	@Query("select l from Let l where l.avion.kapacitet not in l.avion.trenutnoPutnika")
	List<Let> selectAllFlightWithFreeSpace(); 
	
	//pretraga letova po svim svojim parametrima
	@Query("select l from Let l where l.pocetnaDestinacija like :pocetnaDestinacija or l.krajnjaDestinacija like :krajnjaDestinacija or "
			+ "l.duzinaLeta like :duzinaLeta or l.cena like :cena or l.isCanceled like :isCanceled")
	List<Let> searchLetByParameters(String pocetnaDestinacija, String krajnjaDestinacija, int duzinaLeta, int cena, Boolean isCanceled);
	
	Let findByIdLeta(int idLeta);
	
	//provera da li avion pripada nekom letu
	@Query("select l from Let l where l.avion like :avion")
	List<Let> selectFlightWithPlane(Avion avion);
}
