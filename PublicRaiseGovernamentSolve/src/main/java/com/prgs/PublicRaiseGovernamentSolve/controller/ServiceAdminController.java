package com.prgs.PublicRaiseGovernamentSolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.prgs.PublicRaiseGovernamentSolve.Service.ServiceService;
import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.ServiceModel;


@Controller
@SessionAttributes({"msg","loggedInUserID", "loggedInUserEmail", "loggedInUserName"})

public class ServiceAdminController {
	@Autowired
	private ServiceService sService;
	
	
	@RequestMapping("/admin_raise")
	public String viewAdminticketpage(Model model) {
		ServiceModel serviceticket=new ServiceModel();
		model.addAttribute("service", serviceticket);
		return "adminTicketPage";
	}		

	
	@RequestMapping(value="/admin_ticket", method=RequestMethod.POST)
	public String adminRaiseTicket(@ModelAttribute("service") ServiceModel serviceticket,Model model) {
		sService.save(serviceticket);
		return "adminTicketPage";
		
	}
	

	@RequestMapping("/department_update")
	public String changeInStatus(Model model) {
		Department department=new Department();
		model.addAttribute("update", department);
		return null;
	
	}
	
	
}
