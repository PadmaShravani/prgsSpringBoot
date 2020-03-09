package com.prgs.PublicRaiseGovernamentSolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prgs.PublicRaiseGovernamentSolve.Service.UsersService;
import com.prgs.PublicRaiseGovernamentSolve.model.Users;

@Controller
public class LoginAndRegistrationController {
	
	@Autowired
	private UsersService service;

	private String loggedInUser = null;

	@RequestMapping("/prgs")
	public String viewHomePage(Model model) {
		return "home";
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

//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String saveRegisteredUser(@ModelAttribute("users") Users users) {
//		users.setRoleId(1);
//		userRepo.save(users);
//		return "login";
//	}

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
				return "login";
			} else {
				// loggedInUser = user.getEmail();
				loggedInUser = exist_user.getFirstName() + " " + exist_user.getLastName();
				model.addAttribute("msg", "Logged in as " + loggedInUser);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not a valid email. Please register using sign up");
		}
		return "userViewPage";
	}

}
