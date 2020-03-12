package com.prgs.PublicRaiseGovernamentSolve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgs.PublicRaiseGovernamentSolve.model.ServiceModel;

public interface ServiceRepository extends JpaRepository<ServiceModel,Integer> {
	 
	
}
