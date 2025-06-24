package com.epam.ta.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.utils.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected  static final String USER_NAME = "standard_user";
    protected  static final String USER_PASSWORD = "secret_sauce";

    @BeforeMethod()
    public void setUp(){
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(){
        DriverSingleton.closeDriver();
    }
}
