package com.salesconnect.backend.entity.abstractEntity;

import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class EntityListener {

    public EntityListener(){}

    @PrePersist
    public void setCreatedAt(final AbstractEntity entity) {
        final LocalDateTime date = LocalDateTime.now();
        entity.setCreatedAt(date);
        entity.setModifiedAt(date);
        entity.setCreatedBy(getCurrentUser());
    }

    @PreUpdate
    public void setModifiedAt(AbstractEntity entity) {
        final LocalDateTime date = LocalDateTime.now();
        entity.setModifiedAt(date);
        entity.setModifiedBy(getCurrentUser());
    }


    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }


}

