package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        // Assuming you have a way to get the current user
        createdBy = getCurrentUser();
        updatedBy = createdBy; // Initially the same as createdBy
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        // Update the updatedBy field with the current user
        updatedBy = getCurrentUser();
    }

    protected String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && authentication.getPrincipal() instanceof UserDetails)
                ? ((UserDetails) authentication.getPrincipal()).getUsername()
                : "anonymous";
    }
}