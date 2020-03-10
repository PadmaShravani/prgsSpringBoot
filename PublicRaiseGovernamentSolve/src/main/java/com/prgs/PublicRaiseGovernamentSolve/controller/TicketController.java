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
import com.prgs.PublicRaiseGovernamentSolve.model.Users;

@Controller
public class TicketController {
	@Autowired
	private TicketService tservice;
	
	@RequestMapping("/complaint_page")
	public String viewComplaintPage(@ModelAttribute("TicketDetails") TicketDetails ticket) {
		ticket.setTicketType("complaint");
		System.out.println("complaint page");
		return "cticket";
	}
//	@RequestMapping(value="/saveComplaint", method=RequestMethod.POST)
//	public String saveTicket(@ModelAttribute("TicketDetails") TicketDetails ticket) {
//		System.out.println("saving complaint");
//		ticket.setTicketType("complaint");
//		System.out.println("method "+tservice.findByUserId(ticket.getUserId()));
//		System.out.println("id is "+tservice.get(ticket.getId()));
//		System.out.println("user id is "+ticket.getUserId());
//		tservice.save(ticket);			
//		return "cticket";
//		
//	}
	
	@RequestMapping(value="/saveComplaint", method=RequestMethod.POST)
	public String saveTicket(@ModelAttribute TicketDetails ticket, Model model) {
		ticket.setTicketType("complaint");
		TicketDetails td;
		Users user=new Users();
		td= tservice.findByUserId(user.getUserId());
		System.out.println("user class"+user.getUserId());
		System.out.println("uid  "+td);
		tservice.save(ticket);			
		return "cticket";
	}
	
	
	
	
	
	@RequestMapping("/list_complaints")
	public String viewAllCompalaintsPage(Model model) {
		System.out.println("all complaints");
		List<TicketDetails> listTickets=tservice.listAll();
		System.out.println(listTickets.size()+"  size");
		model.addAttribute("listTicket", listTickets);
		return "cticket";
	}
	

}
