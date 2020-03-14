package com.prgs.PublicRaiseGovernamentSolve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.prgs.PublicRaiseGovernamentSolve.Service.DepartmentService;
import com.prgs.PublicRaiseGovernamentSolve.Service.ServiceService;
import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.ServiceModel;
import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;


@Controller
@SessionAttributes({"msg","loggedInUserID", "loggedInUserEmail", "loggedInUserName"})

public class ServiceAdminController {
	@Autowired
	private ServiceService sService;
	@Autowired
	private DepartmentService dService;
	
	@RequestMapping("/admin_raise/{ticketNumbers}")
	public String viewAdminticketpage(@PathVariable(name="ticketNumbers") String ticketNumbers, Model model) {
		ServiceModel serviceticket=new ServiceModel();
		model.addAttribute("service", serviceticket);
		//Department department=new Department();
		System.out.println("Select Tickets :"+ticketNumbers);
		List<Department> dList = dService.listAll();
		model.addAttribute("departments", dList);
		return "adminTicketPage";
	}		

	
	@RequestMapping(value="/admin_ticket", method=RequestMethod.POST)
	public String adminRaiseTicket(@ModelAttribute("service") ServiceModel serviceticket,Model model) {
		System.out.println("Department Id: "+serviceticket.getDepartmentId());
		serviceticket.setStatus("Open");
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
