package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {
	@Query
	List<Tutorial> findByPublished(boolean published);

	@Query
	List<Tutorial> findByTitleContaining(String title);
}
