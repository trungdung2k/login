package com.example.finalexam.request;

import com.example.finalexam.entity.Account;
import com.example.finalexam.enums.AccountRoles;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AccountRequest {

    private Long id;

    @NotBlank
    @Length(max = 255)
    private String userName;

    @NotBlank
    @Length(max = 255)
    private String firstName;

    @NotBlank
    @Length(max = 255)
    private String lastName;

    @NotBlank
    @Length(max = 255)
    private String password;


    private String role;

    private Long departmentId;

    private Date createdDate;

    private Boolean deleted;

    public Account asCreateAccount() {
        Account account = new Account();
        account.setId(this.id);
        account.setUserName(this.userName);
        account.setRole(this.role);
        account.setLastName(this.lastName);
        account.setPassword(this.password);
        account.setFirstName(this.firstName);
        account.setCreatedDate(this.createdDate);

        return account;
    }

    public Account asUpdateAccount(Account account) {
        account.setUserName(this.userName);
        account.setRole(this.role);
        account.setFirstName(this.firstName);
        account.setLastName(this.lastName);
        account.setCreatedDate(this.createdDate);
        return account;
    }

}
