package com.example.pharmapulse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmapulse.models.Login_Details;

@Repository
public interface UsersRepository extends JpaRepository<Login_Details,String> {

	Optional<Login_Details> findByEmail(String username);

}
