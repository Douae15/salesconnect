package com.salesconnect.backend.service;

import com.salesconnect.backend.dto.OpportunityDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpportunityService {
    List<OpportunityDTO> getAllOpportunities();
    OpportunityDTO getOpportunityById(Long id);
    OpportunityDTO addOpportunity(OpportunityDTO opportunityDTO);
    OpportunityDTO updateOpportunity(Long id, OpportunityDTO opportunityDTO);
    boolean deleteOpportunity(Long id);
}
