package com.punam.Service;

import java.util.List;

import com.punam.Model.Message;

public interface MessageService {
	
	void addMessage(Message message);
	Message finMessagebyId(int id);
	void DeleteMessage(int id);
	void UpdateMessage(Message message);
	List<Message> getAllMessage();

}
