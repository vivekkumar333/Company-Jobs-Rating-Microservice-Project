package com.learnwithtiwari.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnwithtiwari.userservice.entity.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Long> {
    Optional<Jobs> findByJobId(Long id);
}
