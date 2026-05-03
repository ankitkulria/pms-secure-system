package com.training.controller;

import com.training.dto.LoginRequest;
import com.training.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request,
                                        HttpServletResponse response)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String accessToken=jwtUtil.generateToken(request.getEmail());
        String refreshToken= jwtUtil.generateRefreshToken(request.getEmail());

//        Store Refresh Token in Cookie
        Cookie cookie=new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(HttpServletRequest request)
    {
        Cookie[] cookies=request.getCookies();
        if(cookies==null)
        {
            throw new RuntimeException("No Cookies found");
        }
        String refreshToken=null;
        for(Cookie cookie:cookies)
        {
            if(cookie.getName().equals("refreshToken"))
            {
                refreshToken=cookie.getValue();
            }
        }
        if(refreshToken==null)
        {
            throw new RuntimeException("Refresh Token not found");
        }
        String email=jwtUtil.extractUsername(refreshToken);

        if(jwtUtil.validateToken(refreshToken,email))
        {
            String newAccessToken=jwtUtil.generateToken(email);
            return ResponseEntity.ok(newAccessToken);
        }
        throw new RuntimeException("Invalid Refresh Token");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response)
    {
        Cookie cookie=new Cookie("refreshToken",null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Logged Out Successfully");
    }
}