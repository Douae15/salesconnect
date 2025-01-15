package com.salesconnect.backend.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Création du token JWT en utilisant l'email comme identifiant'
    public String createToken(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // Expiration dans 1 heure

        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Ici, l'email est utilisé comme identifiant
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    // Extraction du token depuis l'en-tête de la requête HTTP
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Validation du token pour vérifier s'il est valide et non expiré
    public boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        } catch (SignatureException e) {
            log.error("There is an error with the signature of your token ");
        }
        return false;
    }

    // Extraction de l'email (anciennement le nom d'utilisateur) à partir du token
    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Renvoie l'email
    }
}
