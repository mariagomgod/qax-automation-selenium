package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopProductDetailPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CambiarTallaYColorOnlineShop implements Task {

    public static CambiarTallaYColorOnlineShop enElDetalle() {
        return instrumented(CambiarTallaYColorOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(SIZE_SELECT, isVisible()).forNoMoreThan(10).seconds(),
                // cambia a índice 2 (tercera opción) si existe
                SelectFromOptions.byIndex(2).from(SIZE_SELECT),

                Click.on(SECOND_COLOR_RADIO)
        );
    }
}

