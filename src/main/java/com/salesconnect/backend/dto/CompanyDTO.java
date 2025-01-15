package com.salesconnect.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {

    private Long companyId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String industry;
    private LocalDateTime createdAt;
    private List<UserDTO> usersDTO;
    private List<ContactDTO> contactsDTO;

}
