package com.learnwithtiwari.companyservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnwithtiwari.companyservice.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {
}
