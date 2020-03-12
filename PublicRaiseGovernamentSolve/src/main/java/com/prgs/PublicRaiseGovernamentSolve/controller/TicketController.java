package com.prgs.PublicRaiseGovernamentSolve.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.prgs.PublicRaiseGovernamentSolve.Service.TicketService;
import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;


@Controller
@SessionAttributes({"msg","loggedInUserID", "loggedInUserEmail", "loggedInUserName"})
public class TicketController {
	@Autowired
	private TicketService tservice;

	@RequestMapping("/home")
	public String viewVisitorPage(Model model) {
		return "userViewPage";
	}
	
	@RequestMapping("/complaint_page")
	public String viewComplaintPage(Model model) {
		TicketDetails td = new TicketDetails();
		model.addAttribute("ticketdetails", td);
		System.out.println("all complaints");
		int uId=(int) model.getAttribute("loggedInUserID");
		List<TicketDetails> listUserTickets=tservice.getUserTickets(uId, "complaint");
		System.out.println("No. of Tickets: "+listUserTickets.size());
		model.addAttribute("listtickets", listUserTickets);	
		return "cticket";
	}
	
	@RequestMapping(value="/saveComplaint", method=RequestMethod.POST)
	public String saveComplaintTicket(@ModelAttribute("ticketdetails") TicketDetails ticket, Model model) {	
		System.out.println("action: "+model.getAttribute("loggedInUserID"));
		System.out.println("action: "+model.getAttribute("loggedInUserEmail"));
		ticket.setTicketType("complaint");
		ticket.setStatus("Open");
		ticket.setUserId((int) model.getAttribute("loggedInUserID"));
		ticket.setServiceId(1);
		tservice.save(ticket);	
				
		return "cticket";
	}
	
//	@RequestMapping("/complaint_page#menu1")
//	public void viewAllCompalaintsPage(Model model) {
//		System.out.println("all complaints");
//		List<TicketDetails> listTickets=tservice.listAll();
//		System.out.println("No. of Tickets: "+listTickets.size());
//		model.addAttribute("listtickets", listTickets);
//		//return "cticket";
//	}
	
	@RequestMapping("/request_page")
	public String viewRequestPage(Model model) {
		TicketDetails td = new TicketDetails();
		model.addAttribute("requestdetails", td);
		System.out.println("request page");
		int uId=(int) model.getAttribute("loggedInUserID");
		List<TicketDetails> listRequests=tservice.getUserTickets(uId, "request");
		System.out.println("No. of Tickets: "+listRequests.size());
		model.addAttribute("listrequests", listRequests);	
		return "rticket";
	}

		
	@RequestMapping(value="/saveRequest", method=RequestMethod.POST)
	public String saveRequestTicket(@ModelAttribute("requestdetails") TicketDetails ticket, Model model) {
		System.out.println("action: "+model.getAttribute("loggedInUserID"));
		System.out.println("action: "+model.getAttribute("loggedInUserEmail"));
		ticket.setComplaintType("others");
		ticket.setTicketType("request");
		ticket.setStatus("Open");
		ticket.setUserId((int) model.getAttribute("loggedInUserID"));
		ticket.setServiceId(1);
		tservice.save(ticket);	
		return "rticket";
	}
				
	@RequestMapping("/complaints_received")
	public String viewAllComplaints(Model model) {
		System.out.println("all complaints");
		List<TicketDetails> listAllComplaints=tservice.getAllTickets("complaint");
		System.out.println(listAllComplaints.size()+"  size");
		model.addAttribute("list_all_complaints", listAllComplaints);
		return "complaintsReceivedPage";
	}
	@RequestMapping("/requests_received")
	public String viewAllRequests(Model model) {
		System.out.println("all complaints");
		List<TicketDetails> listAllRequests=tservice.getAllTickets("request");
		System.out.println(listAllRequests.size()+"  size");
		model.addAttribute("list_all_requests", listAllRequests);
		return "RequestsReceivedPage";
	}
	
//	@RequestMapping(value="/admin_ticket", method=RequestMethod.POST)
//	public String adminRaiseTicket(@ModelAttribute("service") ServiceModel service,Model model) {
//		
//		return null;
//		
//	}
	
}
