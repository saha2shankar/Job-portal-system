package com.punam.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punam.Model.Applicant;
import com.punam.Repository.ApplicantRepostiory;
import com.punam.Service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService{
	
	@Autowired
	ApplicantRepostiory applicantRepostiory;

	@Override
	public void addApplicant(Applicant applicant) {

		applicantRepostiory.save(applicant);
	}

	@Override
	public void updateApplicant(Applicant applicant) {
		applicantRepostiory.save(applicant);
		
	}

	@Override
	public Applicant getApplicantById(int id) {
		return applicantRepostiory.findById(id).get();
	}

	@Override
	public void deleteApplicantById(int id) {

		applicantRepostiory.deleteById(id);
	}

	@Override
	public List<Applicant> getAllApplicant() {

		return applicantRepostiory.findAll();
	}
	

}
