package com.trendsAnalyser.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsAnalyser.app.entity.TwitterTrend;
import com.trendsAnalyser.app.entity.TwitterTrendDto;
import com.trendsAnalyser.app.service.TwitterScraperService;
import com.trendsAnalyser.app.service.TwitterTrendService;


import java.util.List;

@Controller
public class TwitterTrendController {

    @Autowired
    private TwitterScraperService scraperService;

    @Autowired
    private TwitterTrendService trendService;

//    @GetMapping("/scrape")
//    public List<TwitterTrend> scrapeTrends() {
//        TwitterTrendDto trends = scraperService.scrapeTwitterTrends();
//        if (trends != null) {
//            trendService.saveTrends(trends);
//        }
//        return trendService.getAllTrends();
//    }
    
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/scrape")
    public String scrapeTrends(Model model) {
        TwitterTrendDto trends = scraperService.scrapeTwitterTrends();
        if (trends != null) {
            trendService.saveTrends(trends);
            List<TwitterTrend> trd= trendService.getAllTrends();
            model.addAttribute("trends", trd);
            return "result";
        }
        return "Something Went Wrong";
    }
}

