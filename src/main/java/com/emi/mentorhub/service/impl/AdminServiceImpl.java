package com.emi.mentorhub.service.impl;

import com.emi.mentorhub.dto.AdminStatsResponse;
import com.emi.mentorhub.entity.MentorshipStatus;
import com.emi.mentorhub.entity.Role;
import com.emi.mentorhub.repository.MentorshipRepository;
import com.emi.mentorhub.repository.UserRepository;
import com.emi.mentorhub.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final MentorshipRepository mentorshipRepository;

    @Override
    public AdminStatsResponse getStats() {

        return AdminStatsResponse.builder()
                .totalUsers(userRepository.count())
                .totalAdmins(userRepository.countByRole(Role.ADMIN))
                .totalMentors(userRepository.countByRole(Role.MENTOR))
                .totalStudents(userRepository.countByRole(Role.STUDENT))

                .totalMentorships(mentorshipRepository.count())
                .totalPending(mentorshipRepository.countByStatus(MentorshipStatus.PENDING))
                .totalApproved(mentorshipRepository.countByStatus(MentorshipStatus.APPROVED))
                .totalRejected(mentorshipRepository.countByStatus(MentorshipStatus.REJECTED))
                .build();
    }
}
