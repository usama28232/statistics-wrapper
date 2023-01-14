package com.practice.web.controllers;

import com.practice.dto.Schema;
import com.practice.web.services.MenuServiceProxy;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private MessageSource messageSource;
    private MenuServiceProxy menuServiceProxy;

    public ApiController(MessageSource messageSource, MenuServiceProxy menuServiceProxy) {
        this.messageSource = messageSource;
        this.menuServiceProxy = menuServiceProxy;
    }

    @PostMapping("/schema")
    public Schema getSchemaFromRemote(@RequestBody Schema request) {
        return this.menuServiceProxy.getSchema(request);
    }

}
