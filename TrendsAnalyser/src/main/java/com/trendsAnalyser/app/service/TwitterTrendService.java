package com.trendsAnalyser.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trendsAnalyser.app.entity.TwitterTrend;
import com.trendsAnalyser.app.entity.TwitterTrendDto;
import com.trendsAnalyser.app.repo.TwitterTrendRepository;

import java.util.List;

@Service
public class TwitterTrendService {

    @Autowired
    private TwitterTrendRepository repository;

    public TwitterTrend saveTrends(TwitterTrendDto trends) {
    	TwitterTrendConverter cnvt=new TwitterTrendConverter();
        TwitterTrend trend=cnvt.toEntity(trends);
        return repository.save(trend);
    }

    public List<TwitterTrend> getAllTrends() {
        return repository.findAll();
    }
}


