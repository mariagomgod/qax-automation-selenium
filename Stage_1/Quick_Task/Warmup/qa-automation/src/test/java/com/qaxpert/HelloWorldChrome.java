package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class HelloWorldChrome {

    @Test
    public void helloWroldChrome() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de QAXPERT
        driver.get("https://qaxpert.com/");

        // Imprimimos el título de la página
        WebElement tituloPagina = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h2.elementor-heading-title.elementor-size-default")
                )
        );
        System.out.println(tituloPagina.getText());
    }
}
