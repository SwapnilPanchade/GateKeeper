package com.example.Controller;

import com.example.DTO.UserRegisterDTO;
import com.example.DTO.UserResponseDTO;
import com.example.Entity.Role;
import com.example.Entity.Users;
import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

//    private final UserService userService;
//
//    UserController(UserService userService){
//        this.userService = userService;
//    }
//
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegisterDTO user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable long id, @RequestBody UserRegisterDTO user){
        return userService.updateUser(id,user);
    }

//    //! get user by role
//    @GetMapping("/role/{rolename}")
//    public List<UserResponseDTO> getByRole(@PathVariable String rolename){
//        return userService.getByRoleName(rolename);
//    }
//
    @DeleteMapping("/{id}")
    public String  delete(@PathVariable long id){
        return userService.delete(id);
    }
}
