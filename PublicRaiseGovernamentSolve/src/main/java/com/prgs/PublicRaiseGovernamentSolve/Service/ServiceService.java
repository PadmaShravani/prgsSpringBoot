package com.prgs.PublicRaiseGovernamentSolve.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgs.PublicRaiseGovernamentSolve.model.ServiceModel;
import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;
import com.prgs.PublicRaiseGovernamentSolve.repository.ServiceRepository;
@Service
public class ServiceService {
	@Autowired
	private ServiceRepository srepo;
	public void save(ServiceModel service) {
		srepo.save(service);
	
	}

}
