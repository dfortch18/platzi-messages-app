package com.dfortch.messagesapp.service.impl;

import com.dfortch.messagesapp.dto.CreateMessageDTO;
import com.dfortch.messagesapp.dto.UpdateMessageDTO;
import com.dfortch.messagesapp.exception.ValidationException;
import com.dfortch.messagesapp.persistence.entity.Message;
import com.dfortch.messagesapp.persistence.repository.MessageRepository;
import com.dfortch.messagesapp.service.MessageService;
import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Set;

public class MessageServiceImpl extends ValidationService implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean messageExists(Long id) {
        return messageRepository.findById(id).isPresent();
    }

    @Override
    public Message createMessage(CreateMessageDTO createMessageDTO) {
        Set<ConstraintViolation<CreateMessageDTO>> violations = getValidator().validate(createMessageDTO);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        Message message = new Message();
        message.setMessage(createMessageDTO.message());
        message.setMessageAuthor(createMessageDTO.messageAuthor());

        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(UpdateMessageDTO updateMessageDTO) {
        Set<ConstraintViolation<UpdateMessageDTO>> violations = getValidator().validate(updateMessageDTO);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        Message message = messageRepository.findById(updateMessageDTO.id()).orElseThrow();

        message.setMessage(updateMessageDTO.message());
        message.setMessageAuthor(updateMessageDTO.messageAuthor());

        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.delete(id);
    }
}
