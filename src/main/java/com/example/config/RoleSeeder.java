package com.example.config;

import com.example.Entity.Role;
import com.example.Entity.RoleType;
import com.example.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepo roleRepo;

    public RoleSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) {
        if (roleRepo.count() == 0) {  // Only insert if table is empty
            Arrays.stream(RoleType.values()).forEach(roleType -> {
                Role role = new Role();
                role.setName(roleType);
                roleRepo.save(role);
            });
            System.out.println("✅ Roles initialized in database!");
        }
    }
}
