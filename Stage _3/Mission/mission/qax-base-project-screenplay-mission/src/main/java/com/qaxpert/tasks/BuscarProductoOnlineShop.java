package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static com.qaxpert.ui.OnlineShopSearchPage.SEARCH_INPUT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class BuscarProductoOnlineShop implements Task {

    private final String termino;

    public BuscarProductoOnlineShop(String termino) {
        this.termino = termino;
    }

    public static BuscarProductoOnlineShop porTermino(String termino) {
        return instrumented(BuscarProductoOnlineShop.class, termino);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(SEARCH_INPUT, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(termino).into(SEARCH_INPUT),
                Hit.the(Keys.ENTER).into(SEARCH_INPUT)
        );
    }
}
