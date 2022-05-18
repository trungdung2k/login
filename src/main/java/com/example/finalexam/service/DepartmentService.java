package com.example.finalexam.service;

import com.example.finalexam.entity.Department;
import com.example.finalexam.request.DepartmentRequest;
import org.springframework.data.domain.Page;
import com.example.finalexam.request.ListDepartmentRequest;
import com.example.finalexam.response.ListDepartmentResponse;

public interface DepartmentService {
    void createdDepartment (DepartmentRequest request);


    void updateDepartment(DepartmentRequest request);

    void deleteDepartment(Long id);

    Page<ListDepartmentResponse> listDepartment(ListDepartmentRequest request);
}
