package com.prgs.PublicRaiseGovernamentSolve.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prgs.PublicRaiseGovernamentSolve.model.Users;
import com.prgs.PublicRaiseGovernamentSolve.repository.UsersRepository;


@Controller
public class RegistrationController {
	@Autowired
	private UsersRepository userRepo;
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
	@RequestMapping("/exesting_user")
	public String showLoginForm(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "login";
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveRegisteredUser(@ModelAttribute("users") Users users) {
		users.setRoleId(1);
		userRepo.save(users);
		return "login";
	}
	
	public void findByUserEmail(String email) {
		Users users = new Users();
		String s=users.getEmail();
		if(email.equals(s)) {
			System.out.println("email is "+email);
		}else {
			System.out.println("wrong email");
		}
		//return email;
	}
	
	@RequestMapping("/loggedin")
	public String validateUser(@RequestParam String Email , @RequestParam String password) {
		findByUserEmail(Email);
	
		return "userViewPage";
	}
}
