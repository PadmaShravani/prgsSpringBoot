package com.prgs.PublicRaiseGovernamentSolve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;

public interface TicketDetailsRepository extends JpaRepository<TicketDetails,Integer> {
	 
	List<TicketDetails> findByUserIdAndTicketType(int UserId,String ticketType);
}
