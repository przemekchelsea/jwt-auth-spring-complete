package com.example.jwt.entity;
//import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank
    //Dzięki temu możesz używać @Valid w @RequestBody, np.:
   //public ResponseEntity<?> register(@Valid @RequestBody User user)
    @Column(unique = true)
    private String username;
    //@NotBlank
    private String password;

    private String role;
    
    public User() {}

   public User(Long id, String username, String password, String role) {
     this.id = id;
     this.username = username;
     this.password = password;
     this.role = role;
   }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}