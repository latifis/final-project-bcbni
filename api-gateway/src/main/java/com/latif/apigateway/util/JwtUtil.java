package com.latif.apigateway.util;

import com.latif.apigateway.exception.JwtTokenMalformedException;
import com.latif.apigateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

@Component
public class JwtUtil {

    private String jwtSecret = "ceksatuduatigaempatlimaenamtujudelapansembilan";
    byte[] keyData = jwtSecret.getBytes(Charset.forName("UTF-8"));
    private final Key key = new SecretKeySpec(keyData, SignatureAlgorithm.HS256.getJcaName());

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}