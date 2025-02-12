package com.example.Entity;


import jakarta.persistence.*;

import javax.naming.Name;
import java.util.Set;

@Entity
public class Users {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String username;
   private String password;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<Role> roles;

   public Users() {
   }

   public Users(long id, String username, String password, Set<Role> roles) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.roles = roles;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      return "Users{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", roles=" + roles +
              '}';
   }
}
