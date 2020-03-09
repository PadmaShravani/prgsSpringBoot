package com.prgs.PublicRaiseGovernamentSolve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prgs.PublicRaiseGovernamentSolve.Service.TicketService;
import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;

@Controller
public class TicketController {
	@Autowired
	private TicketService tservice;
	
	@RequestMapping("/complaint_page")
	public String viewComplaintPage(@ModelAttribute("TicketDetails") TicketDetails ticket) {
		ticket.setTicketType("complaint");
		return "cticket";
	}
	@RequestMapping(value="/saveComplaint", method=RequestMethod.POST)
	public String saveTicket(@ModelAttribute("TicketDetails") TicketDetails ticket) {
		ticket.setTicketType("complaint");
		System.out.println(ticket.getUserId());
		tservice.save(ticket);			
		return "cticket";
		
	}
	@RequestMapping("/list_complaints")
	public String viewAllCompalaintsPage(Model model) {
		List<TicketDetails> listTickets=tservice.listAll();
		System.out.println(listTickets.size()+"  size");
		model.addAttribute("listTicket", listTickets);
		return "cticket";
	}
	

}
