package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavegacionEnElMenuPage extends BasePage {

    private final By burgerMenuLocator = By.id("menu-toggle");
    private final By profileLinkLocator = By.linkText("Profile");
    private final By homepageTitleLocator = By.xpath("//h1[normalize-space()='CURA Healthcare Service']");
    private final By historyTitleLocator = By.xpath("//h2[normalize-space()='History']");
    private final By profileTitleLocator = By.xpath("//h2[normalize-space()='Profile']");

    public NavegacionEnElMenuPage(WebDriver driver) {
        super(driver);
    }

    public void menuLateralYNavegacionAEnlace(String enlace) {
        WebElement burgerMenuIcon = wait.until(
                ExpectedConditions.elementToBeClickable(burgerMenuLocator)
        );
        burgerMenuIcon.click();

        WebElement homeLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText(enlace))
        );
        homeLink.click();
    }

    public boolean imprimirTituloHomePage() {
        WebElement homePageTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(homepageTitleLocator)
        );

        System.out.println(homePageTitle.getText());
        return homePageTitle.isDisplayed();
    }

    public boolean imprimirTituloHistory() {
        WebElement historyTitle = wait.until(
                ExpectedConditions.elementToBeClickable(historyTitleLocator)
        );

        System.out.println(historyTitle.getText());
        return historyTitle.isDisplayed();
    }

    public boolean imprimirTituloProfile() {
        WebElement burgerMenuIcon = wait.until(
                ExpectedConditions.elementToBeClickable(burgerMenuLocator)
        );

        WebElement profileLink = wait.until(
                ExpectedConditions.elementToBeClickable(profileLinkLocator)
        );

        burgerMenuIcon.click();
        profileLink.click();

        WebElement profileTitle = wait.until(
                ExpectedConditions.elementToBeClickable(profileTitleLocator)
        );

        System.out.println(profileTitle.getText());
        return profileTitle.isDisplayed();
    }
}
