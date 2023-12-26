package com.agridence.microservice.Assignment.service;

import com.agridence.microservice.Assignment.Entity.User;
import com.agridence.microservice.Assignment.Repository.UserRepository;
import com.agridence.microservice.Assignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
    	
        // Arranging the objects
        User userToSave = new User(null,"testingTesting",null,"Shivam Verma", null);
        User savedUser = new User(null,"testingTesting",null,null,null);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.saveUser(userToSave);

        // Assert
        verify(userRepository).save(userToSave);
        assertEquals(savedUser, result);
    }
}