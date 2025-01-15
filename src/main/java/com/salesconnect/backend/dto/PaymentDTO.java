package com.salesconnect.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long paymentNumber;
    private LocalDateTime paymentDate;
    private double amount;
    private String status;
    private String method;
    private InvoiceDTO invoiceDTO;
}
