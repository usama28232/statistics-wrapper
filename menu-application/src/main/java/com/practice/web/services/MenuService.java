package com.practice.web.services;

import com.practice.constants.AppConstants;
import com.practice.web.entities.Menu;
import com.practice.web.entities.keys.MenuPK;
import com.practice.web.exceptions.DomainException;
import com.practice.web.repositories.MenuDetailRepository;
import com.practice.web.repositories.MenuRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MenuService extends ServiceBase<Menu, MenuPK> {

    private static final String MENU_OPTION_UNDEFINED = "app.menu-option.undefined";

    private MenuRepository menuRepository;
    private MenuDetailRepository menuDetailRepository;

    public MenuService(MenuRepository menuRepository) {
        super(menuRepository);
        this.menuRepository = menuRepository;
    }

    @Cacheable(value = "MenuCache", key = "#org.concat('_').concat(#request).concat('_M')")
    public Menu getMenuOption(String org, String request) {
        return this.menuRepository.findByOrgAndRequestAndActive(org, request, AppConstants.FLAG_Y)
                .orElseThrow(() -> new DomainException(MENU_OPTION_UNDEFINED, org, request));
    }

}
