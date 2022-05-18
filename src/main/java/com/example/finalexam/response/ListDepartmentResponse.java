package com.example.finalexam.response;

import com.example.finalexam.entity.Department;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Data
public class ListDepartmentResponse {

    private Long id;

    @Column(length = 150)
    private String name;

    @Column(length = 50)
    private String type;

    private Date createDate;

    private Long totalMember;

    public ListDepartmentResponse (Department department, Long totalMember){
        this.id = department.getId();
        this.name = department.getName();
        this.type = department.getType();
        this.createDate = department.getCreatedDate();
        this.totalMember = totalMember;
    }
}
