package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.ScrollTo;
import net.serenitybdd.screenplay.actions.ScrollToTarget;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.ProductPage.BTN_BUY_NOW;
import static com.qaxpert.ui.ProductPage.FIRST_VARIATION_OPTION;
import static com.qaxpert.ui.ProductPage.OPTION_DROPDOWN;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ComprarAhora implements Task {

    public static ComprarAhora desdeProducto() {
        return instrumented(ComprarAhora.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        try {
            actor.attemptsTo(
                    WaitUntil.the(OPTION_DROPDOWN, isClickable()).forNoMoreThan(2).seconds(),
                    Click.on(OPTION_DROPDOWN)
            );
        } catch (Exception ignored) {
            // Si no hay variación o no es interactuable, seguimos sin fallar
        }

        actor.attemptsTo(
                Scroll.to(OPTION_DROPDOWN),
                WaitUntil.the(FIRST_VARIATION_OPTION, isClickable()).forNoMoreThan(2).seconds(),
                Click.on(FIRST_VARIATION_OPTION)
        );

        actor.attemptsTo(
                WaitUntil.the(BTN_BUY_NOW, isClickable()).forNoMoreThan(8).seconds(),
                Click.on(BTN_BUY_NOW)
        );
    }
}

