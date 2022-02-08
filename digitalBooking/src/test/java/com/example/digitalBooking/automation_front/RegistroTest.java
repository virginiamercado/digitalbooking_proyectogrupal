package com.example.digitalBooking.automation_front;
import com.example.digitalBooking.automation_front.pages.HomePage;
import com.example.digitalBooking.automation_front.pages.RegistroPage;
import com.example.digitalBooking.listeners.ExtentListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

@Listeners(ExtentListeners.class)
public class RegistroTest extends HooksFront {

    /**
     * select a category on app
     */
    @Test
    public void registerTest() throws InterruptedException {
        HomePage homePage = new HomePage(ThreadLocalDriver.getTLDriver());
        homePage.clickOnBtnRegister();

        RegistroPage registroPage = new RegistroPage(ThreadLocalDriver.getTLDriver());
        registroPage.sendKeysOnInputName("Daniela");
        registroPage.sendKeysOnInputLastName("Cuellar");
        registroPage.selectCountry("Argentina");
        registroPage.sendKeysOnInputDate("02/01/1991");
        registroPage.sendKeysOnInputEmail("daniela2@gmail.com");
        registroPage.sendKeysOnInputPassword("123Hoteles45&");
        registroPage.sendKeysOnInputPasswordEye();
        registroPage.sendKeysOnInputPasswordRep("123Hoteles45&");
        Thread.sleep(3000);
        ThreadLocalDriver.getTLDriver().findElement(By.cssSelector(".botonCrearCuenta")).click();
        Thread.sleep(3000);
    }
}

