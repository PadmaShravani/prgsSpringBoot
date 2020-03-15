package com.prgs.PublicRaiseGovernamentSolve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.ServiceModel;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
