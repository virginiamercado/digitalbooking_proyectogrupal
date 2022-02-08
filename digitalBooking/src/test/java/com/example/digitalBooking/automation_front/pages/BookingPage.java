package com.example.digitalBooking.automation_front.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * http://localhost:8080/Reserva/ReservaProducto.html
 */
public class BookingPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "nombre")
    private WebElement inputName;

    @FindBy(how = How.ID, using = "apellido")
    private WebElement inputLastName;

    @FindBy(how = How.ID, using = "ciudad")
    private WebElement selectCiudad;

    @FindBy(how = How.ID, using = "correo")
    private WebElement inputEmail;

    @FindBy(how = How.ID, using = "hora")
    private WebElement selectHour;

    @FindBy(how = How.CSS, using = ".boton_enviar")
    private WebElement btnConfirm;

    @FindBy(how = How.ID, using = "mensaje_modal")
    private WebElement messageModal;
    
    public BookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void sendKeysOnInputName(String name) {
        inputName.sendKeys(name);
    }

    public void sendKeysOnInputLastName(String lastname) {
        inputLastName.sendKeys(lastname);
    }

    public void sendKeysOnInputCity(String city) {
        selectCiudad.sendKeys(city);
    }

    public void selectHour(String hour) throws InterruptedException {
        selectHour.click();
        driver.findElement(By.xpath("//option[. = '"+hour+"']")).click();
        Thread.sleep(2000);
    }

    public void sendKeysOnInputEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void selectDates() throws InterruptedException {
        List<WebElement> datesAvailables = driver.findElements(By.cssSelector(".available"));
        datesAvailables.get(0).click();
        Thread.sleep(1000);
        datesAvailables = driver.findElements(By.cssSelector(".available"));
        datesAvailables.get(1).click();
    }

    public void clickOnConfirm() {
        btnConfirm.click();
    }

    public String getMessage() {
        return messageModal.getText();
    }
}

