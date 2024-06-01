package com.trendsAnalyser.app.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trendsAnalyser.app.entity.TwitterTrendDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterScraperService {

    @Value("${twitter.username}")
    private String username;

    @Value("${twitter.password}")
    private String password;

    @Value("${proxy.url}")
    private String proxyUrl;

    public TwitterTrendDto scrapeTwitterTrends() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server=" + proxyUrl);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://twitter.com/login");

            WebElement usernameField = driver.findElement(By.name("session[username_or_email]"));
            WebElement passwordField = driver.findElement(By.name("session[password]"));
            WebElement loginButton = driver.findElement(By.xpath("//span[text()='Log in']"));

            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            loginButton.click();

            Thread.sleep(5000); // wait for the login to complete

            List<WebElement> trendingTopics = driver.findElements(By.xpath("//section[@aria-labelledby='accessible-list-0']//span"));
            List<String> top5Trends = trendingTopics.stream().limit(5).map(WebElement::getText).collect(Collectors.toList());

            String currentIp = getCurrentIpAddress();
            
            return new TwitterTrendDto(null, 
                    top5Trends.size() > 0 ? top5Trends.get(0) : null,
                    top5Trends.size() > 1 ? top5Trends.get(1) : null,
                    top5Trends.size() > 2 ? top5Trends.get(2) : null,
                    top5Trends.size() > 3 ? top5Trends.get(3) : null,
                    top5Trends.size() > 4 ? top5Trends.get(4) : null,
                    LocalDateTime.now(), currentIp);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            driver.quit();
        }
    }

    private String getCurrentIpAddress() throws Exception {
        URL url = new URL("http://checkip.amazonaws.com/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String ip = in.readLine();
        in.close();

        return ip;
    }
}


