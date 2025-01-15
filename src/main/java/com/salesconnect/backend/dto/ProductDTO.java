package com.salesconnect.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;
    private String name;
    private String description;
    private double price;
    private List<OpportunityDTO> opportunitiesDTO;
}

