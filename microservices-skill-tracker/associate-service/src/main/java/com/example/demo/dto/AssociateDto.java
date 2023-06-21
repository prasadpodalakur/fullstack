package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AssociateDto {
	
	private Long id;
	private String associateId;
	private String  name;
	private String email;
	private String mobilenumber;
	private String skillCode;
	private List<SkillDto> skills;

	
}
