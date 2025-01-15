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
@Table(name = "opportunity")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opportunityId;

    private String name;
    private String stage;
    private double amount;
    private LocalDateTime createdAt;
    private LocalDateTime closeDate;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToMany
    @JoinTable(
            name = "opportunity_product",
            joinColumns = @JoinColumn(name = "opportunity_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}

