package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.AssociateDto;
import com.example.demo.dto.AssociateResponseModel;
import com.example.demo.dto.SkillDto;
import com.example.demo.entity.AssociateEntity;
import com.example.demo.repos.AssociateRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Data
@Slf4j
public class AssociateServiceImpl implements AssociateService {
	private final ModelMapper modelMapper;
	private final AssociateRepository associateRepository;
	
	
	private RestTemplate restTemplate;


	/*
	 * public AssociateResponseModel createAssociate(AssociateDto associateDto) {
	 * String skillServiceUR="http://localhost:8082/skill-service/skills/"; String
	 * skillService_create_URL="http://localhost:8082/skill-service/skills/create/";
	 * 
	 * AssociateEntity associateEntity=modelMapper.map(associateDto,
	 * AssociateEntity.class);
	 * 
	 * associateEntity.setAssociateId(associateDto.getAssociateId());
	 * associateEntity.setName(associateDto.getName());
	 * associateEntity.setEmail(associateDto.getEmail());
	 * associateEntity.setMobilenumber(associateDto.getMobilenumber()); for(SkillDto
	 * s: associateDto.getSkills()) { AssociateEntity associate =
	 * associateRepository.findByAssociateIdAndSkillCode(associateEntity.
	 * getAssociateId(), s.getCode()); if(associate == null) {
	 * 
	 * ResponseEntity<SkillDto> byCode = null; try { byCode =
	 * restTemplate.getForEntity(skillServiceUR+ s.getCode(), SkillDto.class); }
	 * catch (HttpClientErrorException e) { }
	 * 
	 * 
	 * if(byCode == null) { ResponseEntity<SkillDto> response =
	 * restTemplate.postForEntity( skillService_create_URL, s , SkillDto.class ); }
	 * 
	 * associateEntity.setSkillCode(s.getCode());
	 * associateRepository.save(associateEntity); }
	 * 
	 * }
	 * 
	 * AssociateResponseModel responseModel=modelMapper.map(associateDto,
	 * AssociateResponseModel.class); return responseModel; }
	 */
	

	@Override
	public AssociateResponseModel createAssociate(AssociateDto associateDto) {
		String skillServiceUR="http://localhost:8082/skill-service/skills/";
		String skillService_create_URL="http://localhost:8082/skill-service/skills/create/";
		
		for(SkillDto s: associateDto.getSkills()) {
			AssociateEntity associateEntity=modelMapper.map(associateDto, AssociateEntity.class);
			associateEntity.setAssociateId(associateDto.getAssociateId());
			associateEntity.setName(associateDto.getName());
			associateEntity.setEmail(associateDto.getEmail());
			associateEntity.setMobilenumber(associateDto.getMobilenumber());

			List<AssociateEntity> associateL =  associateRepository.findByAssociateIdAndSkillCode(associateEntity.getAssociateId(), s.getCode());
			if(associateL == null || associateL.isEmpty()) {
				
				ResponseEntity<SkillDto> byCode = null;
				try {
					byCode = restTemplate.getForEntity(skillServiceUR+ s.getCode(), SkillDto.class);
				  } catch (HttpClientErrorException e) {
				  }
				
				if(byCode == null) {
					ResponseEntity<SkillDto> response = restTemplate.postForEntity( skillService_create_URL, s , SkillDto.class );
				}
				
				associateEntity.setSkillCode(s.getCode());
				associateRepository.save(associateEntity);	
			}
			
		}
		
		AssociateResponseModel responseModel=modelMapper.map(associateDto, AssociateResponseModel.class);
		return responseModel;
	}
	
	@Override
	public List<AssociateResponseModel> createAssociates(List<AssociateDto> associateDtoList) {
		List<AssociateResponseModel> associateResponseModels = new ArrayList<>();
		for(AssociateDto associateDto: associateDtoList) {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			AssociateEntity associateEntity=modelMapper.map(associateDto, AssociateEntity.class);
			
			associateEntity.setAssociateId(UUID.randomUUID().toString());
			
			associateRepository.save(associateEntity);
			
			AssociateResponseModel responseModel=modelMapper.map(associateEntity, AssociateResponseModel.class);
			associateResponseModels.add(responseModel);
		}
		
		return associateResponseModels;
	}

	@Override
	public List<AssociateResponseModel> getAllAssociates() {
		String skillServiceUR="http://localhost:8082/skill-service/skills/";
		List<AssociateResponseModel> responseList = new ArrayList<>();
		List<AssociateEntity> associateEntityList = associateRepository.findAll();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<SkillDto> skillList = new ArrayList<>();
		for(AssociateEntity entity: associateEntityList)
		{
			/*
			 * SkillDto skillDto = new SkillDto(); skillDto.setCode(entity.getSkillCode());
			 * ResponseEntity<SkillDto> skillByCode = null; try { skillByCode =
			 * restTemplate.getForEntity(skillServiceUR+ entity.getSkillCode(),
			 * SkillDto.class); } catch (HttpClientErrorException e) { }
			 * skillDto.setDescription(skillByCode.getBody().getDescription());
			 * 
			 * skillList.add(skillDto);
			 */
			
			
			AssociateResponseModel responseModel=modelMapper.map(entity, AssociateResponseModel.class);
			//responseModel.setSkills(skillList);
			responseList.add(responseModel);
		}
		
		return responseList;
	}


