package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopProductDetailPage.BTN_ADD_TO_CART;
import static com.qaxpert.ui.OnlineShopCartModal.MODAL;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarAlCarritoDesdeDetalleOnlineShop implements Task {

    public static AgregarAlCarritoDesdeDetalleOnlineShop ahora() {
        return instrumented(AgregarAlCarritoDesdeDetalleOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_ADD_TO_CART),
                WaitUntil.the(MODAL, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
