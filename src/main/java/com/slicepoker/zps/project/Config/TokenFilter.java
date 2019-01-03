package com.slicepoker.zps.project.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * @author Zps
 * @date 2018/12/26 11:32
 **/
public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException,ServletException{

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) rep;

        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req,rep);
        }else{
            if (authHeader == null || !authHeader.startsWith("Bearer")){
                throw new ServerException("无效或缺失授权");
            }
            final String token = authHeader.substring(7);
            if (token!=null)
//
//            try {
//                final Claims claims =Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
//                request.setAttribute("claims", claims);
//
//            }catch (final SignatureException e) {
//                throw new ServletException("无效的token");
//            }

            chain.doFilter(req,rep);
        }
    }
}
