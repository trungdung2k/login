package com.example.finalexam.response;

import com.example.finalexam.entity.Account;
import com.example.finalexam.enums.AccountRoles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class ListAccountResponse {


    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("fistName")
    private String firstName;

    @ApiModelProperty("createdDate")
    private Date createdDate;

    @ApiModelProperty("lastName")
    private String lastName;

    @ApiModelProperty("role")
    private String role;

    public ListAccountResponse(Account account) {
        this.id = account.getId();
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.firstName = account.getFirstName();
        this.createdDate = account.getCreatedDate();
        this.lastName = account.getLastName();
        this.role = account.getRole();
    }

    public ListAccountResponse() {

    }
}
