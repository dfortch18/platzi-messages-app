package com.dfortch.messagesapp.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UpdateMessageDTO(
        @NotNull Long id,
        @NotNull @Length(max = 280) String message,
        @NotNull @Length(max = 50) String messageAuthor) {
}
