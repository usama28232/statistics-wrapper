package com.practice.web.controllers;

import com.practice.dto.Schema;
import com.practice.web.entities.Menu;
import com.practice.web.entities.keys.MenuPK;
import com.practice.web.services.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController extends ControllerBase<Menu, MenuPK> {


    public MenuController(MenuService service) {
        super(service);
    }

}
