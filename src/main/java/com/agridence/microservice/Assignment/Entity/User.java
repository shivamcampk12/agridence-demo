package com.agridence.microservice.Assignment.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@ToString(of = {"Id", "username","password","fullname"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long Id;
    private String username;
    private String password;
    private String fullname;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Note> notes;
}