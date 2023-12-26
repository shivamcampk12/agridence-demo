package com.agridence.microservice.Assignment.Controller;

import com.agridence.microservice.Assignment.Utility.ContextInformation;
import com.agridence.microservice.Assignment.Utility.JWTUtility;
import com.agridence.microservice.Assignment.Utility.PasswordEncodingUtility;
import com.agridence.microservice.Assignment.Vo.*;
import com.agridence.microservice.Assignment.Entity.User;
import com.agridence.microservice.Assignment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private UserContext userContext;

    private JWTUtility jwtUtility= new JWTUtility();

    private PasswordEncodingUtility passwordEncodingUtility;

    @PostMapping("auth/register")
    public ResponseEntity<?> saveUser(@RequestBody RegisterRequest registerRequest) {

        passwordEncodingUtility = new PasswordEncodingUtility();

        String passwordEncoded = passwordEncodingUtility.generateEncodedPassword(registerRequest.getPassword());

        User registerUser = new User();
        registerUser.setId(null);
        registerUser.setFullname(registerRequest.getFullname());
        registerUser.setUsername(registerRequest.getUsername());
        registerUser.setPassword(passwordEncoded);
        registerRequest.setPassword(passwordEncoded);
        User savedUser = userService.saveUser(registerUser);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        //Checking IF Username or password is null
        if(null == loginRequest.getUsername() || null == loginRequest.getPassword()){
            return new ResponseEntity<>("invalid username/password", HttpStatus.UNAUTHORIZED);
        }else {
            Optional<User> user = userService.findByUsername(loginRequest.getUsername());
            User user1 = user.orElse(null);

            //Checking IF there's any user available with the username
            if (null == user1) {
                return new ResponseEntity<>("invalid username/password", HttpStatus.UNAUTHORIZED);
            } else {
                //Generating encoded passwords
                passwordEncodingUtility = new PasswordEncodingUtility();

                String passwordEncoded = passwordEncodingUtility.generateEncodedPassword(loginRequest.getPassword());

                if (!passwordEncoded.equals(user1.getPassword())) {
                    return new ResponseEntity<>("invalid username/password", HttpStatus.UNAUTHORIZED);
                } else {
                    String[] roleArray = {"USER"};
                    List<String> roles = Arrays.asList(roleArray);

                    //Generating JWT Token
                    String jwtToken = jwtUtility
                            .generateToken(loginRequest.getUsername(), roles);

                    userContext.httpSession.put(jwtToken,
                            new ContextInformation(jwtToken,
                                    user1.getUsername(),
                                    user1.getId(),
                                    user1.getFullname(),
                                    new Date(System.currentTimeMillis()),
                                    roles));
                    return new ResponseEntity<>(new BearerToken("Bearer " + jwtToken,  "JWT"), HttpStatus.OK);
                }
            }
        }
    }



    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/findall")
    public List<User> getUsers() {
        return userService.findAll();
    }

}
