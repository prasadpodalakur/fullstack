package com.example.demo.controllers;



import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AssociateDto;
import com.example.demo.dto.AssociateRequestModel;
import com.example.demo.dto.AssociateResponseModel;
import com.example.demo.dto.ErrorModel;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.MyCustomException;
import com.example.demo.repos.AuthRoleRepository;
import com.example.demo.repos.AuthUserRepository;
import com.example.demo.service.AssociateService;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/associates")
@AllArgsConstructor
@Slf4j
public class AssociateRestController {
	
	private final ModelMapper modelMapper;
	
	private final AssociateService associateService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private AuthRoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	
    @PostMapping("/api/auth/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto,@RequestParam("roleName") String roleName){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName(roleName).get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

	@ExceptionHandler(value = NumberFormatException.class)	
	public ResponseEntity<ErrorModel> handleException(NumberFormatException e)
	{
		ErrorModel errorModel=new ErrorModel(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
	}

	@ExceptionHandler(value = MyCustomException.class)
	public ResponseEntity<ErrorModel> handleIdNotFoundException(MyCustomException e) {
		ErrorModel errorModel = new ErrorModel(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);

	}
	
	@PostMapping("create")
	public ResponseEntity<?> createAssociate(@RequestBody AssociateRequestModel requestModel)
	{
		log.info("inside createUser");

		AssociateDto associateDto=modelMapper.map(requestModel, AssociateDto.class);
		
		log.debug("calling service");
		
		AssociateResponseModel responseModel=associateService.createAssociate(associateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
			 
	}
	
	@GetMapping
	public ResponseEntity<?> getAllAssociates(){
		List<AssociateResponseModel> list = associateService.getAllAssociates();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{associateId}")
	public ResponseEntity<?> getAssociateByAssociateId(@PathVariable("associateId") String associateId) {
		
		List<AssociateResponseModel> associateResponseModels = associateService.getAssociateByAssociateId(associateId);
		
		if(associateResponseModels == null || associateResponseModels.isEmpty()) {
			throw new MyCustomException("Associate With associateId " + associateId + " not found");
		}
		
		return ResponseEntity.ok().body(associateResponseModels);
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<?> getByAssociateId(@PathVariable("name") String name) {
		
		List<AssociateResponseModel> associateResponseModels = associateService.getAssociateByName(name);
		
		if(associateResponseModels == null || associateResponseModels.isEmpty()) {
			throw new MyCustomException("Associate With name " + name + " not found");
		}
		
		return ResponseEntity.ok().body(associateResponseModels);
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
		
		List<AssociateResponseModel> associateResponseModels = associateService.getAssociateByEmail(email);
		
		if(associateResponseModels == null || associateResponseModels.isEmpty()) {
			throw new MyCustomException("Associate With email " + email + " not found");
		}
		
		return ResponseEntity.ok().body(associateResponseModels);
	}
	
	
	@PutMapping("update/{associateId}")
	public ResponseEntity<?> updateByAssociateId(@PathVariable("associateId") String associateId, @RequestBody AssociateRequestModel requestModel)
	{
		log.info("inside updateAssociate");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AssociateDto associateDto=modelMapper.map(requestModel, AssociateDto.class);
		
		log.debug("calling updateAssociate");
		
		AssociateResponseModel userResponseModel = associateService.updateByAssociateId(associateId, associateDto);
		if(userResponseModel == null) {
			throw new MyCustomException("No Associate found for the given associate Id:"+associateId);
		}
		
		return ResponseEntity.ok().body(userResponseModel);
			 
	}
	
	
	@DeleteMapping("delete/{associateId}")
	public ResponseEntity<?> deleteByAssociateId(@PathVariable("associateId") String associateId){
		
		List<AssociateResponseModel> associateResponseModels = associateService.deleteByAssociateId(associateId);
		
		if(associateResponseModels == null || associateResponseModels.isEmpty()) {
			throw new MyCustomException(("No associate found for the given user Id:"+associateId));
		}
		
		return ResponseEntity.ok().body("user has been deleted successfully");
	}
	
	
	@DeleteMapping("delete/all")
	public ResponseEntity<?> deleteAllAssociates(){
		associateService.deleteAllAssociates();
		return ResponseEntity.ok().body("All users have been deleted successfully");
	}

}
