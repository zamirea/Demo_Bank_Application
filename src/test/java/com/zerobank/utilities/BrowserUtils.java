package com.zerobank.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {
    private static String parentWindow;

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement getElement(String xpath, String value) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(xpath, value))));
    }

    public static WebElement getElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(xpath)));
    }

    public static List<WebElement> getElements(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static Select getDropdown(WebElement element) {
        return new Select(element);
    }

    public static Select getDropdown(String xpath, String value) {
        return new Select(getElement(xpath, value));
    }

    public static void clickElement(WebElement element) {
        waitUntilClickable(element).click();
    }

    public static void clickAndSend(String xpath, String value) {
        WebElement element = getElement(xpath);
        clickElement(element);
        BrowserUtils.sleep(0.5);
        element.sendKeys(value);
    }

    public static void clickElement(String xpath) {
        waitUntilClickable(getElement(xpath)).click();
    }

    public static void clickElement(String xpath, String value) {
        waitUntilClickable(getElement(xpath, value)).click();
    }

    public static void jseSendKeys(String elementID, String input) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("document.getElementById(" + "'" + elementID + "')" +
                ".setAttribute('value', " + "'" + input + "')");
    }

    public static String getAlertText() {
        String alert = Driver.getDriver().switchTo().alert().getText();
        Driver.getDriver().switchTo().alert().accept();
        return alert;
    }

    public static String getChildWindowURL() {
        switchToChildWindow();
        return Driver.getDriver().getCurrentUrl();
    }

    public static void scrollTo(String xpath, String value) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                getElement(xpath, value));
    }

    public static void scrollTo(String xpath) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                getElement(xpath));
    }

    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                element);
    }

    public static void scrollAndClick(String xpath, String value) {
        scrollTo(xpath, value);
        clickElement(xpath, value);
    }

    public static void setParentWindow() {
        parentWindow = Driver.getDriver().getWindowHandle();
    }

    public static void switchToChildWindow() {
        setParentWindow();
        Set<String> windows = Driver.getDriver().getWindowHandles();
        for (String each : windows) {
            if (!each.equals(parentWindow)) {
                Driver.getDriver().switchTo().window(each);
            }
        }
    }

    public static void switchToParentWindow() {
        Driver.getDriver().switchTo().window(parentWindow);
    }

    public static boolean elementDisplayed(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
    }

    public static boolean elementDisplayed(String xpath, String value) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, value)))).isDisplayed();
    }

    public static boolean elementSelected(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isSelected();
    }

    public static boolean elementSelected(String xpath, String value) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions.elementToBeSelected(By.xpath(String.format(xpath, value))));
    }

    public static List<String> getTextOfElements(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            WebElement eachElement = elements.get(i);
            texts.add(eachElement.getText().trim());
        }
        return texts;
    }

    public static WebElement waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForClickable(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitUntilTextToBe(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilUrlToBe(String url) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static void waitUntilTitleContains(String title) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilTitleIs(String title) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilNewWindowOpens(int numberOfWindows) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
//        wait.until(File.Exists())
    }

    public static void hoverOver(String xpath) {
        Actions act = new Actions(Driver.getDriver());
        act.moveToElement(Driver.getDriver().findElement(By.xpath(xpath))).perform();
    }

    public static void turnOnImplicitWait() {
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void turnOffImplicitWait() {
        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
    }

    public static String getCurrentPageTitle() {
        return Driver.getDriver().getTitle();
    }

    public static String getCurrentPageURL() {
        return Driver.getDriver().getCurrentUrl();
    }

    public static void closeCurrentPage() {
        Driver.getDriver().close();
    }

    public static void navigateBack() {
        Driver.getDriver().navigate().back();
    }

    public static void waitUntilVisibilityOfAllElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
