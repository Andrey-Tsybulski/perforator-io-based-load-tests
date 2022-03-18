package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.*;


public class WebElementActions {

    public WebElementActions(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    protected WebDriverWait wait;


    public static Wait<WebDriver> fluentWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    protected WebElementActions waitModal(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(TimeoutException.class);
        return this;
    }

    protected WebElementActions waitModalViaWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(TimeoutException.class);
        return this;
    }

    protected WebElementActions waitModalForClickableElements(By key) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(key));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(TimeoutException.class);
        return this;
    }

    public WebElement getElement(By locator) {
        waitModal(locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public WebElement getElementFromList(By locator) {
        waitModal(locator);
        List<WebElement> items = driver.findElements(locator);
        WebElement item = items.stream().findFirst().get();
        return item;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebElementActions getUrl(String url) {
        driver.get(url);
        return this;
    }

    public Set getCookies(String url) {
       return driver.manage().getCookies();
    }


    public WebElementActions input(By key, String value) {
        waitModal(key);
        WebElement element = getElement(key);
        element.clear();
        element.sendKeys(value);
        return this;
    }

    public WebElementActions click(By key) {
        waitModal(key);
        WebElement element = getElement(key);
        element.click();
        return this;
    }

    public WebElementActions clickViaWebElement(WebElement key) {
        waitModalViaWebElement(key);
        key.click();
        return this;
    }

    public WebElementActions moveTo(By key) {
        waitModal(key);
        WebElement element = getElement(key);
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
        return this;
    }

    public WebElementActions moveToWithOffset(By key) {
        waitModalForClickableElements(key);
        WebElement element = getElement(key);
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 1, 1).perform();
        return this;
    }

    public WebElementActions jsClick(By key) {
        waitModal(key);
        WebElement element = getElement(key);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        return this;
    }

    public WebElementActions physicalMouseClick(By key) {
        WebElement element = driver.findElement(key);
        Actions builder = new Actions(driver);
        builder.moveToElement(element).click(element);
        builder.perform();
        return this;
    }

    public WebElementActions scrollDownToElement(By key) {
        WebElement locator = getElement(key);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", locator);
        return this;
    }

    public WebElementActions scrollDown() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,100)");
        return this;
    }

    public WebElementActions scrollUp() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0, -100)");
        return this;
    }

    public boolean isClickable(By locator) {
        WebElement webElement = getElement(locator);
        return isClickable(webElement);
    }

    public boolean isClickable(WebElement element) {
        return element.isEnabled() && element.isDisplayed();
    }

    public boolean isPresent(By key) {
        waitModal(key);
        return driver.findElements(key).size() > 0;
    }

    public boolean isDisplayed(By key) {
        waitModal(key);
        return driver.findElement(key).isDisplayed();
    }

    public String getElementsText(WebElement element) {
        return element.getText();
    }

    public void refresh() {

        driver.navigate().refresh();
    }

    public WebElement fluentWaitOfWebElement(final By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(driver -> driver.findElement(locator));
        return foo;
    }
}