package com.salesconnect.backend.dto;

import com.salesconnect.backend.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long userId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private String password;
    //private CompanyDTO companyDTO;
    private List<TaskDTO> tasksDTO;
    private List<ActivityDTO> activitiesDTO;
}

