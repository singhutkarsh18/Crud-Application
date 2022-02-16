package com.example.testproject.Repository;


import com.example.testproject.Model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups,Long> {
}
