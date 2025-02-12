package com.example.Service;

import com.example.DTO.UserRegisterDTO;
import com.example.DTO.UserResponseDTO;
import com.example.Entity.Role;
import com.example.Entity.RoleType;
import com.example.Entity.Users;
import com.example.Repo.RoleRepo;
import com.example.Repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    public UserService (UserRepo userRepo, RoleRepo roleRepo){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
    public UserResponseDTO registerUser(UserRegisterDTO userdto){
        Role role = roleRepo.findByName(RoleType.valueOf(userdto.getRole().toUpperCase())).orElseThrow(() -> new RuntimeException("user not found"));
        Users user = new Users();
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword());
        user.setRoles(Set.of(role));

        userRepo.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(userdto.getRole());
        return response;
    }

    public UserResponseDTO getUserById(long id) {
        Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user does not exists"));
        UserResponseDTO res = new UserResponseDTO();
        res.setUsername(user.getUsername());
        res.setRole(user.getRoles().iterator().next().getName().name());
        res.setId(user.getId());
        return res;
    }
}
