package com.punam.Service;

import java.util.List;

import com.punam.Model.Testimonial;

public interface TestimonialService {
	
	void addTestimonial(Testimonial testimonial);
	void updateTestimonial(Testimonial testimonial);
	Testimonial getTestimonialById (int id);
	void deleteTestimonial(int id);
	List<Testimonial> getallTestimonial();

}
