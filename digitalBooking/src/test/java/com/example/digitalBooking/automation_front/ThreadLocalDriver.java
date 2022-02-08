package com.example.digitalBooking.automation_front;

import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public synchronized static void setTLDriver(WebDriver webDriver) {
        tlDriver.set(webDriver);
    }

    public synchronized static WebDriver getTLDriver() {
        return tlDriver.get();
    }
}

