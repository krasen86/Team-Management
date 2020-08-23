package se.team_management.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import se.team_management.servises.EmployeeDetailService;
import se.team_management.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmployeeDetailService employeeDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String jwToken = "";
        String username = "";
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            jwToken = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwToken);
        }
        if (username != null && !username.equals("") && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = employeeDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        String origin = httpServletRequest.getHeader("origin");
        origin = (origin == null || origin.equals("")) ? "null" : origin;
        httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, DELETE, OPTIONS");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.addHeader("Access-Control-Allow-Headers",
                "Authorization, origin, content-type, accept, x-requested-with");

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
