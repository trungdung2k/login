package com.example.finalexam.entity;

import com.example.finalexam.enums.AccountRoles;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 150)
    @NotNull(message = "tai khoan khong dc rong")
    private String userName;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 100)
    private String role;

    @Column(length = 100)
    @NotNull(message = "mat khau khong dc rong")
    private String password;

    @CreatedDate
    private Date createdDate;

    private boolean deleted;

    @ManyToOne
    private Department department;

}
