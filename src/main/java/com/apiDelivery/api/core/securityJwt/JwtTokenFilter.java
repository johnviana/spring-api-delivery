//package com.apiDelivery.api.core.securityJwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import java.io.IOException;
//
//public class JwtTokenFilter extends GenericFilterBean {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    // esse filter vai ser executada em cada requisição x
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//
//        if(token != null && jwtTokenProvider.validateToken(token)){
//            Authentication auth = jwtTokenProvider.getAutentication(token);
//            if(auth != null){
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
