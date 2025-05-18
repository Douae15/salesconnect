package com.salesconnect.backend.service;

import com.salesconnect.backend.dto.ContractDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractService {
    List<ContractDTO> getAllContracts();
    ContractDTO getContractById(Long id);
    ContractDTO addContract(ContractDTO contractDTO);
    ContractDTO updateContract(Long id, ContractDTO contractDTO);
    boolean deleteContract(Long id);
}
