package com.example.digitalBooking.automation_front.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * http://localhost:8080/index.html
 */

public class HomePage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id=\"menu\"]/div[1]/ul/a[2]")
    private WebElement btnLogin;

    @FindBy(how = How.LINK_TEXT, using = "Crear cuenta")
    private WebElement btnRegister;

    @FindBy(how = How.CLASS_NAME, using = "saludoUser")
    private WebElement btnNombreUsuario;

    @FindBy(how = How.CSS, using = "a:nth-child(1) > li")
    private WebElement btnCrearProducto;

    @FindBy(how = How.CSS, using = "a:nth-child(2) > li")
    private WebElement btnModificarProducto;

    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(driver,10);
    }

    public void clickOnBtnLogin() {
        btnLogin.click();
    }

    public void clickOnBtnRegister() {
        btnRegister.click();
    }

    public void clickOnBtnNombreUsuario() {
        btnNombreUsuario.click();
    }

    public void clickOnBtnCrearProducto() {
        btnCrearProducto.click();
    }

    public void clickOnBtnModificarProducto() {
        btnModificarProducto.click();
    }

    public String selectRandomCategory() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("tituloCategoria")));

        List<WebElement> categorias = driver.findElements(By.className("tituloCategoria"));
        System.out.println("total categorias: "+categorias.size());
        int number = (int)(Math.random() * categorias.size()-1);

        categorias.get(number).click();
        System.out.println("categoría seleccionada: "+categorias.get(number).getText());
        return categorias.get(number).getText();
    }

    public void assertFilterByCategory(String category) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("seccionEstrellas")));
        List<WebElement> tituloProductos = driver.findElements(By.className("seccionEstrellas"));
        System.out.println("titulos encontrado: "+tituloProductos.size());

        for(int i = 0; i < tituloProductos.size(); i++){
            System.out.println("la categoría del producto "+i+" es: "+tituloProductos.get(i).getText());
            Assert.assertTrue(tituloProductos.get(i).getText().contains(category));
        }
    }
}

