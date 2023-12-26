package com.agridence.microservice.Assignment.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {
    private Long Id;
    private String title;
    private String description;
    private Long userId;
}
