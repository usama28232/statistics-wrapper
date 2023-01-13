package com.practice.web.controllers;

import com.practice.web.services.ServiceBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

public abstract class ControllerBase<T, ID> {

    private ServiceBase<T, ID> service;

    public ControllerBase(ServiceBase<T, ID> service) {
        this.service = service;
    }

    @RequestMapping("get")
    public Optional<T> findOne(ID entity) {
        return this.service.findOneById(entity);
    }

    @RequestMapping("")
    public Page<T> index(Pageable pageable) {
        return this.service.findAll(pageable);
    }

}
