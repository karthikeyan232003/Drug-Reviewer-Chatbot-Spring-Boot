package com.example.pharmapulse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmapulse.models.FeedbackTracker;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackTracker, String> {

}
