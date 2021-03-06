package com.latif.apigateway.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

@Log4j2
@Component
public class JwtTokenProvider {
    String keyString = "kuncinyarahasia";
    byte[] keyData = keyString.getBytes(Charset.forName("UTF-8"));
    private final Key key = new SecretKeySpec(keyData, SignatureAlgorithm.HS256.getJcaName());

    @Value("${jwt.expiration-time}")
    private Long expiration;

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }


    public boolean validateToken(String token) {
        try {
            System.out.println(key);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid Jwt Signature: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid Jwt Token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired Jwt Token: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported Jwt token: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Jwt claim string is empty: {}", ex.getMessage());
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
}
