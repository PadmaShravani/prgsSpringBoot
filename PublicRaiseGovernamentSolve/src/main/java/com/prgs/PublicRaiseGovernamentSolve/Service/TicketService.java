package com.prgs.PublicRaiseGovernamentSolve.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;
import com.prgs.PublicRaiseGovernamentSolve.repository.TicketDetailsRepository;

@Service
public class TicketService {
	@Autowired
	private TicketDetailsRepository trepo;

	public List<TicketDetails> listAll() {

		return trepo.findAll();
	}
	public List<TicketDetails> getByStatus(String status){
		return trepo.findByStatus(status);
	}

	public void save(TicketDetails ticket) {
		trepo.save(ticket);
	}

	public TicketDetails get(int id) {
		return trepo.findById(id).get();

	}
	public List<TicketDetails> getUserTickets(int UserId,String ticketType) {
		return trepo.findByUserIdAndTicketType(UserId,ticketType);
		
	}
	public List<TicketDetails> getAllTickets(String ticketType){
		return trepo.findByTicketType(ticketType);
	}

}
