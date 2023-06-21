package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long> {

	@Query
	Skill findByCode(String code);

}