	@Override
	public AssociateResponseModel getAssociateById(int id) {
		
		Optional<AssociateEntity> associateEntity = associateRepository.findById(Long.valueOf(id));
		System.out.println("associate entity:::"+associateEntity);
		if(associateEntity.isPresent()) {
			AssociateResponseModel associateResponseModel = new AssociateResponseModel();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			modelMapper.map(associateEntity.get(), associateResponseModel);
			
			return associateResponseModel;
		}
		
		return null;
	}

	@Override
	public List<AssociateResponseModel> deleteByAssociateId(String associateId) {
		// TODO Auto-generated method stub
		List<AssociateResponseModel> responseList = new ArrayList<>();
		List<AssociateEntity> list = associateRepository.findByAssociateId(associateId);
		if(list ==null || list.isEmpty()) {
			return null;
		}
		
		for(AssociateEntity associateEntity: list) {
			associateRepository.delete(associateEntity);
			responseList.add(modelMapper.map(associateEntity, AssociateResponseModel.class));
		}
		
		return responseList;
		
	}

	@Override
	public void deleteAllAssociates() {
		// TODO Auto-generated method stub
		associateRepository.deleteAll();
		
	}
	
	@Override
	public List<AssociateResponseModel> getAssociateByAssociateId(String associateId) {
		List<AssociateEntity> associates = associateRepository.findByAssociateId(associateId);
		List<AssociateResponseModel> associateResponseModels = new ArrayList<>();
		for(AssociateEntity associateEntity:associates) {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			AssociateResponseModel associateResponseModel = modelMapper.map(associateEntity, AssociateResponseModel.class);
			associateResponseModels.add(associateResponseModel);
		}
		
		return associateResponseModels;
	}
	
	@Override
	public List<AssociateResponseModel> getAssociateByName(String name) {
		List<AssociateEntity> associates = associateRepository.findByName(name);
		List<AssociateResponseModel> associateResponseModels = new ArrayList<>();
		for(AssociateEntity associateEntity:associates) {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			AssociateResponseModel associateResponseModel = modelMapper.map(associateEntity, AssociateResponseModel.class);
			associateResponseModels.add(associateResponseModel);
		}
		
		return associateResponseModels;
	}
	
	@Override
	public List<AssociateResponseModel> getAssociateByEmail(String email) {
		List<AssociateEntity> associates = associateRepository.findByEmail(email);
		List<AssociateResponseModel> associateResponseModels = new ArrayList<>();
		for(AssociateEntity associateEntity:associates) {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			AssociateResponseModel associateResponseModel = modelMapper.map(associateEntity, AssociateResponseModel.class);
			associateResponseModels.add(associateResponseModel);
		}
		
		return associateResponseModels;
	}

	@Override
	public AssociateResponseModel updateByAssociateId(String associateId, AssociateDto associateDto) {

		String skillServiceUR="http://localhost:8082/skill-service/skills/";
		String skillService_create_URL="http://localhost:8082/skill-service/skills/create/";
		
		
		List<AssociateEntity> associates = associateRepository.findByAssociateId(associateId);
		if(associates ==null || associates.isEmpty()) {
			return null;
		}
		
		if(associates != null) {
			for(AssociateEntity associateEntity: associates) {
				associateEntity.setAssociateId(associateDto.getAssociateId());
				associateEntity.setName(associateDto.getName());
				associateEntity.setEmail(associateDto.getEmail());
				associateEntity.setMobilenumber(associateDto.getMobilenumber());
				
				boolean canDeleteSkill=true;
				
				for(SkillDto s: associateDto.getSkills()) {
					if(canDeleteSkill && associateEntity.getSkillCode().equals(s.getCode())) {
						canDeleteSkill = false;
						break;
					}
					
					List<AssociateEntity> associateL =  associateRepository.findByAssociateIdAndSkillCode(associateDto.getAssociateId(), s.getCode());
					if(associateL == null || associateL.isEmpty()) {
						AssociateEntity newEntity=new AssociateEntity();
						newEntity.setAssociateId(associateDto.getAssociateId());
						newEntity.setName(associateDto.getName());
						newEntity.setEmail(associateDto.getEmail());
						newEntity.setMobilenumber(associateDto.getMobilenumber());
						
						ResponseEntity<SkillDto> byCode = null;
						try {
							byCode = restTemplate.getForEntity(skillServiceUR+ s.getCode(), SkillDto.class);
						  } catch (HttpClientErrorException e) {
						  }
						
						if(byCode == null) {
							ResponseEntity<SkillDto> response = restTemplate.postForEntity( skillService_create_URL, s , SkillDto.class );
						}
						newEntity.setSkillCode(s.getCode());
						associateRepository.save(newEntity);
						canDeleteSkill = false;
					} 
					
				}
				
				if(canDeleteSkill && !associateDto.getSkills().isEmpty()) {
					associateRepository.delete(associateEntity);
				}
				
			}
		}
		
		AssociateResponseModel responseModel=modelMapper.map(associateDto, AssociateResponseModel.class);
		return responseModel;
	
	}


}
