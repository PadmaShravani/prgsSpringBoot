package com.prgs.PublicRaiseGovernamentSolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prgs.PublicRaiseGovernamentSolve.Service.ServiceService;


@Controller
public class ServiceAdminController {
	@Autowired
	private ServiceService sService;
}
