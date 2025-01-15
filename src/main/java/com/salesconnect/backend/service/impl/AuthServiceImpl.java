package com.salesconnect.backend.service.impl;

import com.salesconnect.backend.config.request.RegisterRequest;
import com.salesconnect.backend.dto.UserDTO;
import com.salesconnect.backend.entity.Company;
import com.salesconnect.backend.entity.User;
import com.salesconnect.backend.entity.enums.Role;
import com.salesconnect.backend.repository.CompanyRepository;
import com.salesconnect.backend.repository.UserRepository;
import com.salesconnect.backend.service.AuthService;
import com.salesconnect.backend.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    public CompanyRepository companyRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserTransformer userTransformer;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(RegisterRequest request) {
        // Créer l'entreprise
        Company company = new Company();
        company.setName(request.getCompany().getName());
        company.setAddress(request.getCompany().getAddress());
        company.setPhone(request.getCompany().getPhone());
        company.setEmail(request.getCompany().getEmail());
        company.setIndustry(request.getCompany().getIndustry());
        Company savedCompany = companyRepository.save(company);

        // Créer l'utilisateur Admin d'Entreprise
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN_COMPANY);
        user.setCompany(savedCompany);

        User savedUser = userRepository.save(user);

        // Convertir l'utilisateur en DTO et le retourner
        return userTransformer.toDTO(savedUser);
    }

}
