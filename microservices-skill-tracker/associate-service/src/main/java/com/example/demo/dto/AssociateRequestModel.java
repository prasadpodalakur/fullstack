package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AssociateRequestModel {
	
	private Long id;
	private String associateId;
	private String  name;
	private String email;
	private String mobilenumber;
	private List<SkillDto> skills;

}
