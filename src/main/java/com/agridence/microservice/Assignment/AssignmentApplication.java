package com.agridence.microservice.Assignment;
 import com.agridence.microservice.Assignment.Vo.UserContext;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 
 
 @SpringBootApplication
 public class AssignmentApplication {
        public static void main(String[] args) {
                SpringApplication.run(AssignmentApplication.class, args);
                UserContext.initilizeGlobalHttpSession();
        }
 }