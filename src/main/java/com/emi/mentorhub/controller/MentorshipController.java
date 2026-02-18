package com.emi.mentorhub.controller;

import com.emi.mentorhub.dto.MentorshipRequest;
import com.emi.mentorhub.dto.MentorshipResponse;
import com.emi.mentorhub.service.MentorshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentorships")
@RequiredArgsConstructor
public class MentorshipController {

    private final MentorshipService mentorshipService;

    @PostMapping
    public MentorshipResponse create(@RequestBody MentorshipRequest request,
                                     Authentication authentication) {

        return mentorshipService.createRequest(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public List<MentorshipResponse> getAll(Authentication authentication) {

        String email = authentication.getName();

        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_STUDENT")
                .replace("ROLE_", "");

        return mentorshipService.getByUser(email, role);
    }

    @PatchMapping("/{id}/approve")
    public MentorshipResponse approve(@PathVariable Long id,
                                      Authentication authentication) {

        return mentorshipService.approve(id, authentication.getName());
    }

    @PatchMapping("/{id}/reject")
    public MentorshipResponse reject(@PathVariable Long id,
                                     Authentication authentication) {

        return mentorshipService.reject(id, authentication.getName());
    }
}
