package com.example.finalexam.service.impl;

import com.example.finalexam.constant.MessageConst;
import com.example.finalexam.entity.Account;
import com.example.finalexam.entity.Department;
import com.example.finalexam.exception.BasicException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.finalexam.repository.AccountRepository;
import com.example.finalexam.repository.DepartmentRepository;
import com.example.finalexam.request.AccountRequest;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.response.ListAccountResponse;
import com.example.finalexam.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;


    @Autowired
    public AccountServiceImpl(ObjectMapper objectMapper, AccountRepository accountRepository, DepartmentRepository departmentRepository) {
        this.objectMapper = objectMapper;
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;

    }

    @Override
    public void createAccountUser(AccountRequest request) {

        // 1. Validate department
        Optional<Department> departmentOptional = departmentRepository.findById(request.getDepartmentId());
        if (!departmentOptional.isPresent()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.DEPARTMENT_NOT_FOUND)
                    .addErrors(MessageConst.DEPARTMENT_E0001);
        }

        Account account = request.asCreateAccount();

        if (accountRepository.existsAccountByUserName(account.getUserName())) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.USERNAME_ACCOUNT_EXITS)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        account.setDepartment(departmentOptional.get());
        accountRepository.save(account);
    }

    @Override
    public void updateAccountUser(AccountRequest request) {
        // 1. Validate department
        Optional<Department> departmentOptional = departmentRepository.findById(request.getDepartmentId());
        if (!departmentOptional.isPresent()) {
            // throw Exception
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NOT_FOUND)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }

        Account account = getAccount(request.getId());
        if (accountRepository.existsByUserNameAndIdNot(request.getUserName(), account.getId())) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.USERNAME_ACCOUNT_EXITS)
                    .addErrors(MessageConst.ACCOUNT_E0003);
        }
        // 3. Cập nhật
        account = request.asUpdateAccount(account);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = getAccount(id);
        account.setDeleted(true);
        accountRepository.save(account);
    }


    @Override
    public Page<ListAccountResponse> listAccount(ListAccountRequest request) {
        Page<ListAccountResponse> responses = accountRepository.listAccount(request);
        return responses;
    }

    @Override
    public List<ListAccountResponse> listAccountResponses(List<Long> ids) {
        List<Account> accounts = accountRepository.findAll();
        List<Long> longList = accounts.stream().map(Account::getId).collect(Collectors.toList());
        List<ListAccountResponse> listAccountResponses = accountRepository.listAccountResponses(longList);
        return listAccountResponses;
    }

    @Override
    public ListAccountResponse findById(Long id) {
        return objectMapper.convertValue(accountRepository.findById(id), ListAccountResponse.class);
    }

    @Override
    public List<ListAccountResponse> findByFirstName(String firstName) {
        List<ListAccountResponse> listAccountResponses = new ArrayList<>();
        List<Account> accounts = accountRepository.listAccountByFirstName(firstName);
        accounts.forEach(account -> {
            ListAccountResponse accountResponse = new ListAccountResponse();
            accountResponse.setId(account.getId());
            accountResponse.setFirstName(account.getFirstName());
            accountResponse.setLastName(account.getLastName());
            accountResponse.setUserName(account.getUserName());
            accountResponse.setPassword(account.getPassword());
            accountResponse.setRole(account.getRole());
            accountResponse.setCreatedDate(account.getCreatedDate());

            listAccountResponses.add(accountResponse);
        });
        return listAccountResponses;
    }

    @Override
    public void changePassword(AccountRequest request, String password) {
        Account account = getAccount(request.getId());
        account.setPassword(password);
        if (password.isEmpty()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NEW_PASSWORD_EMPTY)
                    .addErrors(MessageConst.PASSWORD_E0003);
        }
        accountRepository.save(account);
    }

    private Account getAccount(Long id) {
        if (Objects.isNull(id)) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.ID_ACCOUNT_EMPTY)
                    .addErrors(MessageConst.ACCOUNT_E0007);
        }

        Optional<Account> accountOptional = accountRepository.findByIdAndDeletedFalse(id);
        if (!accountOptional.isPresent()) {
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NOT_FOUND)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }
        return accountOptional.get();
    }

}
