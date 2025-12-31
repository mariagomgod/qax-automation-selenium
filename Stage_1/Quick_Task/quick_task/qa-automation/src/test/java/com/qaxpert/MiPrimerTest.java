package com.qaxpert;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class MiPrimerTest {

    @Test
    public void searchBlogArticle() throws InterruptedException {
        // 1. Configurar el driver de Chrome automáticamente
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2. Abrir QAXpert
        driver.get("https://qaxpert.com");

        // 3. Hacer click en el menú Blog
        driver.findElement(By.cssSelector("a[href='https://qaxpert.com/blog/']")).click();

        // 4. Para ver el resultado
        Thread.sleep(2000);

        // 5. Cerrar el navegador
        driver.quit();
    }
}

