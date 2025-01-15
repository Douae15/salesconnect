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
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentNumber;

    private String invoiceNumber;
    private double amount;
    private LocalDateTime dueDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "order_number")
    private Order order;

    @OneToMany(mappedBy = "invoice")
    private List<Payment> payments;
}

