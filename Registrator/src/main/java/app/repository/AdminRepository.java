package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUsername(String username);
}
