package com.example.finalexam.service.impl;

import com.example.finalexam.constant.MessageConst;
import com.example.finalexam.entity.Department;
import com.example.finalexam.exception.BasicException;
import com.example.finalexam.request.DepartmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.finalexam.repository.DepartmentRepository;
import com.example.finalexam.request.ListDepartmentRequest;
import com.example.finalexam.response.ListDepartmentResponse;
import com.example.finalexam.service.DepartmentService;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createdDepartment(DepartmentRequest request){
        Department department = new Department();
        department.setId(request.getId());
        department.setName(request.getName());
        department.setType(request.getType());
        department.setCreatedDate(request.getCreatedDate());

        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(DepartmentRequest request){
        // Validate department
        Optional<Department> departmentOptional = departmentRepository.findById(request.getId());
        if (!departmentOptional.isPresent()){
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.NOT_FOUND)
                    .addErrors(MessageConst.ACCOUNT_E0006);
        }
        Department department = departmentRepository.getById(request.getId());

        department = request.asDepartmentUpdate(department);
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id){
        Department department = departmentRepository.getById(id);
        if (Objects.isNull(department)){
            throw BasicException.INVALID_ARGUMENT.withMessage(MessageConst.DEPARTMENT_NOT_FOUND)
                    .addErrors(MessageConst.DEPARTMENT_E0001);
        }
        departmentRepository.delete(department);
    }

    @Override
    public Page<ListDepartmentResponse> listDepartment(ListDepartmentRequest request){
        Page<ListDepartmentResponse> responses = departmentRepository.listDepartment(request);
        return responses;
    }
}
