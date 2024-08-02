package com.example.demo.repository;

import com.example.demo.models.TestBlueprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBluePrintRepository extends JpaRepository<TestBlueprint, Long> {
}
