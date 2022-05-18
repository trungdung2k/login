package com.example.finalexam.request;

import com.example.finalexam.entity.Account;
import com.example.finalexam.enums.AccountRoles;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @Length(max = 255)
    private String userName;

    @Length(max = 255)
    private String password;

    public Account LoginRequest(Account account) {
        this.userName = account.getUserName();
        this.password = account.getPassword();

        return account;
    }
}
