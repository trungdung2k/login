package com.example.finalexam.request;

import com.example.finalexam.entity.Department;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DepartmentRequest {

    private Long id;

    @Column(length = 150)
    private String name;

    @Column(length = 50)
    private String type;

    private Date createdDate;

    private Long totalMember;


    public Department asDepartmentUpdate (Department department) {
        department.setName(this.name);
        department.setType(this.type);
        department.setCreatedDate(this.createdDate);;

        return department;
    }
}



