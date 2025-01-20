package com.punam.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punam.Model.Testimonial;
import com.punam.Repository.TestimonialRepository;
import com.punam.Service.TestimonialService;

@Service
public class TestimonialServiceImpl implements TestimonialService{
	
	@Autowired
	TestimonialRepository testimonialRepository;

	@Override
	public void addTestimonial(Testimonial testimonial) {
		testimonialRepository.save(testimonial);
		
	}

	@Override
	public void updateTestimonial(Testimonial testimonial) {
		testimonialRepository.save(testimonial);
		
	}

	@Override
	public Testimonial getTestimonialById(int id) {
		
		return testimonialRepository.findById(id).get();
	}

	@Override
	public void deleteTestimonial(int id) {
		testimonialRepository.deleteById(id);
		
	}

	@Override
	public List<Testimonial> getallTestimonial() {
		
		return testimonialRepository.findAll();
	}

	
}
