package com.example.digitalBooking.automation_front.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * http://localhost:8080/LogIn/LogIn.html
 */

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.ID, using = "inputCorreoLog")
    private WebElement inputEmail;

    @FindBy(how = How.ID, using = "inputPassLog")
    private WebElement inputPassword;

    @FindBy(how = How.CLASS_NAME, using = "botonCrearCuenta")
    private WebElement btnLogin;

    @FindBy(how = How.CLASS_NAME, using = "saludoUser")
    private WebElement txtUserName;

    @FindBy(how = How.CLASS_NAME, using = "alert-title")
    private WebElement messageAlert;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,15);
        PageFactory.initElements(this.driver, this);
    }

    public void sendKeysOnInputEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void sendKeysOnInputPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void clickOnBtnLogin() {
        btnLogin.click();
    }

    public String getUserName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(txtUserName));
        return txtUserName.getText();
    }

    public String getMessage() {
        return messageAlert.getText();
    }
}
