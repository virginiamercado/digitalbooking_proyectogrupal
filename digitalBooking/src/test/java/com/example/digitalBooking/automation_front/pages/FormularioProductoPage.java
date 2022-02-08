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
public class FormularioProductoPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "inputNombreProducto")
    private WebElement inputNombreProducto;

    @FindBy(how = How.ID, using = "categorias")
    private WebElement selectCategoria;

    @FindBy(how = How.ID, using = "inputUbicacion")
    private WebElement inputUbicacion;

    @FindBy(how = How.NAME, using = "ciudad")
    private WebElement selectCiudad;

    @FindBy(how = How.ID, using = "inputUbicacionGoogleMaps")
    private WebElement inputUbicacionGoogleMaps;

    @FindBy(how = How.ID, using = "inputUbicacionGoogleMaps2")
    private WebElement inputUbicacionGoogleMaps2;

    @FindBy(how = How.ID, using = "inputPrecio")
    private WebElement inputPrecio;

    @FindBy(how = How.ID, using = "inputPuntaje")
    private WebElement inputPuntaje;

    @FindBy(how = How.ID, using = "text-area-descripcion-corta")
    private WebElement inputDescripcionCorta;

    @FindBy(how = How.ID, using = "text-area-descripcion-larga")
    private WebElement inputDescripcionLarga;

    @FindBy(how = How.ID, using = "text-area-normas")
    private WebElement inputNormas;

    @FindBy(how = How.ID, using = "text-area-salud")
    private WebElement inputSalud;

    @FindBy(how = How.ID, using = "text-area-cancelacion")
    private WebElement inputCancelacion;

    @FindBy(how = How.ID, using = "img-1")
    private WebElement inputImagenUno;

    @FindBy(how = How.ID, using = "img-2")
    private WebElement inputImagenDos;

    @FindBy(how = How.ID, using = "img-3")
    private WebElement inputImagenTres;

    @FindBy(how = How.ID, using = "img-4")
    private WebElement inputImagenCuatro;

    @FindBy(how = How.ID, using = "img-5")
    private WebElement inputImagenCinco;

    @FindBy(how = How.CSS, using = ".btn-crearProducto")
    private WebElement inputBtnCrearProducto;

    @FindBy(how = How.ID, using = "mensaje_modal")
    private WebElement mensajeConfirmacion;

    private WebDriverWait wait;

    public FormularioProductoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(driver,10);
    }

    public void sendKeysOninputNombreProducto(String name) {
        inputNombreProducto.clear();
        inputNombreProducto.sendKeys(name);
    }

    public void sendKeysOninputUbicacion(String ubicacion) {
        inputUbicacion.clear();
        inputUbicacion.sendKeys(ubicacion);
    }
    public void sendKeysOninputUbicacionGoogleMaps(String URLGoogle) {
        inputUbicacionGoogleMaps.clear();
        inputUbicacionGoogleMaps.sendKeys(URLGoogle);
    }
    public void sendKeysOninputUbicacionGoogleMaps2(String URLGoogle2) {
        inputUbicacionGoogleMaps2.clear();
        inputUbicacionGoogleMaps2.sendKeys(URLGoogle2);
    }
    public void sendKeysOninputPrecio(String precio) {
        inputPrecio.clear();
        inputPrecio.sendKeys(precio);
    }

    public void sendKeysOninputPuntaje(String puntaje) {
        inputPuntaje.clear();
        inputPuntaje.sendKeys(puntaje);
    }
    public void sendKeysOninputDescripcionCorta(String descripcionCorta) {
        inputDescripcionCorta.clear();
        inputDescripcionCorta.sendKeys(descripcionCorta);
    }
    public void sendKeysOninputDescripcionLarga(String descripcionLarga) {
        inputDescripcionLarga.clear();
        inputDescripcionLarga.sendKeys(descripcionLarga);
    }
    public void sendKeysOninputNormas(String normas) {
        inputNormas.clear();
        inputNormas.sendKeys(normas);
    }
    public void sendKeysOninputSalud(String salud) {
        inputSalud.clear();
        inputSalud.sendKeys(salud);
    }
    public void sendKeysOninputCancelacion(String cancelacion) {
        inputCancelacion.clear();
        inputCancelacion.sendKeys(cancelacion);
    }
    public void sendKeysOninputImagenUno(String URLImagenUno) {
        inputImagenUno.clear();
        inputImagenUno.sendKeys(URLImagenUno);
    }
    public void sendKeysOninputImagenDos(String URLImagenDos) {
        inputImagenDos.clear();
        inputImagenDos.sendKeys(URLImagenDos);
    }

    public void sendKeysOninputImagenTres(String URLImagenTres) {
        inputImagenTres.clear();
        inputImagenTres.sendKeys(URLImagenTres);
    }
    public void sendKeysOninputImagenCuatro(String URLImagenCuatro) {
        inputImagenCuatro.clear();
        inputImagenCuatro.sendKeys(URLImagenCuatro);
    }
    public void sendKeysOninputImagenCinco(String URLImagenCinco) {
        inputImagenCinco.clear();
        inputImagenCinco.sendKeys(URLImagenCinco);
    }
    public void clickinputBtnCrearProducto() {
        inputBtnCrearProducto.click();
    }

    public void selectCategoria(String categorias) {
        selectCategoria.click();
        driver.findElement(By.xpath("//option[. = '"+categorias+"']")).click();
    }

    public void selectCiudad(String ciudad) {
        selectCiudad.click();
        driver.findElement(By.xpath("//option[. = '"+ciudad+"']")).click();
    }

    public void selectRandomCaracteristica() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("checkbox-caracteristicas")));
        List<WebElement> caracteristicas = driver.findElements(By.className("checkbox-caracteristicas"));
        System.out.println("total caracteristicas: "+caracteristicas.size());
        int number = (int)(Math.random() * caracteristicas.size()-1);

        caracteristicas.get(number).click();
        if(number==0){
            caracteristicas.get(number+1).click();
        }else{
            caracteristicas.get(number-1).click();
        }

        System.out.println("Caracteristicas seleccionada: "+caracteristicas.get(number).getAttribute("value"));
    }

    public String assertMessage() {
        return mensajeConfirmacion.getText();
    }
}

