package com.example.finalexam.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Data
public class ListDepartmentRequest {

    private Long id;

    @Column(length = 150)
    private String name;

    @Column(length = 50)
    private String type;

    private Date createDate;

    private Long totalMember;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
