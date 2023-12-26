package com.agridence.microservice.Assignment.Utility;

import com.agridence.microservice.Assignment.Vo.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
public class AuthenticationUtility implements HandlerInterceptor {
     private JWTUtility jwtUtility= new JWTUtility();
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (null != authHeader && authHeader.startsWith("Bearer")) {

            String JWTToken = jwtUtility.getJWTToken(httpServletRequest);

            //Here we are verifying if the user's JWT present in the user session
            ContextInformation cnxt = UserContext.httpSession.get(JWTToken);

            if (null != cnxt && cnxt.getJWT().equals(JWTToken)) {
                //Several more checks can be performed here
                return true;
            }
        }
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access! Try login");
            return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
