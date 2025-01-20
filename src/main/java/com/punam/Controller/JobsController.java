package com.punam.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.punam.Model.Job;
import com.punam.Service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JobsController {
	
	@Autowired
	JobService jobService;
	
	
	
	@GetMapping("/addJobs")
	public String addJob(HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		return "Admin/AddJob";
	}
	
	@PostMapping("/addJobs")
	public String postJob(@ModelAttribute Job job, Model model, @RequestParam(required = false) MultipartFile image, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		if(!image.isEmpty()) {
			try {
				Files.copy(image.getInputStream(), 
						Path.of("src/main/resources/static/Photo/"+ image.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				job.setLogiUrl(image.getOriginalFilename());
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		job.setPostedDate(LocalDateTime.now());
		jobService.addJob(job);
		return "redirect:/addJobs";
	}
	
	@GetMapping("/jobDetails")
	public String JobDetails(@RequestParam int id, Model model) {
		model.addAttribute("jobDetails", jobService.findJobById(id));
		return "JobDetails";
	}
	
	@GetMapping("/allVacancy")
	public String getVacancy(Model model) {
		model.addAttribute("VacancyList", jobService.getallJobs());
		return "vacancy";
	}
	
	@GetMapping("/adminjobview")
	public String getAdminVacancy(Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		model.addAttribute("VacancyList", jobService.getallJobs());
	return "Admin/JobsView";
	}
	
	@GetMapping("/delete/job")
	public String deleteJob(@RequestParam int id, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		jobService.deleteJobById(id);
		return "redirect:/adminjobview";
	}
	
	@GetMapping("/edit/job")
	public String editjob(Model model, @RequestParam int id, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		Job job = jobService.findJobById(id);
		model.addAttribute("JobModel",job);
		return "Admin/EditJob";
	}
	
	@PostMapping("/updatejob")
	public String editjob(@ModelAttribute Job job, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		
		System.out.println("this is postdete:" + job.getPostedDate());
		System.out.println("this is postdete:" + job.getLogiUrl());
		job.setLogiUrl(job.getLogiUrl());
		job.setPostedDate(job.getPostedDate());
		
		System.out.println("this is postdete:"+ job.getPostedDate());
		System.out.println("this is postdete:"+ job.getLogiUrl());
		jobService.update(job);
		return "redirect:/adminjobview";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "location", required = false) String location, Model model) {
		List<Job> jobs = jobService.searchJobs(title, location);
		model.addAttribute("location", location);
		System.out.println("this is location:"+ location);
		System.out.println("this is tilel:"+title);
		model.addAttribute("keyword", title);
		model.addAttribute("VacancyList", jobs);
		
		return "vacancy";
	}
	
	@GetMapping("/jobs")
	public String JobsbyCategory(@RequestParam String category, Model model) {
		System.out.println("thi calteofildjadjs"+category);
		List<Job> byCategory = jobService.findByCategory(category);
		System.out.println("Job by category"+byCategory);
		System.out.println("thi calteofildjadjs"+category);
		model.addAttribute("VacancyList", byCategory);
		return "vacancy";
	}

}
