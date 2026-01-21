package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopCartPage.CART_MAIN;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IrAlCarritoOnlineShop implements Task {

    public static IrAlCarritoOnlineShop porUrl() {
        return instrumented(IrAlCarritoOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("http://www.testingyes.com/onlineshop/cart"),
                WaitUntil.the(CART_MAIN, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}