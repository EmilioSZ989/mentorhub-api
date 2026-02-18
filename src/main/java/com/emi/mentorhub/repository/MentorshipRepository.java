package com.emi.mentorhub.repository;

import com.emi.mentorhub.entity.Mentorship;
import com.emi.mentorhub.entity.MentorshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentorshipRepository extends JpaRepository<Mentorship, Long> {

    // Consultas por usuario
    List<Mentorship> findByMentor_Email(String email);

    List<Mentorship> findByStudent_Email(String email);

    Optional<Mentorship> findByIdAndMentor_Email(Long id, String email);

    // Métricas ADMIN
    long countByStatus(MentorshipStatus status);
}
