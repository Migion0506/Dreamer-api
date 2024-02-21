package org.beru.dreammer.web.config;

import java.util.Date;
import java.util.concurrent.*;

import org.beru.dreammer.env.JwtEnvs;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {
    private final Algorithm algorithm;
    private final JwtEnvs jwtEnv;

    public JwtUtil(JwtEnvs jwtEnv) {
        this.algorithm = Algorithm.HMAC256(jwtEnv.getJwtSecret());
        this.jwtEnv = jwtEnv;
    }
    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer(jwtEnv.getJwtIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+TimeUnit.DAYS.toMillis(Long.parseLong(jwtEnv.getJwtExpiration()))))
                .sign(algorithm);
    }
    public boolean isValid(String token){
        try{
            JWT.require(algorithm).build().verify(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public String getSub(String jwt){
        return JWT.require(algorithm).build().verify(jwt).getSubject();
    }
}
