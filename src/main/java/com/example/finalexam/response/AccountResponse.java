package com.example.finalexam.response;

import com.example.finalexam.entity.Account;
import com.example.finalexam.enums.AccountRoles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountResponse {
    private Long id;

    @ApiModelProperty("username")
    private String userName;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("role")
    private String role;

    public AccountResponse (Account account){
        this.id = account.getId();
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.role = account.getRole();
    }
}
