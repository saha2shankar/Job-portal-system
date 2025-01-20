package com.punam.Service;

import java.util.List;

import com.punam.Model.Applicant;

public interface ApplicantService {
	
	void addApplicant(Applicant applicant);
	void updateApplicant(Applicant applicant);
	Applicant getApplicantById(int id);
	void deleteApplicantById(int id);
	List<Applicant> getAllApplicant();

}
