package com.practice.web.controllers;

import com.jamonapi.MonitorFactory;
import com.practice.web.services.CacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/public")
public class SummaryController {

    @Value("${app.clear.cache.on.reset:false}")
    private boolean clearCacheOnReset;

    private CacheService cacheService;

    public SummaryController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @RequestMapping("/summary")
    public ModelAndView jamon(Model model) {
        String report = MonitorFactory.getReport();
        model.addAttribute("report", report);
        model.addAttribute("caches", this.cacheService.getAllCacheKeys());
        return new ModelAndView("summary", model.asMap());
    }

    @RequestMapping("summary/reset")
    public String reset() {
        MonitorFactory.reset();
        if (this.clearCacheOnReset) {
            this.cacheService.clearAllCache();
        }
        return "redirect:/public/summary";
    }


}
