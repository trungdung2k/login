package com.example.finalexam.controllers;

import com.example.finalexam.constant.BaseConst;
import com.example.finalexam.factory.PageResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageController {

    public Pageable getPageable(Integer page, Integer size, String sort, String direction) {
        Integer searchPage = page == null ? 0 : page;
        Integer searchSize = size == null ? 10 : size;

        if (StringUtils.isEmpty(sort)) {
            return PageRequest.of(searchPage, searchSize);
        }

        Sort.Direction searchDirection = Sort.Direction.DESC;
        if (StringUtils.isNotEmpty(direction)) {
            searchDirection = BaseConst.SORT_ASC.equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        }
        return PageRequest.of(searchPage, searchSize, Sort.by(searchDirection, sort));
    }

    public PageResponse buildPageResponse(Page page) {
        if (page == null) {
            return null;
        }
        int currentPage = page.getNumber();
        if (currentPage <= page.getTotalPages()) {
            return new PageResponse(page.getContent(), page.getTotalPages(), page.hasNext(), page.hasPrevious(), currentPage, page.getSize(), page.getTotalElements());
        } else {
            return new PageResponse(page.getContent(), page.getTotalPages(), null, null, null, null, page.getTotalElements());
        }
    }
}
