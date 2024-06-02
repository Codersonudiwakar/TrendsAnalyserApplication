package com.trendsAnalyser.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trendsAnalyser.app.entity.TwitterTrend;

@Repository
public interface TwitterTrendRepository extends MongoRepository<TwitterTrend, Integer> {
}
