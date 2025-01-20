package com.punam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.punam.Model.Message;

public interface MessageReopsitory extends JpaRepository<Message, Integer>{

}
