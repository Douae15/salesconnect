package com.salesconnect.backend.repository;

import com.salesconnect.backend.entity.Company;
import com.salesconnect.backend.entity.User;
import com.salesconnect.backend.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByRole(Role role);
    List<User> findByCompany(Company company);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);


}

