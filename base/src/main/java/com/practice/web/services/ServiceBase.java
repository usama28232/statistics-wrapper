package com.practice.web.services;

import com.practice.web.repositories.RepositoryBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ServiceBase<T, ID> {

    @Value("${app.default.list.records:20}")
    private int defaultRows;

    private RepositoryBase<T, ID> repository;

    public ServiceBase(RepositoryBase<T, ID> repository) {
        this.repository = repository;
    }

    public Optional<T> findOneById(ID entity) {
        return this.repository.findById(entity);
    }

    public Page<T> findAll(Pageable pageable) {
        if (pageable == null || pageable.getPageSize() == 0) {
            pageable = PageRequest.of(0, defaultRows);
        }
        return this.repository.findAll(pageable);
    }

}
