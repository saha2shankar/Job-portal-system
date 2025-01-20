package com.punam.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punam.Model.Job;
import com.punam.Repository.JobRepository;
import com.punam.Service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobRepository jobRepository;

	@Override
	public void addJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public void update(Job job) {
jobRepository.save(job);
	}

	@Override
	public Job findJobById(int id) {
		return jobRepository.findById(id).get();
	}

	@Override
	public void deleteJobById(int id) {
jobRepository.deleteById(id);
	}

	@Override
	public List<Job> getallJobs() {
		return jobRepository.findAll();
	}

	@Override
	public List<Job> searchJobs(String title, String location) {
		if (title == null && location == null) {
            return jobRepository.findAll(); // Return all jobs if no filter is applied
        }
        return jobRepository.findByTitleAndLocation(title, location);
	}

	@Override
	public List<Job> findByCategory(String category) {
		
		return jobRepository.findByCategory(category);
	}

}
