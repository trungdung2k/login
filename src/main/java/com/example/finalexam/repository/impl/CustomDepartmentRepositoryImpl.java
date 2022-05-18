package com.example.finalexam.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.finalexam.entity.Department;
import com.example.finalexam.entity.QAccount;
import com.example.finalexam.entity.QDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.finalexam.repository.CustomDepartmentRepository;
import com.example.finalexam.request.ListDepartmentRequest;
import com.example.finalexam.response.ListDepartmentResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomDepartmentRepositoryImpl implements CustomDepartmentRepository {

    private final EntityManager entityManager;

    @Autowired
    public CustomDepartmentRepositoryImpl(EntityManager entityManagerl) {
        this.entityManager = entityManagerl;
    }

    @Override
    public Page<ListDepartmentResponse> listDepartment(ListDepartmentRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), null);

        JPAQuery<Department> query = new JPAQuery<>(this.entityManager);
        QAccount qAccount = QAccount.account;
        QDepartment qDepartment = QDepartment.department;

        BooleanBuilder where = new BooleanBuilder();
        where.and(qDepartment.id.eq(qAccount.department.id));

        List<ListDepartmentResponse> responses = query.select(Projections.bean(ListDepartmentResponse.class, qDepartment.id.as("id"),
                        qDepartment.name.as("name"), qDepartment.type.as("type"), qAccount.id.count().as("totalMember")))
                .from(qAccount)
                .innerJoin(qAccount.department, qDepartment)
                .where(where)
                .groupBy(qAccount.id, qDepartment.id)
                .fetch();
        return new PageImpl<>(responses, pageable, query.fetchCount());
    }


}
