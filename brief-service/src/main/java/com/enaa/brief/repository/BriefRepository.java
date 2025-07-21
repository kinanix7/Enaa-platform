package com.enaa.brief.repository;

import com.enaa.brief.entity.Brief;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BriefRepository extends JpaRepository<Brief, Long> {
}
