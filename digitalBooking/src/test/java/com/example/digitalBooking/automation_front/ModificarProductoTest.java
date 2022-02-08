package com.example.digitalBooking.automation_front;
import com.example.digitalBooking.automation_front.pages.FormularioProductoPage;
import com.example.digitalBooking.automation_front.pages.HomePage;
import com.example.digitalBooking.automation_front.pages.ListarProductosPage;
import com.example.digitalBooking.listeners.ExtentListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(ExtentListeners.class)
public class ModificarProductoTest extends HooksFront {

    /**
     * modify a product
     */
    @Test
    public void modificarProductos() throws InterruptedException {
        LoginTest.login("Admin@gmail.com", "Charly1234!", "juan");

        HomePage homePage = new HomePage(ThreadLocalDriver.getTLDriver());
        homePage.clickOnBtnNombreUsuario();
        homePage.clickOnBtnModificarProducto();

        ListarProductosPage listarProductosPage = new ListarProductosPage(ThreadLocalDriver.getTLDriver());
        listarProductosPage.editRandomProducto();
        CrearProductoTest.fillFormProduct();

        FormularioProductoPage editarProductoPage = new FormularioProductoPage(ThreadLocalDriver.getTLDriver());
        assertTrue(editarProductoPage.assertMessage().contains("éxito"),
                "no fue posible crear el producto");

    }
}


