package com.emi.mentorhub.service;

import com.emi.mentorhub.dto.MentorshipRequest;
import com.emi.mentorhub.dto.MentorshipResponse;

import java.util.List;

public interface MentorshipService {

    MentorshipResponse createRequest(MentorshipRequest request, String studentEmail);

    List<MentorshipResponse> getByUser(String email, String role);

    MentorshipResponse approve(Long id, String mentorEmail);

    MentorshipResponse reject(Long id, String mentorEmail);
}
