package com.everyhealth.backend.domain.user.repository;

import com.everyhealth.backend.domain.user.domain.PhysicalInfomation;
import com.everyhealth.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysicalInfomationRepository extends JpaRepository<PhysicalInfomation, Long> {

}
