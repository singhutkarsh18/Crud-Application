package com.example.testproject.Repository;

import com.example.testproject.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    Boolean existsByEmailId(String emailId);
}
