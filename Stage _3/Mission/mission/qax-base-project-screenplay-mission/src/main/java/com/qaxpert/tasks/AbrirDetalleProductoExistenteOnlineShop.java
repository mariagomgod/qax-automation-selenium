package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopProductListPage.FIRST_PRODUCT_LINK;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class AbrirDetalleProductoExistenteOnlineShop implements Task {

    public static AbrirDetalleProductoExistenteOnlineShop desdeElListado() {
        return instrumented(AbrirDetalleProductoExistenteOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(FIRST_PRODUCT_LINK, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(FIRST_PRODUCT_LINK)
        );
    }
}
