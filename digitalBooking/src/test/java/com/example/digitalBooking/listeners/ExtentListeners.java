package com.example.digitalBooking.listeners;

import java.util.Date;

import com.example.digitalBooking.automation_front.ThreadLocalDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentListeners implements ITestListener, ISuiteListener {
    static Date d = new Date();
    static String fileName = "/Report_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

    private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir") + fileName);
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getTestClass().getName() + "@TestCase : " + result.getMethod().getMethodName());
        testReport.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String logText = "<b>Test case passed</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(m);
        testReport.get().assignCategory(result.getTestClass().getName());
        extent.flush();
    }

    public void onTestFailure(ITestResult result) {
        String excepionMessage = result.getThrowable().toString();
        testReport.get()
                .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured: Click to see"
                        + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");

        try {
            String base64Screenshot = "data:image/png;base64,"
                    + ((TakesScreenshot) ThreadLocalDriver.getTLDriver()).getScreenshotAs(OutputType.BASE64);
            testReport.get().fail("<b><font color='red'>" + "Click to see screenshot of the fail" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {

        }

        String failureLogg = "<b>Test case failed</b>";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testReport.get().log(Status.FAIL, m);
        testReport.get().assignCategory(result.getTestClass().getName());
        extent.flush();
    }

    public void onTestSkipped(ITestResult result) {
        String excepionMessage = result.getThrowable().toString();
        testReport.get()
                .skip("<details>" + "<summary>" + "<b>" + "<font color=" + "yellow>" + "Test Skipped: Click to see"
                        + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");

        String logText = "<b>Test case skipped</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().skip(m);
        testReport.get().assignCategory(result.getTestClass().getName());
        extent.flush();
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onStart(ISuite suite) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }
}