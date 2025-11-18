package com.qaxpert.api.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qaxpert.api.utils.RunContext;

import java.util.HashMap;
import java.util.Map;

// Queremos que el ChromeDriver esté disponible antes de cualquier setup de steps.
// No confiamos en un orden alfabético de inicialización (no está garantizado).
// Por eso, la inicialización del driver vive en un hook @BeforeAll(order = 0).

public class StepsSetup {

    @BeforeAll(order = 0)
    public static void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        chromeOptions.setExperimentalOption("prefs", prefs);

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        RunContext.setDriver(driver);
    }

    @AfterAll(order = 0)
    public static void tearDown() {
        WebDriver driver = RunContext.getDriver();
        if (driver != null) {
            driver.quit();
            RunContext.setDriver(null);
        }
    }
}
