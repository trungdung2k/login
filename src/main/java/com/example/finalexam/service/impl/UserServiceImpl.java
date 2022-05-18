package com.example.finalexam.service.impl;

import com.example.finalexam.entity.Account;
import com.example.finalexam.entity.CustomAccount;
import com.example.finalexam.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        // Kiểm tra xem user có tồn tại trong database không?
        Account account = accountRepository.findByUserName(userName);

        if (account == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new CustomAccount(account);
    }

}
