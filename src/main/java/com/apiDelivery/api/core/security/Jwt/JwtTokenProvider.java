package com.apiDelivery.api.core.security.Jwt;

import com.apiDelivery.api.core.security.TokenVO;
import com.apiDelivery.api.domain.exception.InvalidJwtAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${secret.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMillseconds = 3600000; //1h

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    // permite executar uma ação logo apois a inicialização do spring
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    // criação do token
    public TokenVO createAccessToken(String userName, List<String> roles){
        Date now = new Date();
        Date validity =  new Date(now.getTime() + validityInMillseconds);
        var accessToken = getAccessToken(userName, roles, now, validity);
        var refreshToken = getRefreshToken(userName, roles, now);
        return new TokenVO(userName, true, now, validity, accessToken, refreshToken);
    }

    // gerar o accessToken
    private String getAccessToken(String userName, List<String> roles, Date now, Date validity) {

        // pego a URL do servidor
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(userName)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip()
                ;
    }
    // gera RefreshToken
    private String getRefreshToken(String userName, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + validityInMillseconds * 3);
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(userName)
                .sign(algorithm)
                .strip()
                ;
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;

    }

    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }

        return null;
    }

    public boolean validateToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        } catch (Exception e){
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }


}