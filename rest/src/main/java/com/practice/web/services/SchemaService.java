package com.practice.web.services;

import com.practice.annotations.LogSummaryPreview;
import com.practice.dto.Schema;
import com.practice.web.entities.Menu;
import com.practice.web.entities.MenuDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SchemaService {

    @Value("${app.lang:USA}")
    private String lang;

    @Value("${app.user:USA}")
    private String userId;


    private MenuService menuService;
    private MenuDetailService menuDetailService;
    private MenuStatsMappingService areaService;
    private static final Logger logger = LoggerFactory.getLogger(SchemaService.class);

    public SchemaService(MenuService menuService, MenuDetailService menuDetailService, MenuStatsMappingService areaService) {
        this.menuService = menuService;
        this.menuDetailService = menuDetailService;
        this.areaService = areaService;
    }

    @LogSummaryPreview
    public Schema getStatsForSchema(Schema request) {
        String org = request.getOrg();
        String req = request.getKey();
        Menu menuOption = this.menuService.getMenuOption(org, req);
        MenuDetail menuOptionDetail = this.menuDetailService.getMenuDetailEntity(org, req, this.lang);
        request.setTitle(menuOptionDetail.getTitle());
        request.setDesc(menuOptionDetail.getText());
        request.setAreas(this.areaService.getAreas(menuOption.getOrg(), menuOption.getRequest(), userId));
        return request;
    }

}