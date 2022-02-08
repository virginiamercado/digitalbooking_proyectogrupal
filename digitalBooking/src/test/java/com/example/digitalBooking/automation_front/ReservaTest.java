package com.example.digitalBooking.automation_front;
import com.example.digitalBooking.automation_front.pages.BookingPage;
import com.example.digitalBooking.automation_front.pages.LoginPage;
import com.example.digitalBooking.listeners.ExtentListeners;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

@Listeners(ExtentListeners.class)
public class ReservaTest extends HooksFront {

    /**
     * book property on app
     */

    @Test
    public void reservaExitosa() throws InterruptedException {
        LoginTest.login("daniela@gmail.com", "123Daniela45&", "Daniela");
        BookingPage bookingPage = new BookingPage(ThreadLocalDriver.getTLDriver());
        this.intentarReserva();
        Assert.assertTrue(bookingPage.getMessage().equals("Su reserva se ha realizado con éxito"));
    }

    @Test
    public void reservaSinExito() throws InterruptedException {
        intentarReserva();
        LoginPage loginPage = new LoginPage(ThreadLocalDriver.getTLDriver());
        Assert.assertTrue(loginPage.getMessage().equals("Debes Estar Logeado Para Reservar"));
    }

    private void intentarReserva() throws InterruptedException {
        ThreadLocalDriver.getTLDriver().get("http://localhost:8080/Reserva/reservaProducto.html#1");
        BookingPage bookingPage = new BookingPage(ThreadLocalDriver.getTLDriver());
        bookingPage.sendKeysOnInputCity("Cordoba");

        //scroll
        JavascriptExecutor jse = (JavascriptExecutor)ThreadLocalDriver.getTLDriver();
        jse.executeScript("window.scrollBy(0,250)");

        bookingPage.selectHour("06:00 PM");
        bookingPage.selectDates();
        Thread.sleep(1000);
        bookingPage.clickOnConfirm();
        Thread.sleep(5000);
    }
}