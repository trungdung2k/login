package com.example.finalexam.repository;

import org.springframework.data.domain.Page;
import com.example.finalexam.request.ListDepartmentRequest;
import com.example.finalexam.response.ListDepartmentResponse;

import java.util.List;

public interface CustomDepartmentRepository {
    Page<ListDepartmentResponse> listDepartment (ListDepartmentRequest request);
}
