package com.punam.Controller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.punam.Model.Applicant;
import com.punam.Service.ApplicantService;
import com.punam.Service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ApplicantController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	ApplicantService applicantService;
	
	
	@GetMapping("/apply")
	public String getApplicationForm(@RequestParam int id, Model model) {		
	model.addAttribute("job", jobService.findJobById(id));
		return "ApplyForm";
	}
	
	@PostMapping("/apply")
	public ResponseEntity<String> apply(@ModelAttribute Applicant applicant, @RequestParam("cv") MultipartFile cv) {
	    try {
	        if (!cv.isEmpty()) {
	            // Save the file
	            Files.copy(cv.getInputStream(),
	                    Path.of("src/main/resources/static/Resumes/" + cv.getOriginalFilename()),
	                    StandardCopyOption.REPLACE_EXISTING);
	            applicant.setResume(cv.getOriginalFilename());
	        }

	        // Add additional applicant details
	        applicant.setCreateAt(LocalDateTime.now());
	        applicantService.addApplicant(applicant);

	        // Respond with success message
	        return ResponseEntity.ok("Application submitted successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Respond with error message
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while submitting your application.");
	    }
	}
	
	
	@GetMapping("/allApplicant")
	public String getAllApplicantList(Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		model.addAttribute("Applicants", applicantService.getAllApplicant());
		return"Admin/ApplicantList";
	}
	
	@GetMapping("/applicantDetails")
	public String ApplicantDetails(@RequestParam int id ,Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		model.addAttribute("applicant", applicantService.getApplicantById(id));
		return "Admin/ApplicantDetails";
	}


}
