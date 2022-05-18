package com.example.finalexam.controllers;

import com.example.finalexam.constant.MessageConst;
import com.example.finalexam.entity.include.BaseEntity;
import com.example.finalexam.exception.BasicException;
import com.example.finalexam.request.LoginRequest;
import com.example.finalexam.response.LoginResponse;
import com.example.finalexam.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;


    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        if (loginRequest.getUserName().isEmpty()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.USERNAME_EMPTY)
                    .addErrors(MessageConst.ACCOUNT_E0009);
        }
        if (loginRequest.getPassword().isEmpty()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.PASSWORD_EMPTY)
                    .addErrors(MessageConst.ACCOUNT_E00010);
        }
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken(loginRequest.getUserName());
        return new LoginResponse(jwt);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/random")
    public String randomStuff() {
        return ("JWT Hợp lệ + Role_Admin mới có thể thấy được message này");
    }
}
