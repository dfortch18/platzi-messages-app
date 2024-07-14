package com.dfortch.messagesapp.service;

import com.dfortch.messagesapp.dto.CreateMessageDTO;
import com.dfortch.messagesapp.dto.UpdateMessageDTO;
import com.dfortch.messagesapp.persistence.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();

    Message getMessage(Long id);

    boolean messageExists(Long id);

    Message createMessage(CreateMessageDTO createMessageDTO);

    Message updateMessage(UpdateMessageDTO updateMessageDTO);

    void deleteMessage(Long id);
}
