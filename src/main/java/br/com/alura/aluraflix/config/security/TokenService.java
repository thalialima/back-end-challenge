package br.com.alura.aluraflix.config.security;

import br.com.alura.aluraflix.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    //@Value serve para injetar parâmetros do application.properties
    @Value("${aluraflix.jwt.expiration}")
    private String expiration;

    @Value("${aluraflix.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User tokenOwner = (User) authentication.getPrincipal();
        Date today = new Date();
        Date dataExpiration = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("AluraFlix API")
                .setSubject(tokenOwner.getId().toString())
                //data de geração de token
                .setIssuedAt(today)
                .setExpiration(dataExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }




}
