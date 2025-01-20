package com.punam.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String logiUrl;
	private String location;
	private String salary;
	@Lob
	private String jobDescription;
	@Lob
	private String requiredSkills;
	@Lob
	private String educationandExperience;
	private String vacancyNo;
	private String jobNature;
	private LocalDateTime postedDate;
	private LocalDate lastDate;
	private String companyName;
	@Lob
	private String companyDescription;
	private String companyWebsite;
	private String compayeEmail;
	private String category;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogiUrl() {
		return logiUrl;
	}
	public void setLogiUrl(String logiUrl) {
		this.logiUrl = logiUrl;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getRequiredSkills() {
		return requiredSkills;
	}
	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	public String getEducationandExperience() {
		return educationandExperience;
	}
	public void setEducationandExperience(String educationandExperience) {
		this.educationandExperience = educationandExperience;
	}
	public String getVacancyNo() {
		return vacancyNo;
	}
	public void setVacancyNo(String vacancyNo) {
		this.vacancyNo = vacancyNo;
	}
	public String getJobNature() {
		return jobNature;
	}
	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}
	public LocalDateTime getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDateTime postedDate) {
		this.postedDate = postedDate;
	}
	public LocalDate getLastDate() {
		return lastDate;
	}
	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getCompayeEmail() {
		return compayeEmail;
	}
	public void setCompayeEmail(String compayeEmail) {
		this.compayeEmail = compayeEmail;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
	
	
}
