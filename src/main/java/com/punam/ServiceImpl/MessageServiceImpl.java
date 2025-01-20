package com.punam.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punam.Model.Message;
import com.punam.Repository.MessageReopsitory;
import com.punam.Service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageReopsitory messageReopsitory;
	
	@Override
	public void addMessage(Message message) {
		messageReopsitory.save(message);		
	}

	@Override
	public Message finMessagebyId(int id) {
		
		return messageReopsitory.findById(id).get();
	}

	@Override
	public void DeleteMessage(int id) {
		messageReopsitory.deleteById(id);
		
	}

	@Override
	public void UpdateMessage(Message message) {
		messageReopsitory.save(message);
		
	}

	@Override
	public List<Message> getAllMessage() {
		
		return messageReopsitory.findAll();
	}

}
