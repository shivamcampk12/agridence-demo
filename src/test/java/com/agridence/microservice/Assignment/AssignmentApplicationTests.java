package com.agridence.microservice.Assignment;
 
 import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
 
@SpringBootTest(classes = AssignmentApplication.class)
 class AssignmentApplicationTests {
 

       @Autowired
       private AssignmentApplication assignmentApplication;
        @Test
        void contextLoads() {

               //Testing the application loading
               assertThat(assignmentApplication).isNotNull();
        }
 
 }