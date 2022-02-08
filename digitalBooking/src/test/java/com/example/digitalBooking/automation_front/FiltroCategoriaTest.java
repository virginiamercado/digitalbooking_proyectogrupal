package com.example.digitalBooking.automation_front;
import com.example.digitalBooking.automation_front.pages.HomePage;
import com.example.digitalBooking.listeners.ExtentListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

@Listeners(ExtentListeners.class)
public class FiltroCategoriaTest extends HooksFront {

    /**
     * select a category on app
     */

    @Test
    public void selectCategory() throws InterruptedException {
        HomePage homePage = new HomePage(ThreadLocalDriver.getTLDriver());
        String category = homePage.selectRandomCategory();
        homePage.assertFilterByCategory(category);
        Thread.sleep(3000);

        //scroll
        JavascriptExecutor jse = (JavascriptExecutor)ThreadLocalDriver.getTLDriver();
        jse.executeScript("window.scrollBy(0,250)");

        Thread.sleep(3000);
    }
}

