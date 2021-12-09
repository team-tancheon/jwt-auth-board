package com.hancom.authserver.filter;

import com.hancom.authserver.domain.User;
import com.hancom.authserver.util.JwtUtil;
import com.hancom.authserver.config.VerifyResult;
import com.hancom.authserver.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(jwtUtil.AUTH_HEADER);
        if(token == null || !token.startsWith(jwtUtil.BEARER)){
            // 1차 검증: null, prefix 검증
            chain.doFilter(request, response);
            return;
        }

        VerifyResult result = jwtUtil.verify(token.substring(jwtUtil.BEARER.length()));
        if(result.isResult()){
            User user = userService.findUserById(result.getUserId());

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        super.doFilterInternal(request, response, chain);
    }
}
