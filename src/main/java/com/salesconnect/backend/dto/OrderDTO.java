package com.salesconnect.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderNumber;
    private String name;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private CompanyDTO companyDTO;
    private List<InvoiceDTO> invoicesDTO;
    private ContractDTO contractDTO;
}

