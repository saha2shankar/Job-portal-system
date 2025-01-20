package com.punam.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.punam.Model.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {


    @Query("SELECT j FROM Job j WHERE "
         + "(:title IS NULL OR j.title LIKE %:title%) AND "
         + "(:location IS NULL OR j.location LIKE %:location%)")
    List<Job> findByTitleAndLocation(@Param("title") String title, @Param("location") String location);
	
    List<Job> findByCategory(String category);
}
