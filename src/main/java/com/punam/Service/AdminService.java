package com.punam.Service;

import java.util.List;

import com.punam.Model.Admin;

public interface AdminService {
	
	void addAdmin(Admin admin);
	void updateAdmin(Admin admin);
	Admin login(String email, String password);
	Admin getAdminbyId(int id);
	List<Admin> getAllAdmin();
	void deleteAdminByid(int id);
	 Admin findByIdAndPassword(int id, String password);
}
