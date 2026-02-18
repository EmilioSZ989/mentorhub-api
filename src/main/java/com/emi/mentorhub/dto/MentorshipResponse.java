package com.emi.mentorhub.dto;

import com.emi.mentorhub.entity.MentorshipStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MentorshipResponse {

    private Long id;
    private String mentorEmail;
    private String studentEmail;
    private MentorshipStatus status;
    private LocalDateTime createdAt;
}
