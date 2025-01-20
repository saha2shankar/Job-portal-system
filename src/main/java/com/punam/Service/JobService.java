package com.punam.Service;

import java.util.List;

import com.punam.Model.Job;

public interface JobService {
	
	void addJob(Job job);
	void update(Job job);
	Job findJobById(int id);
	void deleteJobById(int id);
	List<Job> getallJobs();
	List<Job> searchJobs(String title, String location);
	List<Job> findByCategory(String category);

}
