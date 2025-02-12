package com.example.Repo;

import com.example.DTO.UserResponseDTO;
import com.example.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    public Users findByUsername(String username);

//    List<UserResponseDTO> findUserByRoles(String rolename);
}
