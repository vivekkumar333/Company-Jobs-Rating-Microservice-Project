package com.learnwithtiwari.raitingservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnwithtiwari.raitingservice.entity.ReviewRaiting;

public interface ReviewsRepo extends JpaRepository<ReviewRaiting, Long> {
    Optional<ReviewRaiting> findByReviewId(Long id);

	Optional<List<ReviewRaiting>> findByCompanyId(Long id);
}
