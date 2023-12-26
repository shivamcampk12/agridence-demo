package com.agridence.microservice.Assignment.Utility;

import com.agridence.microservice.Assignment.Vo.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtility implements Serializable {

    private static final long serialVersionUID = 234234523523L;

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    private static String JWTSecretSalt = "AGRIDENCEASSIGNMENT";

    public static Object getInstance() {
        return new JWTUtility();
    }


    //Fetching username from the JWT
    public String fetchUsernameFromToken(String jwtToken) {
        return fetchClaimFromToken(jwtToken, Claims::getSubject);
    }

    //Getting expiration date of JWT
    public Date fetchExpirationDateFromToken(String jwtToken) {
        return fetchClaimFromToken(jwtToken, Claims::getExpiration);
    }

    //Checking if the token has expired
    public Boolean checkIsTokenExpired(String jwtToken) {
        final Date expiration = fetchExpirationDateFromToken(jwtToken);
        return expiration.before(new Date());
    }

    //Validating the JWT
    public Boolean checkIsTokenValid(String jwtToken, LoginRequest loginRequest) {
        final String userName = fetchUsernameFromToken(jwtToken);
        return (userName.equals(loginRequest.getUsername()) && !checkIsTokenExpired(jwtToken));
    }

    public boolean checkIsTokenValid(String jwtToken) {
        try {

            Jwts.parser().setSigningKey(JWTSecretSalt).parse(jwtToken);

            return true;
        } catch (SignatureException e) {

        } catch (MalformedJwtException e) {

        } catch (ExpiredJwtException e) {

        } catch (UnsupportedJwtException e) {

        } catch (IllegalArgumentException e) {

        }
        return false;
    }


    //Generating JWT for user
    public String generateToken(LoginRequest loginRequest) {
        Map<String, Object> claims = new HashMap<>();
        return createJWTToken(claims, loginRequest.getUsername());
    }

    public String generateToken(String username , List<String> roles) {
        return Jwts.builder().setSubject(username).claim("role",roles).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(JWT_TOKEN_VALIDITY, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, JWTSecretSalt).compact();
    }

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(JWT_TOKEN_VALIDITY, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, JWTSecretSalt).compact();
    }

    public String getJWTToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
    private String createJWTToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, JWTSecretSalt).compact();
    }
    public <T> T fetchClaimFromToken(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = fetchAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }
    private Claims fetchAllClaimsFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(JWTSecretSalt).parseClaimsJws(jwtToken).getBody();
    }
}
