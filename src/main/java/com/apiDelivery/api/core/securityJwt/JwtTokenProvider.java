//package com.apiDelivery.api.core.securityJwt;
//
//import com.apiDelivery.api.domain.exception.InvalidJwtAuthenticationException;
//import com.apiDelivery.api.domain.model.vo.TokenVO;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class JwtTokenProvider {
//
//    @Value("${security.jwt.token.secret-key:secret}")
//    private String secretKey = "secret";
//
//    @Value(("${security.jwt.token.expire-length:3600000}"))
//    private long validityInMilliSeconds = 3600000; //1hr
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    Algorithm algorithm = null;
//
//    // Inicializa antes do usuario depois deo spring instanciar
//    @PostConstruct
//    protected void init(){
//        // setar a chave para encriptar
//        secretKey  = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        algorithm = Algorithm.HMAC256(secretKey.getBytes());
//    }
//
//    // criação do Token
//    public TokenVO createAccessToken(String userName, List<String> roles){
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliSeconds);
//        var accessToken = getAccessToken(userName, roles, now, validity);
//        var refreshToken = getRefreshToken(userName, roles, now);
//
//        return new TokenVO(userName, true, now, validity, accessToken, refreshToken);
//    }
//
//    private String getAccessToken(String userName, List<String> roles, Date now, Date validity) {
//
//        //pegando a url do servidor
//        String issuerUrl = ServletUriComponentsBuilder
//                .fromCurrentContextPath().build().toUriString();
//
//        return JWT.create()
//                .withClaim("roles", roles)
//                .withIssuedAt(now)
//                .withExpiresAt(validity)
//                .withSubject(userName)
//                .withIssuer(issuerUrl)
//                .sign(algorithm)
//                .strip();
//    }
//
//
//    private String getRefreshToken(String userName, List<String> roles, Date now) {
//
//        Date getRefreshToken = new Date(now.getTime() + validityInMilliSeconds * 3);
//
//        return JWT.create()
//                .withClaim("roles", roles)
//                .withIssuedAt(now)
//                .withExpiresAt(getRefreshToken)
//                .withSubject(userName)
//                .sign(algorithm)
//                .strip();
//    }
//
//    // criando a autenticação
//    public Authentication getAutentication(String token){
//        DecodedJWT decodedJWT = decodedToken(token);
//        //carrega o usuario
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
//        return new UsernamePasswordAuthenticationToken
//                (userDetails, "", userDetails.getAuthorities());
//    }
//    // ler o token e setar em objeto
//    private DecodedJWT decodedToken(String token) {
//        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
//        JWTVerifier verifier = JWT.require(alg).build();
//
//        //decodificar o toklen
//        DecodedJWT decodedJWT = verifier.verify(token);
//        return decodedJWT;
//    }
//
//    //validar o toke quando usuario estiver autenticando na aplicação e ou não valido
//    public String resolveToken(HttpServletRequest req){
//        String bearerToken = req.getHeader("Authorization");
//        if(bearerToken != null && bearerToken.startsWith("Bearer ") ){
//            return bearerToken.substring("Bearer ".length());
//        }
//        return null;
//    }
//
//    // Metodo para validar o Token
//    public boolean validateToken(String token){
//        // Obter o token decodificado
//        DecodedJWT decodedJWT = decodedToken(token);
//        try {
//            // se for um token expirado entra no
//            if(decodedJWT.getExpiresAt().before(new Date())){
//               return false;
//            }
//            return true;
//        } catch (Exception e) {
//            throw new InvalidJwtAuthenticationException("Expire or invalid JWT TOKEN");
//        }
//    }
//
//
//}
