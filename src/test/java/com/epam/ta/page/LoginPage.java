package com.epam.ta.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.model.User;

public class LoginPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement buttonSubmit;

    @FindBy(xpath="//h3[@data-test='error']")
    private WebElement errorMessage;



    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        logger.info("Login page opened");
        return this;
    }

    public MainPage login(User user)
    {
        inputLogin.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        logger.info("Login performed");
        return new MainPage(driver);
    }
}
