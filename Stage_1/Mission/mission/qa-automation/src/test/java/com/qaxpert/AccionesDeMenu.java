package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class AccionesDeMenu {

    @Test
    public void accionesMenu() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de Boni García
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");

        // Localizar los menús usando CSS Selector o XPath avanzado
        WebElement useLeftClickDropdownMenu = driver.findElement(By.cssSelector("#my-dropdown-1"));
        WebElement useRightClickDropdownMenu = driver.findElement(By.cssSelector("#my-dropdown-2"));
        WebElement useDoubleClickDropdownMenu = driver.findElement(By.cssSelector("#my-dropdown-3"));

        // Hacer click, doble clic y click derecho en los elementos seleccionados e imprimir la acción realizada por cada elemento
        Actions actions = new Actions(driver);

        actions.moveToElement(useLeftClickDropdownMenu).perform();
        useLeftClickDropdownMenu.click();
        System.out.println("Clicado normal en el menú 1 (#my-dropdown-1)");

        actions.moveToElement(useRightClickDropdownMenu).perform();
        actions.contextClick(useRightClickDropdownMenu).perform();
        System.out.println("Clicado derecho en el menú 2 (#my-dropdown-2)");

        actions.moveToElement(useDoubleClickDropdownMenu).perform();
        actions.doubleClick(useDoubleClickDropdownMenu).perform();
        System.out.println("Doble clicado en el menú 3 (#my-dropdown-3)");

        // Cerramos el navegador
        driver.quit();
    }
}
