package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.CheckoutPage.RADIO_WOMPI;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class SeleccionarWompi implements Task {

    public static SeleccionarWompi comoMedioDePago() {
        return instrumented(SeleccionarWompi.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(RADIO_WOMPI, isClickable()).forNoMoreThan(8).seconds(),
                Click.on(RADIO_WOMPI)
        );
    }
}

