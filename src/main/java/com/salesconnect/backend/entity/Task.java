package com.salesconnect.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String subject;
    private String description;
    private String type;
    private LocalDateTime dueDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
