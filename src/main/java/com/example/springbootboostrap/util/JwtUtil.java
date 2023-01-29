package com.example.springbootboostrap.util;

import com.example.springbootboostrap.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Set;

public class JwtUtil {
    private static final String secretKey = "6975a8b0cdde097a1c9b9a9b9a9d9d9d9a9a9a9a9a9b9a9b9a9c9c9c9c9c9c9c";
    private static Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    private static final long validityInMilliseconds = 3600000;

    public static String createToken(String username, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public static void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {

        } catch (UnsupportedJwtException ex) {

        } catch (MalformedJwtException ex) {

        } catch (IllegalArgumentException ex) {

        }
    }

    public static String getUsernameFromToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        Claims body = claims.getBody();
        return body.getSubject();
    }
}

