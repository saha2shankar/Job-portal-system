package com.punam.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.punam.Model.Admin;
import com.punam.Service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/login")
	public String getLoginpage() {
		return "loginpage";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
	    Admin user = adminService.login(email, password);
	    
	    if (user != null) { 
	    	session.setAttribute("validuser", user);
	    	session.setMaxInactiveInterval(10000);
	        return "redirect:/dashboard"; // Redirect to the admin dashboard
	    } 
	    
	    // If login fails, return to the login page with an error message
	    model.addAttribute("message", "Invalid email or password");
	    return "loginpage";
	}

	
	@GetMapping("/register")
	public String getRegisterPage() {
		return "Register";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute Admin admin ,Model model) {
		admin.setProfilePic("placeholder.jpg");
		admin.setLastUpdated(LocalDateTime.now());
		adminService.addAdmin(admin);
	    model.addAttribute("message", "Account Success Fully Created let's Login");
		return "loginpage";
	
	}
	
	@GetMapping("/adminList")
	public String getAdmin(Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		model.addAttribute("AdminList", adminService.getAllAdmin());
		return "Admin/AdminList";
	}
	
	@GetMapping("/delete")
	public String deleteAdmin(@RequestParam int id, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		adminService.deleteAdminByid(id);
		return "redirect:/adminList";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/myprofile")
	public String getProfilePage(HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		return"Admin/Profile";
	}
	
	@GetMapping("/edit/profile")
	public String editProfile(@RequestParam int id, Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		Admin admin =adminService.getAdminbyId(id);
		model.addAttribute("aModel", admin);
		return "Admin/ProfileEdit";
	}
	
@PostMapping("/updateProfile")
public String updateProfile(
        @ModelAttribute Admin admin,
        @RequestParam(required = false) MultipartFile image,
        HttpSession session) {

    if (session.getAttribute("validuser") == null) {
        return "redirect:/login";
    }

    try {

        if (image != null && !image.isEmpty()) {

            // Upload directory
            Path uploadDir = Path.of("uploads/photos");

            // Create directory if it doesn't exist
            Files.createDirectories(uploadDir);

            // File name
            String fileName = image.getOriginalFilename();

            // File path
            Path filePath = uploadDir.resolve(fileName);

            // Save image
            Files.copy(
                    image.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            // Save image name in database
            admin.setProfilePic(fileName);

            System.out.println("Profile image uploaded to: "
                    + filePath.toAbsolutePath());

        } else {

            // Keep existing image if no new image uploaded
            Admin oldAdmin = adminService.getAdminbyId(admin.getId());

            if (oldAdmin != null) {
                admin.setProfilePic(oldAdmin.getProfilePic());
            } else {
                admin.setProfilePic("placeholder.jpg");
            }
        }

        admin.setLastUpdated(LocalDateTime.now());
        adminService.updateAdmin(admin);

    } catch (IOException e) {
        e.printStackTrace();
    }

    return "redirect:/logout";
}
	
	@GetMapping("/change-password")
	public String changePassword(HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		return"Admin/PasswordChange";
	}
	
	@PostMapping("/change-password")
	public String passwordChange(@RequestParam int id, @RequestParam String oldpassword, @RequestParam String newpassword, Model model,HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}

		Admin adminByIdPassword =adminService.findByIdAndPassword(id, oldpassword);
		if(adminByIdPassword != null) {
			adminByIdPassword.setPassword(newpassword);
			adminService.updateAdmin(adminByIdPassword);
			model.addAttribute("message", "Your password is changed !");
			return "loginpage";
		}else {
			model.addAttribute("message", "Your Old Password is Incorrect. Try Again !");
			return "Admin/PasswordChange";
		}
		
		
		
	}


}
