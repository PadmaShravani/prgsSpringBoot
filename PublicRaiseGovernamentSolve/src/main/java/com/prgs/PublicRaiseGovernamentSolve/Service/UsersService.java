package com.prgs.PublicRaiseGovernamentSolve.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgs.PublicRaiseGovernamentSolve.model.Users;
import com.prgs.PublicRaiseGovernamentSolve.repository.UsersRepository;

@Service

public class UsersService {
	@Autowired
	private UsersRepository repo;
	
	
	public List<Users> listAll(){
		
		return repo.findAll();
	}
	
	
	public void save(Users users) {
		repo.save(users);
	}
	
	
	public Users get(int id) {
		return repo.findById(id).get();
		
	}
	
	public void delete(int id) {
		
		repo.deleteById(id);
	}
	
	public Users findByEmail(String email) {
		
	        return repo.findByEmail(email);	 
	        
	}
	
}
