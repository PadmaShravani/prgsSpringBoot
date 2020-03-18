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
import org.springframework.web.servlet.ModelAndView;

import com.prgs.PublicRaiseGovernamentSolve.Service.DepartmentService;
import com.prgs.PublicRaiseGovernamentSolve.Service.ServiceService;
import com.prgs.PublicRaiseGovernamentSolve.Service.TicketService;
import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.ServiceModel;
import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;

@Controller
@SessionAttributes({ "msg", "loggedInUserID", "loggedInUserEmail", "loggedInUserName" })

public class ServiceAdminController {
	@Autowired
	private ServiceService sService;
	@Autowired
	private DepartmentService dService;
	@Autowired
	private TicketService tService;

	
	@RequestMapping("/admin_page")
	public String adminViewPage(Model model) {
		return "adminViewPage";
	}

	@RequestMapping("/admin_raise")
	public String viewAdminticketpage(@ModelAttribute("service") ServiceModel serviceticket1, Model model) {
		ServiceModel serviceticket = new ServiceModel();
		serviceticket.setTicketNumbers(serviceticket1.getTicketNumbers());
		System.out.println("tkt1 "+serviceticket1.getTicketNumbers());
		model.addAttribute("service", serviceticket);
		List<Department> dList = dService.listAll();
		model.addAttribute("departments", dList);
		return "adminTicketPage";
	}

	@RequestMapping(value = "/admin_ticket", method = RequestMethod.POST)
	public String adminRaiseTicket(@ModelAttribute("service") ServiceModel serviceticket, Model model) {
		System.out.println("Department Id: " + serviceticket.getDepartmentId());
		serviceticket.setStatus("Open");
		sService.save(serviceticket);
		System.out.println("Select Tickets :" + serviceticket.getTicketNumbers());
		System.out.println("Service ID :" + serviceticket.getServiceId());
		String selTickets = serviceticket.getTicketNumbers();
		if (selTickets.contains(",")) {
			System.out.println("Multiple Tickets");
			String[] s = selTickets.split(",");
			TicketDetails[] tickets = new TicketDetails[s.length];
			for (int i = 0; i < s.length; i++) {
				tickets[i] = tService.get(Integer.parseInt(s[i]));
				System.out.println("Before - Service ID: " + tickets[i].getServiceId());
				tickets[i].setServiceId(serviceticket.getServiceId());
				tickets[i].setStatus("assigned");
				tService.save(tickets[i]);
				
			}
		}else {
			TicketDetails tkt=tService.get(Integer.parseInt(selTickets));
			tkt.setServiceId(serviceticket.getServiceId());
			tkt.setStatus("assigned");
			tService.save(tkt);
		}
		System.out.println("all Service Tickets");
		List<ServiceModel> listAllServiceTickets=sService.listAll();
		System.out.println(listAllServiceTickets.size()+"  size");	
		model.addAttribute("list_all_service_tickets", listAllServiceTickets);
		return "serviceticketListPage";
	}
	
	@RequestMapping("/serviceList")
	public String viewAllServiceTickets(Model model) {
		System.out.println("all Service Tickets");
		List<ServiceModel> listAllServiceTickets=sService.listAll();
		System.out.println(listAllServiceTickets.size()+"  size");	
		model.addAttribute("list_all_service_tickets", listAllServiceTickets);
		
		return "serviceticketListPage";
	}
	
	@RequestMapping("/department_page")
	public String departmentViewPage(Model model) {
		List<Department> dList = dService.listAll();
		model.addAttribute("departments", dList);
		return "departmentViewpage";
	}

	@RequestMapping("/department_ticket_open")
	public String viewDepartmentPage(@ModelAttribute("department") Department dept,Model model) {
		
		//Department department = new Department();
		List<ServiceModel> serviceTktListByDept = sService.getDepartmentTickets(dept.getDepartmentId());
		System.out.println("d t id:"+dept.getDepartmentId());
		model.addAttribute("servicetickets", serviceTktListByDept);
		//System.out.println("d id:"+serviceticket.getDepartmentId());
		return "departmentListPage";

	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("editDepartmentListPage");
		ServiceModel serviceTicket = sService.get(id);
		mav.addObject("service", serviceTicket);
		return mav;
	}
	
	@RequestMapping(value="/saveTicket", method=RequestMethod.POST)
	public String saveTicket(@ModelAttribute("service") ServiceModel serviceTicket,Model model) {
		sService.save(serviceTicket);
		System.out.println("Saved :"+serviceTicket.getServiceId());
		System.out.println("ticket nums"+serviceTicket.getTicketNumbers());
		String selTickets = serviceTicket.getTicketNumbers();
		if (selTickets.contains(",")) {
			System.out.println("Multiple Tickets");
			String[] s = selTickets.split(",");
			TicketDetails[] tickets = new TicketDetails[s.length];
			for (int i = 0; i < s.length; i++) {
				tickets[i] = tService.get(Integer.parseInt(s[i]));
				System.out.println("Before - status : " + tickets[i].getStatus());
				tickets[i].setStatus(serviceTicket.getStatus());
				tService.save(tickets[i]);
			}
		}else {
			TicketDetails tkt=tService.get(Integer.parseInt(selTickets));
			tkt.setStatus(serviceTicket.getStatus());
			tService.save(tkt);
		}
		model.addAttribute("success", "ticket Status has been changed");
		return "editDepartmentListPage";
		}
	}
