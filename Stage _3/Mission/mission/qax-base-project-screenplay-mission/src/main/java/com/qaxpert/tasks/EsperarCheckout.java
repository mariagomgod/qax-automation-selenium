package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.CheckoutPage.CHECKOUT_FORM;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EsperarCheckout implements Task {

    public static EsperarCheckout visible() {
        return instrumented(EsperarCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CHECKOUT_FORM, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

