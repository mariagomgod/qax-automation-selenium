package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopProductDetailPage.QTY_INPUT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CambiarCantidadOnlineShop implements Task {

    private final int cantidad;

    public CambiarCantidadOnlineShop(int cantidad) {
        this.cantidad = cantidad;
    }

    public static CambiarCantidadOnlineShop a(int cantidad) {
        return instrumented(CambiarCantidadOnlineShop.class, cantidad);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(QTY_INPUT, isVisible()).forNoMoreThan(10).seconds(),
                Clear.field(QTY_INPUT),
                Enter.theValue(String.valueOf(cantidad)).into(QTY_INPUT)
        );
    }
}
