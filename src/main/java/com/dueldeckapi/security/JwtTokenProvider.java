package com.dueldeckapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    // Chave secreta usada para assinar os tokens. Guarde-a com segurança!
    private final String jwtSecret = "sua-chave-secreta-aqui";

    // Tempo de expiração do token em milissegundos (ex.: 1 hora)
    private final long jwtExpirationInMs = 3600000;

    // Gera um token JWT para um determinado usuário (por exemplo, usando o username)
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Extrai o username (subject) do token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Valida o token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            // Você pode logar o erro ou lançar uma exceção personalizada conforme o caso
            return false;
        }
    }
}