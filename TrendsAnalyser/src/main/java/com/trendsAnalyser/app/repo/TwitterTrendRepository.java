package com.trendsAnalyser.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendsAnalyser.app.entity.TwitterTrend;

@Repository
public interface TwitterTrendRepository extends JpaRepository<TwitterTrend, Long> {
}
