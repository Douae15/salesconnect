package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.*;
import com.salesconnect.backend.entity.*;

public class OpportunityTransformer extends Transformer<Opportunity, OpportunityDTO>{
    @Override
    public Opportunity toEntity(OpportunityDTO opportunityDTO) {
        if (opportunityDTO == null)
            return null;
        else {
            Transformer<Contact, ContactDTO> contactTransformer = new ContactTransformer();
            Transformer<Contract, ContractDTO> contractTransformer = new ContractTransformer();
            Transformer<Product, ProductDTO> productsTransformer = new ProductTransformer();
            Opportunity opportunity = new Opportunity();
            opportunity.setOpportunityId(opportunityDTO.getOpportunityId());
            opportunity.setName(opportunityDTO.getName());
            opportunity.setAmount(opportunityDTO.getAmount());
            opportunity.setStage(opportunityDTO.getStage());
            opportunity.setCloseDate(opportunityDTO.getCloseDate());
            opportunity.setCreatedAt(opportunityDTO.getCreatedAt());
            opportunity.setContact(contactTransformer.toEntity(opportunityDTO.getContactDTO()));
            opportunity.setContract(contractTransformer.toEntity(opportunityDTO.getContractDTO()));
            opportunity.setProducts(productsTransformer.toEntityList(opportunityDTO.getProductsDTO()));
            return opportunity;
        }
    }

    @Override
    public OpportunityDTO toDTO(Opportunity opportunity) {
        if (opportunity == null)
            return null;
        else {
            Transformer<Contact, ContactDTO> contactTransformer = new ContactTransformer();
            Transformer<Contract, ContractDTO> contractTransformer = new ContractTransformer();
            Transformer<Product, ProductDTO> productsTransformer = new ProductTransformer();
            OpportunityDTO opportunityDTO = new OpportunityDTO();
            opportunityDTO.setOpportunityId(opportunity.getOpportunityId());
            opportunityDTO.setName(opportunity.getName());
            opportunityDTO.setAmount(opportunity.getAmount());
            opportunityDTO.setStage(opportunity.getStage());
            opportunityDTO.setCloseDate(opportunity.getCloseDate());
            opportunityDTO.setCreatedAt(opportunity.getCreatedAt());
            opportunityDTO.setContactDTO(contactTransformer.toDTO(opportunity.getContact()));
            opportunityDTO.setContractDTO(contractTransformer.toDTO(opportunity.getContract()));
            opportunityDTO.setProductsDTO(productsTransformer.toDTOList(opportunity.getProducts()));
            return opportunityDTO;
        }
    }
}
