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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    private String name;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "order")
    private List<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
}

