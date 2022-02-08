package com.example.digitalBooking.automation_front;

import com.example.digitalBooking.automation_front.pages.HomePage;
import com.example.digitalBooking.automation_front.pages.ListarProductosPage;
import com.example.digitalBooking.listeners.ExtentListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;

import static org.testng.Assert.assertEquals;

@Listeners(ExtentListeners.class)
public class EliminarProductoTest extends HooksFront {

    /**
     * delete a product
     */
    @Test
    public void eliminarUnProducto() throws InterruptedException {
        LoginTest.login("Admin@gmail.com", "Charly1234!", "juan");

        HomePage homePage = new HomePage(ThreadLocalDriver.getTLDriver());
        homePage.clickOnBtnNombreUsuario();
        homePage.clickOnBtnModificarProducto();

        ListarProductosPage listarProductosPage = new ListarProductosPage(ThreadLocalDriver.getTLDriver());
        int totalAntes = listarProductosPage.getTotalProducts();
        listarProductosPage.eliminarRandomProducto();
        listarProductosPage.clickBtnEliminar();

        Thread.sleep(2000);
        Alert alert = ThreadLocalDriver.getTLDriver().switchTo().alert();
        alert.accept();
        Thread.sleep(1000);

        int totalDespues = listarProductosPage.getTotalProducts();

        assertEquals(totalAntes-1, totalDespues,
                "no fue posible eliminar el producto");

    }
}