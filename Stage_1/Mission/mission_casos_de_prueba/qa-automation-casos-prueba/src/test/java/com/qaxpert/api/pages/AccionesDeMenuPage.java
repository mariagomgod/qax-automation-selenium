package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccionesDeMenuPage extends BasePage{

    private final By useLeftClickDropdownMenuLocator = By.cssSelector("#my-dropdown-1");
    private final By useRightClickDropdownMenuLocator = By.cssSelector("#my-dropdown-2");
    private final By useDoubleClickDropdownMenuLocator = By.cssSelector("#my-dropdown-3");

    public AccionesDeMenuPage(WebDriver driver) {
        super(driver);
    }

    public void useLeftClickHereMenu() {
        WebElement useLeftClickDropdownMenu = wait.until(
                ExpectedConditions.elementToBeClickable(useLeftClickDropdownMenuLocator)
        );
        new Actions(driver).contextClick(useLeftClickDropdownMenu).perform();
    }

    public void useRightClickHereMenu() {
        WebElement useRightClickDropdownMenu = wait.until(
                ExpectedConditions.elementToBeClickable(useRightClickDropdownMenuLocator)
        );
        new Actions(driver).contextClick(useRightClickDropdownMenu).perform();
    }

    public void useDoubleClickHereMenu() {
        WebElement useDoubleClickDropdownMenu = wait.until(
                ExpectedConditions.elementToBeClickable(useDoubleClickDropdownMenuLocator)
        );
        new Actions(driver).doubleClick(useDoubleClickDropdownMenu).perform();
    }

    public void imprimirAccionClickBasico() {
        System.out.println("Clicado normal en el menú 1 (#my-dropdown-1)");
    }

    public void imprimirAccionClickDerecho() {
        System.out.println("Clicado derecho en el menú 2 (#my-dropdown-2)");
    }

    public void imprimirAccionClickDoble() {
        System.out.println("Doble clicado en el menú 3 (#my-dropdown-3)");
    }
}
