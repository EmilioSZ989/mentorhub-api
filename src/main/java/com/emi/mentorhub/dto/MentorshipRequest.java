package com.emi.mentorhub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MentorshipRequest {

    @NotNull(message = "El mentorId es obligatorio")
    private Long mentorId;
}
