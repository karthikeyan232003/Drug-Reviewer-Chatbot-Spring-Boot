package com.example.pharmapulse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.pharmapulse.models.User_Registration;

@Repository
public interface RegisterRepository extends JpaRepository<User_Registration,String> {
	User_Registration findByEmail(String email);
}
