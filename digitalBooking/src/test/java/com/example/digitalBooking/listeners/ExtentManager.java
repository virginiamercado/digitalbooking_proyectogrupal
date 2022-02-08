package com.example.digitalBooking.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {

        System.out.println(fileName);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automatic Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //extent.setSystemInfo("User Name", System.getProperty("user.name"));
        return extent;
    }
}