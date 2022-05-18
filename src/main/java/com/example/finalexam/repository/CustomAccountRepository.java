package com.example.finalexam.repository;

import com.example.finalexam.entity.Account;
import org.springframework.data.domain.Page;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.response.ListAccountResponse;

import java.util.List;


public interface CustomAccountRepository {

    Page<ListAccountResponse> listAccount(ListAccountRequest request);

    List<ListAccountResponse> listAccountResponses(List<Long> listId);

    List<Account> listAccountByFirstName(String firstName);
}
