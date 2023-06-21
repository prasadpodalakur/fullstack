package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AssociateEntity;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

	List<AssociateEntity> findByAssociateId(String associateId);

	List<AssociateEntity> findByEmail(String email);

	List<AssociateEntity> findBySkillCode(String code);
	
	List<AssociateEntity> findByAssociateIdAndSkillCode(String associateId, String code);

	List<AssociateEntity> findByName(String name);

}
