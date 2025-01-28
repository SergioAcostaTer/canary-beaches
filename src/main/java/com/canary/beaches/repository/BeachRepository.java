package com.canary.beaches.repository;

import com.canary.beaches.model.Beach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BeachRepository extends JpaRepository<Beach, Long>, JpaSpecificationExecutor<Beach> {
}