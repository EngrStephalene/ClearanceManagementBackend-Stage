package com.clearance.management.backend.repository;

import com.clearance.management.backend.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    List<Announcement> findAllByOrderByReportedDateDesc();
}
