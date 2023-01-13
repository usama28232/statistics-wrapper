package com.practice.web.services;

import com.practice.constants.AppConstants;
import com.practice.web.entities.MenuDetail;
import com.practice.web.entities.keys.MenuDetailPK;
import com.practice.web.repositories.MenuDetailRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MenuDetailService extends ServiceBase<MenuDetail, MenuDetailPK> {

    private MenuDetailRepository menuDetailRepository;

    public MenuDetailService(MenuDetailRepository menuDetailRepository) {
        super(menuDetailRepository);
        this.menuDetailRepository = menuDetailRepository;
    }

    @Cacheable(value = "MenuDetailCache", key = "#org.concat('_').concat(#request).concat('_').concat(#lang).concat('_MD')")
    public MenuDetail getMenuDetailEntity(String org, String request, String lang) {
        return this.menuDetailRepository.findByOrgAndRequestAndLangAndActive(org, request, lang, AppConstants.FLAG_Y)
                .orElse(new MenuDetail());
    }

}
