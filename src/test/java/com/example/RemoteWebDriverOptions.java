package com.example;


import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class RemoteWebDriverOptions{
    public DesiredCapabilities createRemoteCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> map = new HashMap<>();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.addArguments("start-maximized");
                options.addArguments("--incognito");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--ignore-certificate-errors");
                map.put("enableVNC", true);
                map.put("enableVideo", true);
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", "97.0");
                capabilities.setCapability("selenoid:options", map);
                capabilities.setCapability("acceptInsecureCerts", true);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                return capabilities;
    }
}




