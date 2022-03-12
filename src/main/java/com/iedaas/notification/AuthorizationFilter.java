package com.iedaas.notification;

import com.iedaas.notification.dao.CustomRepository;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class AuthorizationFilter {

    @Autowired
    private CustomRepository customRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    private static final String HEADER_STRING = "Authorization";

    @Value("${secretKey}")
    private String secret;

    public UUID authenticate(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String userEmail;

        if(token==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Token Found");
        }

        try {
            logger.info("Validating Jwt token :={}", token);
            userEmail = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .get("email", String.class);
        }
        catch (Exception e){
            logger.debug("Token :={} is invalid or expired", token);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token or token is expired");
        }
        logger.debug("Token :={}, userEmail :={}", token, userEmail);
        UUID userUid = customRepository.getUserUUID(userEmail);
        return userUid;
    }
}
