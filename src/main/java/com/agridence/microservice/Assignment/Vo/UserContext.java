package com.agridence.microservice.Assignment.Vo;

import com.agridence.microservice.Assignment.Utility.ContextInformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
@Setter
@AllArgsConstructor
public abstract class UserContext {
    public static Map<String, ContextInformation> httpSession = new HashMap<>();

    public static void initilizeGlobalHttpSession(){
        ContextInformation contextInformation = new ContextInformation();
        contextInformation.setJWT("JWT");
        contextInformation.setFullname("SpringSecurity");
        contextInformation.setLoginTime(new Date(System.currentTimeMillis()));
        contextInformation.setUsername("SpringSecurity");
        contextInformation.setId(10000000L);
        String[] roles={"USER","ADMIN"};
        contextInformation.setRoles(Arrays.asList(roles));

        httpSession.put("START_CONTEXT",contextInformation);
    }
}