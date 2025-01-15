package com.salesconnect.backend.config.request;



import com.salesconnect.backend.dto.CompanyDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(min = 3, max = 20)
  private String name;
  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  private String phone;
  private String username;
  private CompanyDTO company;
  //private List<Role> role;

}
