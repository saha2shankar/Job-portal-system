package com.punam.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.punam.Model.Message;
import com.punam.Service.MessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@PostMapping("/contact")
	public String addMessage(@ModelAttribute Message message, Model model) {
		message.setMessageDate(LocalDateTime.now());
		messageService.addMessage(message);
		model.addAttribute("message", "Your message has been sent successfully!");
		return "contact";
	}
	
	@GetMapping("/contactview")
	public String contactView(Model model, HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return"redirect:/login";
		}
		model.addAttribute("messages", messageService.getAllMessage());
		return"Admin/ContactView";
	}

}
