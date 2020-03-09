package com.prgs.PublicRaiseGovernamentSolve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;


@Controller
public class TicketController {
	
	@RequestMapping("/complain")
	public String viewHomePage(Model model) {

		TicketDetails ticket = new TicketDetails();
		model.addAttribute("ticket", ticket);
		return "ticket1";
	}

}
