package com.emi.mentorhub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminStatsResponse {

    private long totalUsers;
    private long totalAdmins;
    private long totalMentors;
    private long totalStudents;

    private long totalMentorships;
    private long totalPending;
    private long totalApproved;
    private long totalRejected;
}
