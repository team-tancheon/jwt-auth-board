package com.br.login.web.config;

import com.br.login.web.domain.User;
import com.br.login.web.service.UserService;
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

public class JwtCheckFilter extends BasicAuthenticationFilter {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtCheckFilter(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
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

        VerifyResult result = jwtUtil.verify(token.split(" ")[2]);
        if(result.isResult()){
            User user = userService.findUser(result.getUserId()).get();

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        super.doFilterInternal(request, response, chain);
    }
}
