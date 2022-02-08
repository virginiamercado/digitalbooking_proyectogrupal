package com.example.digitalBooking.automation_front;
import com.example.digitalBooking.automation_front.pages.FormularioProductoPage;
import com.example.digitalBooking.automation_front.pages.HomePage;
import com.example.digitalBooking.listeners.ExtentListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

@Listeners(ExtentListeners.class)
public class CrearProductoTest extends HooksFront {

    /**
     * create a product
     */
    @Test
    public void crearProducto() throws InterruptedException {
        LoginTest.login("Admin@gmail.com", "Charly1234!", "juan");
        HomePage homePage = new HomePage(ThreadLocalDriver.getTLDriver());
        homePage.clickOnBtnNombreUsuario();
        homePage.clickOnBtnCrearProducto();
        this.fillFormProduct();
    }

    public static void fillFormProduct() throws InterruptedException {
        FormularioProductoPage crearProductoPage = new FormularioProductoPage(ThreadLocalDriver.getTLDriver());
        crearProductoPage.sendKeysOninputNombreProducto("Hotel automatización");
        crearProductoPage.selectCategoria("Resorts");
        crearProductoPage.sendKeysOninputUbicacion("Av flores 12345");
        crearProductoPage.selectCiudad("Madrid");
        Thread.sleep(1000);
        crearProductoPage.sendKeysOninputUbicacionGoogleMaps("http://www.UbicacionGoogle.com");
        crearProductoPage.sendKeysOninputUbicacionGoogleMaps2("http://www.UbicacionGoogle.com");
        crearProductoPage.sendKeysOninputPrecio("1700");
        crearProductoPage.sendKeysOninputPuntaje("8");

        Thread.sleep(1000);
        crearProductoPage.sendKeysOninputDescripcionCorta("Hermoso hotel el centro de la ciudad");
        crearProductoPage.sendKeysOninputDescripcionLarga("Uno de los mejores hoteles que te puede ofrecer la ciudad");
        //scroll
        JavascriptExecutor jse = (JavascriptExecutor)ThreadLocalDriver.getTLDriver();
        jse.executeScript("window.scrollBy(0,250)");
        Thread.sleep(1000);
        crearProductoPage.selectRandomCaracteristica();
        Thread.sleep(1000);
        crearProductoPage.sendKeysOninputNormas("Check in a partir de las 15:00 hrs");
        crearProductoPage.sendKeysOninputSalud("Carnet de vacunación contra COVID");
        crearProductoPage.sendKeysOninputCancelacion("Cancelación con hasta 48h antes del día de la reserva");
        Thread.sleep(1000);
        crearProductoPage.sendKeysOninputImagenUno("http://ImagenUno.com");
        crearProductoPage.sendKeysOninputImagenDos("http://ImagenDos.com");
        crearProductoPage.sendKeysOninputImagenTres("http://ImagenTres.com");
        crearProductoPage.sendKeysOninputImagenCuatro("http://ImagenCuatro.com");
        crearProductoPage.sendKeysOninputImagenCinco("http://ImagenCinco.com");
        Thread.sleep(1000);
        crearProductoPage.clickinputBtnCrearProducto();
        Thread.sleep(1000);
    }
}


