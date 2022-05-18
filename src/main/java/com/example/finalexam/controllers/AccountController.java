package com.example.finalexam.controllers;

import com.example.finalexam.entity.Account;
import com.example.finalexam.factory.PageResponse;
import com.example.finalexam.response.ListAccountResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.finalexam.request.AccountRequest;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("isAuthenticated()")
public class AccountController extends PageController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/account/list")
    public ResponseEntity<PageResponse> getListAccount(ListAccountRequest request) {
        return new ResponseEntity<>(buildPageResponse(accountService.listAccount(request)), HttpStatus.OK);
    }

    @ApiOperation(value = "create account user ")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })

    @PostMapping("/account")
    public ResponseEntity<Void> createAccountUser(@Valid @RequestBody AccountRequest request) {
        accountService.createAccountUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "update account user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/account")
    public ResponseEntity<Void> updateAccount(@Valid @RequestBody AccountRequest request) {
        accountService.updateAccountUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "delete account ")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server Error")
    })
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<ListAccountResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/account_firstName")
    public ResponseEntity<List<ListAccountResponse>> findByFirstName(@RequestParam String firstName) {
        return new ResponseEntity<>(accountService.findByFirstName(firstName), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public void changePassword(@Valid @RequestBody AccountRequest request, String password) {
        accountService.changePassword(request, password);
    }

//    @GetMapping("/account1")
//    public List<ListAccountResponse> listAccountResponses ()
}
