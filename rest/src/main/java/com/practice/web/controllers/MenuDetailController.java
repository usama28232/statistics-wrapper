package com.practice.web.controllers;

import com.practice.web.entities.MenuDetail;
import com.practice.web.entities.keys.MenuDetailPK;
import com.practice.web.services.MenuDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu_detail")
public class MenuDetailController extends ControllerBase<MenuDetail, MenuDetailPK> {

    public MenuDetailController(MenuDetailService service) {
        super(service);
    }

}
