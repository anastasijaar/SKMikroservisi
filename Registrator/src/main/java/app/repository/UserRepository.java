package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	User findByIdUser(int idUser);

	boolean existsByEmail(String email);
	
	//Selekcija usera koji idu na prosledjeni let
	@Query("select u from User u join u.letovi l where l.idLeta like :idLeta")
	List<User> findAllUsersThatGoOnFlight(long idLeta);
	
	//vraca sve kartice za odredjenog user-a
	@Query("select ka.kartica.brKartice from User_Kartica ka where ka.user like :user")
	List<Long> selectAllKarticeForUser(User user);
}