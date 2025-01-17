package com.salesconnect.backend.service.impl;

import com.salesconnect.backend.dto.OpportunityDTO;
import com.salesconnect.backend.entity.Opportunity;
import com.salesconnect.backend.repository.OpportunityRepository;
import com.salesconnect.backend.service.OpportunityService;
import com.salesconnect.backend.transformer.OpportunityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    private final OpportunityTransformer opportunityTransformer = new OpportunityTransformer();

    @Override
    public List<OpportunityDTO> getAllOpportunities() {
        List<Opportunity> opportunities = opportunityRepository.findAll();
        return opportunityTransformer.toDTOList(opportunities);
    }

    @Override
    public OpportunityDTO getOpportunityById(Long id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opportunity not found with id: " + id));
        return opportunityTransformer.toDTO(opportunity);
    }

    @Override
    public OpportunityDTO addOpportunity(OpportunityDTO opportunityDTO) {
        Opportunity opportunity = opportunityTransformer.toEntity(opportunityDTO);
        Opportunity savedOpportunity = opportunityRepository.save(opportunity);
        return opportunityTransformer.toDTO(savedOpportunity);
    }

    @Override
    public OpportunityDTO updateOpportunity(Long id, OpportunityDTO opportunityDTO) {
        Optional<Opportunity> existingOpportunity = opportunityRepository.findById(id);
        if (existingOpportunity.isPresent()) {
            Opportunity opportunity = opportunityTransformer.toEntity(opportunityDTO);
            opportunity.setOpportunityId(id);
            Opportunity updatedOpportunity = opportunityRepository.save(opportunity);
            return opportunityTransformer.toDTO(updatedOpportunity);
        }
        return null;
    }

    @Override
    public boolean deleteOpportunity(Long id) {
        if (opportunityRepository.existsById(id)) {
            opportunityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
