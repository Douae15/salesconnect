package com.salesconnect.backend.controller;

import com.salesconnect.backend.dto.OpportunityDTO;
import com.salesconnect.backend.service.OpportunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@RequiredArgsConstructor
@Tag(name = "Opportunity", description = "The Opportunity API")
@Slf4j
public class OpportunityController {
    @Autowired
    private OpportunityService opportunityService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<OpportunityDTO>> getAllOpportunities() {
        log.info("Invoke Get All Opportunities end point");
        return ResponseEntity.ok(opportunityService.getAllOpportunities());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OpportunityDTO> getOpportunityById(@PathVariable Long id) {
        OpportunityDTO opportunityDTO = opportunityService.getOpportunityById(id);
        if (opportunityDTO != null) {
            return ResponseEntity.ok(opportunityDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<OpportunityDTO> addOpportunity(@RequestBody OpportunityDTO opportunityDTO) {
        OpportunityDTO createdOpportunity = opportunityService.addOpportunity(opportunityDTO);
        return ResponseEntity.ok(createdOpportunity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OpportunityDTO> updateOpportunity(@PathVariable Long id, @RequestBody OpportunityDTO opportunityDTO) {
        OpportunityDTO updatedOpportunity = opportunityService.updateOpportunity(id, opportunityDTO);
        if (updatedOpportunity != null) {
            return ResponseEntity.ok(updatedOpportunity);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
        if (opportunityService.deleteOpportunity(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
