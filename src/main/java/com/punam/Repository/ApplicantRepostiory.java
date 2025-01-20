package com.punam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.punam.Model.Applicant;

public interface ApplicantRepostiory extends JpaRepository<Applicant, Integer>{

}
