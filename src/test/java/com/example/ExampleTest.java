package com.example;

import io.perforator.sdk.loadgenerator.core.Perforator;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExampleTest {
    WebElementActions webElementActions;
    @Test
    public void verify() throws Exception {
        RemoteWebDriver driver = PerSuiteRemoteWebDriverManager.getRemoteWebDriver();
        webElementActions = new WebElementActions(driver);
        
        Perforator.transactionally("Open landing page and await to be loaded", () -> {
            driver.navigate().to("https://fifa.new-dev.smartexclusives.com/drops");
            awaitToBeVisible(driver, "#deskHeaderSignInBtn");
        });

        Perforator.transactionally("Click desk header auction button", () -> {
            webElementActions.click(By.id("deskHeaderAuctionBtn"));
        });

        Perforator.transactionally("Click desk header drops button", () -> {
            webElementActions.click(By.id("deskHeaderDropsBtn"));
        });

        Perforator.transactionally("Click desk header marketplace button", () -> {
            webElementActions.click(By.id("deskHeaderMarketplaceBtn"));
        });

        Perforator.transactionally("Click sign in button", () -> {
            webElementActions.click(By.id("deskHeaderSignInBtn"));
        });

    }
    
    private static WebElement awaitToBeVisible(RemoteWebDriver driver, String cssSelector) {
        return awaitToBeVisible(driver, cssSelector, Duration.ofSeconds(30));
    }
    
    private static WebElement awaitToBeVisible(RemoteWebDriver driver, String cssSelector, Duration timeout) {
        return new WebDriverWait(
                driver,
                timeout.toSeconds()
        ).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(cssSelector)
                )
        );
    }
    
    private static WebElement click(RemoteWebDriver driver, String cssSelector) {
        return click(driver, cssSelector, Duration.ofSeconds(30));
    }
    
    private static WebElement click(RemoteWebDriver driver, String cssSelector, Duration timeout) {
        WebElement element = new WebDriverWait(
                driver,
                timeout.toSeconds()
        ).until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(cssSelector)
                )
        );

        element.click();
        return element;
    }
}
    

