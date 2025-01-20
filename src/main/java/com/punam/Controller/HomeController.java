package com.punam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.punam.Service.AdminService;
import com.punam.Service.ApplicantService;
import com.punam.Service.JobService;
import com.punam.Service.MessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MessageService  messageService;
	
	@Autowired
	ApplicantService applicantService;
	
	
	@GetMapping("/")
	public String getHomepage() {
		return "index";
	}
	
	
	@GetMapping("/dashboard")
	public String getDashboard(Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		model.addAttribute("totalJob", jobService.getallJobs());
		model.addAttribute("totalAdmin", adminService.getAllAdmin());
		model.addAttribute("totalMessage", messageService.getAllMessage());
		model.addAttribute("totalApplicant", applicantService.getAllApplicant());
		
		return"Admin/Dashboard";
	}
	@GetMapping("/contact")
	public String getcontact() {
		return"contact";
	}
	


}
