package com.example.digitalBooking.automation_front;

import com.example.digitalBooking.automation_front.pages.HomePage;
import com.example.digitalBooking.automation_front.pages.LoginPage;
import com.example.digitalBooking.listeners.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertTrue;

@Listeners(ExtentListeners.class)
public class LoginTest extends HooksFront {

    /**
     * login in the app
     */
    @Test
    public void loginUser() throws InterruptedException {
        login("daniela@gmail.com", "123Daniela45&", "Daniela");
    }

    @Test
    public void loginAdmin() throws InterruptedException {
        login("Admin@gmail.com", "Charly1234!", "juan");
    }

    public static void login(String username, String password, String name) throws InterruptedException {
        HomePage homePage = new HomePage(ThreadLocalDriver.getTLDriver());
        homePage.clickOnBtnLogin();

        LoginPage loginPage = new LoginPage(ThreadLocalDriver.getTLDriver());
        loginPage.sendKeysOnInputEmail(username);
        loginPage.sendKeysOnInputPassword(password);
        loginPage.clickOnBtnLogin();
        Thread.sleep(3000);
        assertTrue(loginPage.getUserName().contains(name.toUpperCase(Locale.ROOT)),
                "verifica el nombre de usuario");
    }
}