package com.canary.beaches.repository;

import com.canary.beaches.model.Beach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeachRepository extends JpaRepository<Beach, String> {
    Beach findById(Long id);
}