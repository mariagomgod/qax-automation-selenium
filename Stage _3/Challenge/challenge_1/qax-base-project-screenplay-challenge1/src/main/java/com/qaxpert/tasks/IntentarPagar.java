package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.CheckoutPage.BTN_PLACE_ORDER;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class IntentarPagar implements Task {

    public static IntentarPagar sinCompletarDatos() {
        return instrumented(IntentarPagar.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(BTN_PLACE_ORDER, isClickable()).forNoMoreThan(8).seconds(),
                Click.on(BTN_PLACE_ORDER)
        );
    }
}

