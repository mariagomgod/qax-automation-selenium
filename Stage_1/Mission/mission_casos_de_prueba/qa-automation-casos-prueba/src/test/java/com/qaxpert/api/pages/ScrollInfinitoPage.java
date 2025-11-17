package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.qaxpert.api.config.Config.SCROLL_PAGE;

public class ScrollInfinitoPage extends BasePage {

    private final By txtElements = By.cssSelector("#content > p");

    public ScrollInfinitoPage(WebDriver driver) {
        super(driver);
    }

    public void navegarAPaginaScrollInfinito() {
        driver.get(SCROLL_PAGE);
    }

    public void hacerScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i <= 20; i++) {
            js.executeScript("window.scrollBy(0, 1000);");
        }

        List<WebElement> elements = driver.findElements(txtElements);

        if (!elements.isEmpty()) {
            WebElement ultimoElemento = elements.getLast();
            js.executeScript("arguments[0].scrollIntoView(true);", ultimoElemento);
        }
    }

    public void imprimirTextos() {
        List<WebElement> elements = driver.findElements(txtElements);

        System.out.println("Total de elementos: " + elements.size());

        for (WebElement element : elements) {
            System.out.println(element.getText());
        }
    }
}
