package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long>{

	Avion findByIdAviona(long idAviona);
	
	//trazimo broj putnika za odredjeni avion
	@Query("select a.kapacitet-a.trenutnoPutnika from Avion a where a.idAviona like :idAviona")
	int getKapacitetPutnika(long idAviona);
}
