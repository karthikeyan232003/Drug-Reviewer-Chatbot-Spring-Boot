package com.example.pharmapulse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmapulse.models.Feedback;

@Repository
public interface ReviewRepository extends JpaRepository<Feedback, String> {

}
