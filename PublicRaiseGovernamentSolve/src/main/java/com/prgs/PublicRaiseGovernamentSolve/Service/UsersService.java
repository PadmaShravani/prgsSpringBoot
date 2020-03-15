package com.prgs.PublicRaiseGovernamentSolve.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.Users;
import com.prgs.PublicRaiseGovernamentSolve.repository.UsersRepository;

@Service

public class UsersService {
	@Autowired
	private UsersRepository repo;
	

	public String loginWithRole(Users users) {
				
		if (users.getRoleId() == 1) {
			return "userViewPage";
		}
		else if (users.getRoleId() == 2) {
				System.out.println("Admin login");
				return "adminViewPage";
			} else if (users.getRoleId() == 3) {
				System.out.println("department login");
				
				return "departmentViewPage1";
			}
		return "visitor";
	}
	
	
	public List<Users> listAll(){
		
		return repo.findAll();
	}
	
	
	public void save(Users users) {
		repo.save(users);
	}
	
	
	public Users get(int userId) {
		return repo.findById(userId).get();
		
	}
	
	public void delete(int userId) {
		
		repo.deleteById(userId);
	}
	
	public Users findByEmail(String email) {
		
	        return repo.findByEmail(email);	 
	        
	}
	
}
