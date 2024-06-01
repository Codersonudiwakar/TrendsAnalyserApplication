package com.trendsAnalyser.app.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsAnalyser.app.entity.TwitterTrend;
import com.trendsAnalyser.app.entity.TwitterTrendDto;
import com.trendsAnalyser.app.service.TwitterScraperService;
import com.trendsAnalyser.app.service.TwitterTrendService;


import java.util.List;

@RestController
public class TwitterTrendController {

    @Autowired
    private TwitterScraperService scraperService;

    @Autowired
    private TwitterTrendService trendService;

    @GetMapping("/scrape")
    public List<TwitterTrend> scrapeTrends() {
        TwitterTrendDto trends = scraperService.scrapeTwitterTrends();
        if (trends != null) {
            trendService.saveTrends(trends);
        }
        return trendService.getAllTrends();
    }
}

