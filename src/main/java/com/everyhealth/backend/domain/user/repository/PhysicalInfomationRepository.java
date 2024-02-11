package com.everyhealth.backend.domain.user.repository;

import com.everyhealth.backend.domain.user.domain.PhysicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalInfomationRepository extends JpaRepository<PhysicalInformation, Long> {}
