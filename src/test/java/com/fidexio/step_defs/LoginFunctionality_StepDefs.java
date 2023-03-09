package com.fidexio.step_defs;

import com.fidexio.pages.HomePage;
import com.fidexio.pages.LoginPage;
import com.fidexio.utilities.ConfigurationReader;
import com.fidexio.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginFunctionality_StepDefs {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    String currentUsername, currentPassword;
    @Given("user on the login page")
    public void userOnTheLoginPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
    }

    @When("user enter username {string} in username box")
    public void userEnterUsernameInUsernameBox(String username) {
        loginPage.loginBox.sendKeys(username);
        currentUsername = username;
    }

    @And("user enter password {string} in password box")
    public void userEnterPasswordInPasswordBox(String password) {
        loginPage.passwordBox.sendKeys(password);
        currentPassword = password;
    }

    @And("user click login button")
    public void userClickLoginButton() {
        loginPage.loginBtn.click();
    }

    @Then("user should login homepage")
    public void userShouldLoginHomepage() {
        Assert.assertTrue(homePage.inbox.isDisplayed());
    }

    @Then("user should see wrong credentials message")
    public void userShouldSeeWrongCredentialsMessage() {
        Assert.assertTrue(homePage.wrongCredentialsMsg.isDisplayed());
    }

    @Then("user should see {string} message")
    public void userShouldSeeMessage(String validationMsg) {
        if (currentUsername.isEmpty()){
            if (loginPage.loginBox.getAttribute("required") != null){
                Assert.assertEquals(loginPage.loginBox.getAttribute("validationMessage"), validationMsg);
            }
        } else if (currentPassword.isEmpty()) {
            if (loginPage.passwordBox.getAttribute("required") != null){
                Assert.assertEquals(loginPage.passwordBox.getAttribute("validationMessage"),validationMsg);
            }
        }else {
            Assert.fail();
        }
    }
}
