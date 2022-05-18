package com.example.finalexam.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.finalexam.entity.Account;
import com.example.finalexam.entity.QAccount;
import com.example.finalexam.entity.QDepartment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.finalexam.repository.CustomAccountRepository;
import com.example.finalexam.request.ListAccountRequest;
import com.example.finalexam.response.ListAccountResponse;
import com.example.finalexam.response.ListDepartmentResponse;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    private final EntityManager entityManager;

    @Autowired
    public CustomAccountRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<ListAccountResponse> listAccount(ListAccountRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), null);

        JPAQuery<Account> query = new JPAQuery<>(this.entityManager);
        QAccount qAccount = QAccount.account;

        BooleanBuilder where = new BooleanBuilder();
        where.and(qAccount.deleted.isFalse());

        if (Objects.nonNull(request.getDepartmentId())) {
            where.and(qAccount.department.id.eq(request.getDepartmentId()));
        }

        if (StringUtils.isNotBlank(request.getSort())) {
            query.orderBy(request.isAsc() ? Expressions.stringPath(request.getSort()).asc()
                    : Expressions.stringPath(request.getSort()).desc());
        }

        List<ListAccountResponse> responses = query.select(Projections.bean(ListAccountResponse.class, qAccount.id.as("id"),
                        qAccount.userName.as("userName"), qAccount.firstName.as("firstName"), qAccount.lastName.as("lastName"), qAccount.password.as("password"),
                        qAccount.createdDate.as("createdDate"), qAccount.role.as("role")))
                .from(qAccount)
                .where(where)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(responses, pageable, query.fetchCount());
    }

    @Override
    public List<ListAccountResponse> listAccountResponses(List<Long> listId) {
        JPAQuery<Account> query = new JPAQuery<>(this.entityManager);
        QAccount qAccount = QAccount.account;
        QDepartment qDepartment = QDepartment.department;

        BooleanBuilder where = new BooleanBuilder();
        where.and(qAccount.id.in(listId));
        where.and(qAccount.department.id.eq(qDepartment.id));

        List<ListAccountResponse> responses = query.select(Projections.bean(ListAccountResponse.class, qAccount.id.as("id"),
                        qAccount.userName.as("userName"), qAccount.firstName.as("firstName"), qAccount.lastName.as("lastName"), qAccount.password.as("password"),
                        qAccount.createdDate.as("createdDate"),
                        Projections.bean(ListDepartmentResponse.class, qDepartment.name.as("department"))))
                .from(qAccount)
                .innerJoin(qAccount.department, qDepartment)
                .where(where)
                .groupBy(qAccount.id, qDepartment.id)
                .fetch();
        return responses;
    }

    @Override
    public List<Account> listAccountByFirstName(String firstName) {
        JPAQuery<Account> query = new JPAQuery<>(this.entityManager);
        QAccount qAccount = QAccount.account;

        BooleanBuilder where = new BooleanBuilder();
        where.and(qAccount.deleted.isFalse());
        where.and(qAccount.firstName.containsIgnoreCase(firstName));

        List<Account> responses = query.select(Projections.bean(Account.class, qAccount.id.as("id"),
                        qAccount.userName.as("userName"), qAccount.firstName.as("firstName"), qAccount.lastName.as("lastName"), qAccount.password.as("password"),
                        qAccount.createdDate.as("createdDate"), qAccount.role.as("role")))
                .from(qAccount)
                .where(where)
                .fetch();
        return responses;
    }
}