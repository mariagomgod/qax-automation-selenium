package com.qaxpert.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.qaxpert.ui.OnlineShopProductListPage.FIRST_QUICK_VIEW;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class AbrirQuickViewOnlineShop implements Task {

    public static AbrirQuickViewOnlineShop desdeElListado() {
        return instrumented(AbrirQuickViewOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(FIRST_QUICK_VIEW, isPresent()).forNoMoreThan(10).seconds()
        );

        jsClick(actor);
    }

    private <T extends Actor> void jsClick(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        // Se usó WebElementFacade porque es el elemento que devuelve Serenity al resolver un Target.
        // Permite interactuar de forma más estable (en este caso, hacer click por JS en Quick View).
        WebElementFacade element = com.qaxpert.ui.OnlineShopProductListPage.FIRST_QUICK_VIEW.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}



