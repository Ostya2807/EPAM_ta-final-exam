package com.epam.ta.driver;

import com.epam.ta.utils.exception.UnsupportedBrowserException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton(){}

    public  static WebDriver getDriver(){
            if (driver == null){
             switch(System.getProperty("browser")){
                 case "firefox":{
                     WebDriverManager.firefoxdriver().setup();
                     driver = new FirefoxDriver();
                     break;
                 }case "chrome":{
                     WebDriverManager.chromedriver().setup();
                     driver = new ChromeDriver();
                     break;
                 }
                 default:{
                     throw new UnsupportedBrowserException("Unsupported browser: " + System.getProperty("browser"));
                 }
             }
             driver.manage().window().maximize();
            }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }

}
