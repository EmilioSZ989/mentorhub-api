package com.emi.mentorhub.service.impl;

import com.emi.mentorhub.dto.MentorshipRequest;
import com.emi.mentorhub.dto.MentorshipResponse;
import com.emi.mentorhub.entity.*;
import com.emi.mentorhub.exception.BusinessException;
import com.emi.mentorhub.exception.ResourceNotFoundException;
import com.emi.mentorhub.repository.MentorshipRepository;
import com.emi.mentorhub.repository.UserRepository;
import com.emi.mentorhub.service.MentorshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MentorshipServiceImpl implements MentorshipService {

    private final MentorshipRepository mentorshipRepository;
    private final UserRepository userRepository;

    @Override
    public MentorshipResponse createRequest(MentorshipRequest request, String studentEmail) {

        User mentor = userRepository.findById(request.getMentorId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor no encontrado"));

        if (mentor.getRole() != Role.MENTOR) {
            throw new BusinessException("El usuario seleccionado no es mentor");
        }

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        if (student.getRole() != Role.STUDENT) {
            throw new BusinessException("Solo los estudiantes pueden solicitar mentorías");
        }

        Mentorship mentorship = Mentorship.builder()
                .mentor(mentor)
                .student(student)
                .status(MentorshipStatus.PENDING)
                .build();

        return mapToResponse(mentorshipRepository.save(mentorship));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MentorshipResponse> getByUser(String email, String role) {

        if ("ADMIN".equals(role)) {
            return mentorshipRepository.findAll()
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
        }

        if ("MENTOR".equals(role)) {
            return mentorshipRepository.findByMentor_Email(email)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
        }

        return mentorshipRepository.findByStudent_Email(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MentorshipResponse approve(Long id, String mentorEmail) {

        Mentorship mentorship = mentorshipRepository
                .findByIdAndMentor_Email(id, mentorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada"));

        if (mentorship.getStatus() != MentorshipStatus.PENDING) {
            throw new BusinessException("Solo se pueden aprobar solicitudes pendientes");
        }

        mentorship.setStatus(MentorshipStatus.APPROVED);

        return mapToResponse(mentorship);
    }

    @Override
    public MentorshipResponse reject(Long id, String mentorEmail) {

        Mentorship mentorship = mentorshipRepository
                .findByIdAndMentor_Email(id, mentorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada"));

        if (mentorship.getStatus() != MentorshipStatus.PENDING) {
            throw new BusinessException("Solo se pueden rechazar solicitudes pendientes");
        }

        mentorship.setStatus(MentorshipStatus.REJECTED);

        return mapToResponse(mentorship);
    }

    private MentorshipResponse mapToResponse(Mentorship m) {
        return MentorshipResponse.builder()
                .id(m.getId())
                .mentorEmail(m.getMentor().getEmail())
                .studentEmail(m.getStudent().getEmail())
                .status(m.getStatus())
                .createdAt(m.getCreatedAt())
                .build();
    }
}
