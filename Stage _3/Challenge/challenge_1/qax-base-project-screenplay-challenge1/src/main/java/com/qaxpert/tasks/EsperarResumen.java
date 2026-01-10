package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.CheckoutPage.ORDER_REVIEW;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EsperarResumen implements Task {

    public static EsperarResumen visible() {
        return instrumented(EsperarResumen.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ORDER_REVIEW, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

