package com.netcrackerTask.backend.jwt;

import com.netcrackerTask.backend.business.service.implementations.LogService;
import com.netcrackerTask.backend.business.service.implementations.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    public LogService logService;

    @Autowired
    public JwtUtils(final LogService logService){
        this.logService = logService;
    }

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logService.writeLog("{'error': 'Invalid JWT signature'}","JWTError");
        } catch (MalformedJwtException e) {
            logService.writeLog("{'error': 'Invalid JWT token'}","JWTError");
        } catch (ExpiredJwtException e) {
            logService.writeLog("{'error': 'JWT token is expired'}","JWTError");
        } catch (UnsupportedJwtException e) {
            logService.writeLog("{'error': 'JWT token is unsupported'}","JWTError");
        } catch (IllegalArgumentException e) {
            logService.writeLog("{'error': 'JWT claims string is empty}","JWTError");
        }

        return false;
    }
}
