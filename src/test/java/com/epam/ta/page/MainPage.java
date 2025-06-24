package com.epam.ta.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage{
    private final String BASE_URL = "https://www.saucedemo.com";


    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public Boolean getLoggedInUserState()
    {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
          return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']"))).getText();
    }

    public String getLoggedInTitle() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='app_logo']"))).getText();
    }
}
