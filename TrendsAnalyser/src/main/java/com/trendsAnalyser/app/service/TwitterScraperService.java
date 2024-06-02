package com.trendsAnalyser.app.service;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trendsAnalyser.app.entity.TwitterTrendDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\git\\repository\\TrendsAnalyser\\src\\main\\java\\webdriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyUrl);
        proxy.setHttpProxy(proxyUrl);
        proxy.setSslProxy(proxyUrl);
        
        options.setCapability(CapabilityType.PROXY, proxy);
        System.out.println(options);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        
       String explore="(//div[contains(@class,'css-146c3p1 r-dnmrzs')]//span)[2]";
       String trending="//div[@id='react-root']/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/nav[1]/div[1]/div[2]/div[1]/div[2]/a[1]/div[1]/div[1]";
       String trend1="//div[contains(@class,'css-175oi2r r-6koalj')]//div";
       String trend2="(//div[contains(@class,'css-175oi2r r-6koalj')])[3]";
       String trend3="//div[@id='id__or0mgug7a9d']";
       String trend4="//div[@id='react-root']/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/section[1]/div[1]/div[1]/div[6]/div[1]/div[1]";
       String trend5="//div[@id='react-root']/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/section[1]/div[1]/div[1]/div[7]/div[1]/div[1]";
        
        
        try {
            driver.get("https://x.com/i/flow/login");
            Thread.sleep(10000);
            WebElement usernameField = driver.findElement(By.xpath("//div[contains(@class,'css-146c3p1 r-bcqeeo')]//input[1]"));
            WebElement nextBtn = driver.findElement(By.xpath("(//button[contains(@class,'css-175oi2r r-sdzlij')]//div)[3]"));
            WebElement passwordField = driver.findElement(By.xpath("(//div[contains(@class,'css-146c3p1 r-bcqeeo')]//input)[2]"));
            WebElement loginButton = driver.findElement(By.xpath("(//button[contains(@class,'css-175oi2r r-sdzlij')]//div)[3]"));
            ExpectedConditions.visibilityOf(usernameField);
            usernameField.sendKeys("sonukumardiwakar.mail@gmail.com");
            
            Thread.sleep(5000);
            nextBtn.click();
            passwordField.sendKeys("Sonu@555");
            Thread.sleep(5000);
            loginButton.click();
            
            Thread.sleep(5000); // wait for the login to complete
            
            WebElement trd1 = driver.findElement(By.xpath(trend1));
            WebElement trd2 = driver.findElement(By.xpath(trend2));
            WebElement trd3 = driver.findElement(By.xpath(trend3));
            WebElement trd4 = driver.findElement(By.xpath(trend4));
            WebElement trd5 = driver.findElement(By.xpath(trend5));
            List<String> top5Trends =new ArrayList<String>();
            top5Trends.add(trd1.getText());
            top5Trends.add(trd2.getText());
            top5Trends.add(trd3.getText());
            top5Trends.add(trd4.getText());
            top5Trends.add(trd5.getText());

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


