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
        
        
        
      String nextBt="/html/body/div/div/div/div[1]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/button[2]"; 
      String pasInput="/html/body/div/div/div/div[1]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[3]/div/label/div/div[2]/div[1]/input";
      
      String loginBtn="/html/body/div/div/div/div[1]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/button";
      
       String explore="(//div[contains(@class,'css-146c3p1 r-dnmrzs')]//span)[2]";
       String trending="//div[@id='react-root']/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/nav[1]/div[1]/div[2]/div[1]/div[2]/a[1]/div[1]/div[1]";
       String trend1="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[2]/div/div[2]/div/div/div/div[4]/div/section/div/div/div[3]/div/div/div/div[2]";
       String trend2="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[2]/div/div[2]/div/div/div/div[4]/div/section/div/div/div[4]/div/div/div/div[2]";
       String trend3="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[2]/div/div[2]/div/div/div/div[4]/div/section/div/div/div[5]/div/div/div/div[2]";
       String trend4="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[2]/div/div[2]/div/div/div/div[4]/div/section/div/div/div[6]/div/div/div/div[2]";
       String trend5="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[2]/div/div[2]/div/div/div/div[4]/div/section/div/div/div[7]/div/div/div/div[2]";
        
        
        try {
            driver.get("https://x.com/i/flow/login");
            Thread.sleep(10000);
            System.out.println("usernameField");
            WebElement usernameField = driver.findElement(By.xpath("//*[@id='layers']/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div/label/div/div/div/input[1]"));
            usernameField.sendKeys("sonuDiwakar555");
            Thread.sleep(5000);
            
            WebElement nextBtn = driver.findElement(By.xpath(nextBt));
            nextBtn.click();
            Thread.sleep(5000);
            
            
            WebElement passwordField = driver.findElement(By.xpath(pasInput));
            passwordField.sendKeys("Career@5525");
            Thread.sleep(5000);
            
            WebElement loginButton = driver.findElement(By.xpath(loginBtn));
            loginButton.click();
            
            Thread.sleep(10000);            
            WebElement whatHPN=driver.findElement(By.xpath("//div[contains(@class,'css-175oi2r r-1wtj0ep')]//h2"));
            System.out.println(whatHPN);
            
            ExpectedConditions.visibilityOf(whatHPN);
            System.out.println(whatHPN.getText());
            
            
            
            WebElement trd1 = driver.findElement(By.xpath(trend1));
            WebElement trd2 = driver.findElement(By.xpath(trend2));
            WebElement trd3 = driver.findElement(By.xpath(trend3));
            WebElement trd4 = driver.findElement(By.xpath(trend4));
            WebElement trd5 = driver.findElement(By.xpath(trend5));
            List<String> top5Trends =new ArrayList<String>();
            top5Trends.add(trd1.getText());
            System.out.println(trd1.getText());
            top5Trends.add(trd2.getText());
            System.out.println(trd2.getText());
            top5Trends.add(trd3.getText());
            System.out.println(trd3.getText());
            top5Trends.add(trd4.getText());
            System.out.println(trd4.getText());
            top5Trends.add(trd5.getText());
            System.out.println(trd5.getText());

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


