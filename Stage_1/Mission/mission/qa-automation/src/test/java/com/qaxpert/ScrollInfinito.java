package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ScrollInfinito {

    @Test
    public void scrollInfinito() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de Boni García
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");

        // Hacemos scroll hacia abajo hasta que se carguen al menos 20 nuevos elementos
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i <= 20; i++) {
            js.executeScript("window.scrollBy(0, 1000);");
        }

        List<WebElement> elements = driver.findElements(By.cssSelector("#content > p"));

        if (!elements.isEmpty()) {
            WebElement ultimoElemento = elements.get(elements.size() - 1);
            js.executeScript("arguments[0].scrollIntoView(true);", ultimoElemento);
        }

        // Imprimimos los textos de los elementos visibles
        System.out.println("Total de elementos: " + elements.size());

        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        // Cerramos el navegador
        driver.quit();
    }
}
