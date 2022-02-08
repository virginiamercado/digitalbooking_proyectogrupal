package com.example.digitalBooking.automation_front.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * http://localhost:8080/Registro/Registro.html
 */

public class RegistroPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "nombre")
    private WebElement inputName;

    @FindBy(how = How.ID, using = "apellido")
    private WebElement inputLastName;

    @FindBy(how = How.ID, using = "fecha")
    private WebElement inputDate;

    @FindBy(how = How.ID, using = "paises")
    private WebElement selectCountry;

    @FindBy(how = How.ID, using = "correo")
    private WebElement inputEmail;

    @FindBy(how = How.ID, using = "password")
    private WebElement inputPassword;

    @FindBy(how = How.ID, using = "ojoPass")
    private WebElement btnPasswordEye;

    @FindBy(how = How.ID, using = "passwordRep")
    private WebElement inputPasswordRep;


    public RegistroPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void sendKeysOnInputName(String name) { inputName.sendKeys(name); }
    public void sendKeysOnInputLastName(String lastname) { inputLastName.sendKeys(lastname); }
    public void sendKeysOnInputDate(String date) { inputDate.sendKeys(date); }

    public void selectCountry(String country) {
        selectCountry.click();
        driver.findElement(By.xpath("//option[. = '"+country+"']")).click();
    }

    public void sendKeysOnInputEmail(String email) { inputEmail.sendKeys(email); }
    public void sendKeysOnInputPassword(String password) { inputPassword.sendKeys(password); }
    public void sendKeysOnInputPasswordEye() { btnPasswordEye.click(); }
    public void sendKeysOnInputPasswordRep(String passwordrep) { inputPasswordRep.sendKeys(passwordrep); }
}


