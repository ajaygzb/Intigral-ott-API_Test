package com.intigral.ott.popcorn.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class will create the Spark Test Report using TestNG's ITestlIstener interface
 */

public class ReportListener implements ITestListener {
    public static ExtentReports extentReporter = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    public void onStart(ITestContext context) {
       
    }

    /**
     * On Test start, create the test list part and enter the details of the test method in the report.
     */
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReporter.createTest(result.getMethod().getMethodName(), result.getMethod().getMethodName());
        test.set(extentTest);
    }

    /**
     * On successfull test completion, this method will right the Test methodname and the
     * user defined success message in the report
     */
    public void onTestSuccess(ITestResult result) {
        test.get().pass(result.getMethod().getMethodName() + " is successfully validated for all the assertions");
    }

    /**
     * If test is skipped, this method will wright the Test methodname and the
     * user defined skip message in the report
     */
    public void onTestSkipped(ITestResult result) {
        test.get().skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " is skipped", ExtentColor.GREY));

    }

    /**
     * On partial test completion, this method will wright the Test methodname and the
     * user defined message in the report
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }


    /**
     * On failed test, this method will right the Test methodname and the
     * user defined fail message in the report along with full error trace.
     */
    public void onTestFailure(ITestResult result) {
        test.get().fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " test failed ", ExtentColor.RED));
        test.get().fail(result.getThrowable());

    }

    /**
     * On completion of  test, this method will flush the listener.
     */
    public void onFinish(ITestContext context) {
        extentReporter.flush();
    }
}
