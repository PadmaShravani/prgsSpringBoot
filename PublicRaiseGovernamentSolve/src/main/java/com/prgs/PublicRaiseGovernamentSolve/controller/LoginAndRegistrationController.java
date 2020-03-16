package com.prgs.PublicRaiseGovernamentSolve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.prgs.PublicRaiseGovernamentSolve.Service.DepartmentService;
import com.prgs.PublicRaiseGovernamentSolve.Service.TicketService;
import com.prgs.PublicRaiseGovernamentSolve.Service.UsersService;
import com.prgs.PublicRaiseGovernamentSolve.model.Department;
import com.prgs.PublicRaiseGovernamentSolve.model.TicketDetails;
import com.prgs.PublicRaiseGovernamentSolve.model.Users;

@Controller
@SessionAttributes({"msg","loggedInUserID", "loggedInUserEmail", "loggedInUserName"})
public class LoginAndRegistrationController {

	@Autowired
	private UsersService service;
	@Autowired
	private DepartmentService dService;
	@Autowired
	private TicketService tService;
	
	private String loggedInUser = null;
	
	@RequestMapping("/prgs")
	public String viewVisitorPage(Model model) {
		
		List<TicketDetails> listAllTickets=tService.listAll();
		System.out.println(listAllTickets.size()+"  size");	
		model.addAttribute("list", listAllTickets.size());
		
		List<TicketDetails> solvedTickets=tService.getByStatus("closed");
		System.out.println(solvedTickets.size()+"  size");	
		model.addAttribute("closedList", solvedTickets.size());
		
		return "visitor";
	}

	@RequestMapping("/new_user")
	public String showNewUserForm(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "signUp";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("users") Users users) {
		users.setRoleId(1);
		service.save(users);
		return "login";
	}

	@RequestMapping("/existing_user")
	public String showLoginForm(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "login";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validateLoginInfo(@ModelAttribute Users user, Model model) {
		System.out.println("Entered :" + user.getEmail());
		System.out.println("Entered :" + user.getPassword());
		Users exist_user;
		try {
			exist_user = service.findByEmail(user.getEmail());
			System.out.println("From DB :" + exist_user.getEmail());
			System.out.println("From DB :" + exist_user.getPassword());
			if (!exist_user.getPassword().equalsIgnoreCase(user.getPassword())) {
				
				System.out.println("Not a valid password. Please click forgot password");
				model.addAttribute("errorMessage", "Please enter valid Password ");
				return "login";
			} else {
				// loggedInUser = user.getEmail();
			
				loggedInUser = exist_user.getFirstName() + " " + exist_user.getLastName();				
				model.addAttribute("msg", "Logged in as " + loggedInUser);
				model.addAttribute("loggedInUserEmail", exist_user.getEmail());
				model.addAttribute("loggedInUserName",  exist_user.getUserName());
				model.addAttribute("loggedInUserID",  exist_user.getUserId());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not a valid email. Please register using sign up");
			model.addAttribute("erroremail", "Please enter valid email ");
			return "login";
		}
		 System.out.println("role id "+exist_user.getRoleId());
		 if(exist_user.getRoleId()==3) {
			 List<Department> dList = dService.listAll();
				model.addAttribute("departments", dList);
		 }
		return service.loginWithRole(exist_user);
	}

}
