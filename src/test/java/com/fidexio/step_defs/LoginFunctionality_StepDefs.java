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
import org.openqa.selenium.Keys;

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

    @Then("user should see {string} warning message")
    public void userShouldSeeWarningMessage(String wrongMsg) {
        Assert.assertTrue(loginPage.wrongCredentialsMsg.isDisplayed());
        Assert.assertEquals(wrongMsg,loginPage.wrongCredentialsMsg.getText());
    }

    @Then("user should see {string} validation message")
    public void userShouldSeeValidationMessage(String validationMsg) {
        if (currentUsername.isEmpty()){
            Assert.assertNotNull(loginPage.loginBox.getAttribute("required"));
            Assert.assertEquals(loginPage.loginBox.getAttribute("validationMessage"), validationMsg);
        } else if (currentPassword.isEmpty()) {
            Assert.assertNotNull(loginPage.passwordBox.getAttribute("required"));
            Assert.assertEquals(loginPage.passwordBox.getAttribute("validationMessage"),validationMsg);
        }
    }

    @Then("user should see bullet signs")
    public void userShouldSeeBulletSigns() {
        Assert.assertEquals("password", loginPage.passwordBox.getAttribute("type"));
    }

    @And("user click enter key of the keyboard")
    public void userClickEnterKeyOfTheKeyboard() {
        loginPage.passwordBox.sendKeys(Keys.ENTER);
    }


}
