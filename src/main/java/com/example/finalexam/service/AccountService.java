package com.example.finalexam.service;

import com.example.finalexam.entity.Account;
import org.springframework.data.domain.Page;
import com.example.finalexam.request.AccountRequest;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.response.ListAccountResponse;

import java.util.List;

public interface AccountService {
    Page<ListAccountResponse> listAccount(ListAccountRequest request);

    void createAccountUser(AccountRequest request);

    void updateAccountUser(AccountRequest request);

    void deleteAccount(Long id);

    List<ListAccountResponse> listAccountResponses(List<Long> ids);

    ListAccountResponse findById(Long id);

    List<ListAccountResponse> findByFirstName(String firstName);

    void changePassword(AccountRequest request, String password);
}
