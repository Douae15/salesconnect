package com.salesconnect.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    private String name;
    private Long contractNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String contractTerm;

    @OneToOne(mappedBy = "contract")
    private Opportunity opportunity;

    @OneToMany(mappedBy = "contract")
    private List<Order> orders;
}

