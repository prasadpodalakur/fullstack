package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Skill;
import com.example.demo.repos.SkillRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/skills")
@Slf4j
public class SkillRestController {

	SkillRepo repo;

	public SkillRestController(SkillRepo repo) {
		super();
		this.repo = repo;
	}

	@PostMapping("/create")
	public Skill create(@RequestBody Skill skill) {

		return repo.save(skill);

	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getSkill(@PathVariable("code") String code) {
		log.info("getSkill::::"+code);
		Skill skill = repo.findByCode(code);
		log.info("skill::::"+skill);
		if(skill == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(skill);
		}
		
		return ResponseEntity.ok().body(skill);

	}
}
