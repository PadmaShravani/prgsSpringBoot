package com.prgs.PublicRaiseGovernamentSolve.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.Users;
import com.prgs.PublicRaiseGovernamentSolve.repository.DepartmentRepository;
@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository drepo;
	
	public List<Department> listAll(){
		
		return drepo.findAll();
	}
	
}
