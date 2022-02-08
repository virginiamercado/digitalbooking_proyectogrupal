package com.example.digitalBooking.automation_front;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class HooksFront {

    @BeforeMethod
    public void initDriver() {
        System.out.println("@BeforeMethod");

        final int TIME_TO_WAIT_SEC = 10;
        System.setProperty("webdriver.chrome.driver", "chromedriver\\95\\chromedriver.exe");
        ThreadLocalDriver.setTLDriver(new ChromeDriver());
        ThreadLocalDriver.getTLDriver().manage().timeouts().implicitlyWait(TIME_TO_WAIT_SEC, TimeUnit.SECONDS);
        ThreadLocalDriver.getTLDriver().manage().deleteAllCookies();
        ThreadLocalDriver.getTLDriver().get("http://localhost:8080/index.html");
        ThreadLocalDriver.getTLDriver().manage().window().setSize(new Dimension(1936, 1056));
    }

    @AfterMethod
    public void teardown() {
        System.out.println("@AfterMethod");
        ThreadLocalDriver.getTLDriver().quit();
    }

}


