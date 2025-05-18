package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.*;
import com.salesconnect.backend.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ProductTransformer extends Transformer<Product, ProductDTO>{
    @Override
    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null)
            return null;
        else {
            Transformer<Opportunity, OpportunityDTO> opportunityTransformer = new OpportunityTransformer();
            Product product = new Product();
            product.setProductId(productDTO.getProductId());
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setQuantity(productDTO.getQuantity());
            //product.setOpportunities(opportunityTransformer.toEntityList(productDTO.getOpportunitiesDTO()));

            return product;

        }
    }

    @Override
    public ProductDTO toDTO(Product product) {
        if (product == null)
            return null;
        else {
            Transformer<Opportunity, OpportunityDTO> opportunityTransformer = new OpportunityTransformer();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setQuantity(product.getQuantity());
            //productDTO.setOpportunitiesDTO(opportunityTransformer.toDTOList(product.getOpportunities()));
            return productDTO;
        }
    }
}
