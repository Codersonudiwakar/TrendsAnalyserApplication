package com.trendsAnalyser.app.service;

import com.trendsAnalyser.app.entity.TwitterTrend;
import com.trendsAnalyser.app.entity.TwitterTrendDto;

public class TwitterTrendConverter {

    public  TwitterTrend toEntity(TwitterTrendDto dto) {
        TwitterTrend entity = new TwitterTrend();
        entity.setId(dto.getId());
        entity.setTrend1(dto.getTrend1());
        entity.setTrend2(dto.getTrend2());
        entity.setTrend3(dto.getTrend3());
        entity.setTrend4(dto.getTrend4());
        entity.setTrend5(dto.getTrend5());
        entity.setTimestamp(dto.getTimestamp());
        entity.setIpAddress(dto.getIpAddress());
        
        return entity;
    }

    public TwitterTrendDto toDTO(TwitterTrend entity) {
        TwitterTrendDto dto=new TwitterTrendDto();
        dto.setId(entity.getId());
        dto.setTrend1(entity.getTrend1());
        dto.setTrend2(entity.getTrend2());
        dto.setTrend3(entity.getTrend3());
        dto.setTrend4(entity.getTrend4());
        dto.setTrend5(entity.getTrend5());
        dto.setTimestamp(entity.getTimestamp());
        dto.setIpAddress(entity.getIpAddress());
        return dto;
        
    }
}

