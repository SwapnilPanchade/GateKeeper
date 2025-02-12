package com.example.Service;

import com.example.DTO.UserRegisterDTO;
import com.example.DTO.UserResponseDTO;
import com.example.Entity.Role;
import com.example.Entity.RoleType;
import com.example.Entity.Users;
import com.example.Repo.RoleRepo;
import com.example.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

//    private final UserRepo userRepo;
//    private final RoleRepo roleRepo;
//
//    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
//        this.userRepo = userRepo;
//        this.roleRepo = roleRepo;
//    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public UserResponseDTO registerUser(UserRegisterDTO userdto) {
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

    public UserResponseDTO updateUser(long id, UserRegisterDTO user) {

        Users users = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user with id " + id + " not found call from userService "));
        users.setUsername(user.getUsername());
        userRepo.save(users);
        UserResponseDTO res = new UserResponseDTO();
        res.setUsername(user.getUsername());
        res.setRole(user.getRole());
        res.setId(users.getId());
        return res;
    }

//    public List<UserResponseDTO> getByRoleName(String rolename) {
//        return userRepo.findUserByRoles(rolename).stream().map(UserResponseDTO::new).collect(Collectors.toList());
//    }

    public String delete(long id) {
        userRepo.deleteById(id);
        return "user deleted successfully";
    }
}
