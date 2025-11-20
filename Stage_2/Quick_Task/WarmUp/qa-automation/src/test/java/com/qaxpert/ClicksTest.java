package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClicksTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
    }

    @Test
    public void clicks() {

        // SELECT DROPDOWN
        WebElement useLeftClickBtn = driver.findElement(By.cssSelector("#my-dropdown-1"));

        Actions actions = new Actions(driver);
        actions.contextClick(useLeftClickBtn).perform();

        System.out.println("Dropdown ejecutado correctamente.");

        // CLICK DERECHO
        WebElement useRightClickBtn = driver.findElement(By.cssSelector("#my-dropdown-2"));

        actions.contextClick(useRightClickBtn).perform();

        System.out.println("Click derecho ejecutado correctamente.");

        // DOBLE CLICK
        WebElement useDoubleClickBtn = driver.findElement(By.cssSelector("#my-dropdown-3"));
        actions.doubleClick(useDoubleClickBtn).perform();

        System.out.println("Doble clic ejecutado correctamente.");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
