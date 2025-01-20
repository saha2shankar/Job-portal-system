package com.punam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.punam.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	Admin findAdminByEmailAndPassword(String email, String password);
	 Admin findByIdAndPassword(int id, String password);

}
