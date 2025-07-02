package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.LoginPage;
import com.epam.ta.page.MainPage;
import com.epam.ta.service.UserCreator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserAccessTests extends CommonConditions{

    @Test
    public void oneCanLoginSwagLabs(){
        User testUser = UserCreator.withCredentialsFromProperty();
        boolean loggedIn = new LoginPage(driver)
                .openPage().login(testUser).getLoggedInUserState();
        assertThat(loggedIn, is(true));
    }

    @Test
    public void testLoginWithEmptyCredentialsShowsUsernameRequired() {
        User testUserWithoutUsername = UserCreator.withEmptyUsername();
        String errorMessage = new LoginPage(driver)
                .openPage().login(testUserWithoutUsername).getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithEmptyCredentialsShowsPasswordRequired(){
        User testUserWithoutPassword = UserCreator.withEmptyPassword();
        String errorMessage = new LoginPage(driver)
                .openPage().login(testUserWithoutPassword).getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public void TestLoginSuccessSwagLabsWithTitle(){
        User testUser = UserCreator.withCredentialsFromProperty();
        String successfulLogin = new LoginPage(driver)
                .openPage().login(testUser).getLoggedInTitle();
        Assert.assertEquals(successfulLogin, "Swag Labs");
    }

    @DataProvider(name = "allUsers")
    public Object[][] allUsers() {
        List<User> users = UserCreator.getAllUsersFromProperty();
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }

    @Test(dataProvider = "allUsers")
    public void testLoginWithAllUsers(User user) {
        LoginPage loginPage = new LoginPage(driver).openPage();
        MainPage mainPage = loginPage.login(user);

        boolean isErrorPresent = false;
        String errorMsg = "";
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            isErrorPresent = driver.findElements(By.xpath("//h3[@data-test='error']")).size() > 0;
            if (isErrorPresent) {
                errorMsg = mainPage.getErrorMessage();
            }
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        if (isErrorPresent) {
            Assert.assertTrue(
                    errorMsg.contains("locked out") ||
                            errorMsg.contains("Username is required") ||
                            errorMsg.contains("Password is required"),
                    "Unwanted error message : " + errorMsg
            );
        } else {
            Assert.assertEquals(mainPage.getLoggedInTitle(), "Swag Labs", "Failed to login: " + user.getUsername());
        }
    }
}
