package com.agridence.microservice.Assignment.Utility;
import lombok.*;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContextInformation {
    private String JWT;
    private String username;
    private Long Id;
    private String fullname;
    private Date loginTime;
    private List<String> roles;
}
