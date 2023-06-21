package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AssociateDto;
import com.example.demo.dto.AssociateResponseModel;

public interface AssociateService {

	public AssociateResponseModel createAssociate(AssociateDto associateDto);
	
	public List<AssociateResponseModel> createAssociates(List<AssociateDto> associateDto);
	
	public List<AssociateResponseModel> getAllAssociates();
	
	 public AssociateResponseModel getAssociateById(int id);

	 public List<AssociateResponseModel> getAssociateByAssociateId(String userId);
	 public List<AssociateResponseModel> getAssociateByName(String name);
	 public List<AssociateResponseModel> getAssociateByEmail(String email);
	 
	 public List<AssociateResponseModel> deleteByAssociateId(String associateId);
	 void deleteAllAssociates();
	 AssociateResponseModel updateByAssociateId(String associateId, AssociateDto associateDto);


}
