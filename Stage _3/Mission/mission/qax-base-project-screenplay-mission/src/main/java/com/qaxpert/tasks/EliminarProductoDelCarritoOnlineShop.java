package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopCartPage.EMPTY_CART_MESSAGE;
import static com.qaxpert.ui.OnlineShopCartPage.FIRST_ITEM_REMOVE;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EliminarProductoDelCarritoOnlineShop implements Task {

    public static EliminarProductoDelCarritoOnlineShop primero() {
        return instrumented(EliminarProductoDelCarritoOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(FIRST_ITEM_REMOVE, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(FIRST_ITEM_REMOVE),

                // Espera a que el carrito quede vacío
                WaitUntil.the(EMPTY_CART_MESSAGE, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}