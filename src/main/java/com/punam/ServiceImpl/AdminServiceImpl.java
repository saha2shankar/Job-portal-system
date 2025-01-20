package com.punam.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punam.Model.Admin;
import com.punam.Repository.AdminRepository;
import com.punam.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public void addAdmin(Admin admin) {
		adminRepository.save(admin);
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminRepository.save(admin);
		
	}

	@Override
	public Admin login(String email, String password) {
		
		return adminRepository.findAdminByEmailAndPassword(email, password);
	}

	@Override
	public Admin getAdminbyId(int id) {
		
		return adminRepository.findById(id).get();
	}

	@Override
	public List<Admin> getAllAdmin() {
		
		return adminRepository.findAll();
	}

	@Override
	public void deleteAdminByid(int id) {
		adminRepository.deleteById(id);
		
	}
	
	@Override
	public Admin findByIdAndPassword(int id, String password) {
		return adminRepository.findByIdAndPassword(id, password);
	}

}
