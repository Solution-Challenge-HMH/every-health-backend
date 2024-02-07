package com.everyhealth.backend.domain.calendar.repository;

import com.everyhealth.backend.domain.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
