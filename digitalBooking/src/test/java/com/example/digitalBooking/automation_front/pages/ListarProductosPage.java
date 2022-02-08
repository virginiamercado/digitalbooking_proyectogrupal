package com.example.digitalBooking.automation_front.pages;

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
 * http://localhost:8080/CreacionProducto/creacionProducto.html
 */
public class ListarProductosPage {

    private WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "btnModificar")
    private List<WebElement> listaBtnsModificar;

    @FindBy(how = How.CLASS_NAME, using = "btnCancelar")
    private List<WebElement> listaBtnsEliminar;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div[6]/button[1]")
    private WebElement btnConfirmarEliminar;

    private WebDriverWait wait;

    public ListarProductosPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(driver,10);
    }

    public int getTotalProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btnModificar")));
        System.out.println("total productos: "+listaBtnsModificar.size());
        return listaBtnsModificar.size();
    }

    public void editRandomProducto() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btnModificar")));
        System.out.println("total productos: "+listaBtnsModificar.size());
        int number = (int)(Math.random() * listaBtnsModificar.size()-1);
        listaBtnsModificar.get(number).click();
    }

    public void eliminarRandomProducto() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btnCancelar")));
        System.out.println("total productos: "+listaBtnsEliminar.size());
        int number = (int)(Math.random() * listaBtnsEliminar.size()-1);
        listaBtnsEliminar.get(number).click();
    }

      public void clickBtnEliminar(){
        btnConfirmarEliminar.click();

      }
}

