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
public class OpportunityDTO {

    private Long opportunityId;
    private String name;
    private String stage;
    private double amount;
    private LocalDateTime createdAt;
    private LocalDateTime closeDate;
    private ContactDTO contactDTO;
    private ContractDTO contractDTO;
    private List<ProductDTO> productsDTO;
}

