package com.agridence.microservice.Assignment.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest implements Serializable {
    private Long Id;
    private String username;
    private String password;
    private String fullname;
}