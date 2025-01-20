package com.punam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.punam.Model.Testimonial;

public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {

}
