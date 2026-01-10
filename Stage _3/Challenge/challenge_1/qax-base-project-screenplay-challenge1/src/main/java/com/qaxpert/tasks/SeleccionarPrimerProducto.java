package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.SearchResultsPage.FIRST_PRODUCT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class SeleccionarPrimerProducto implements Task {

    public static SeleccionarPrimerProducto deResultados() {
        return instrumented(SeleccionarPrimerProducto.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(FIRST_PRODUCT, isClickable()).forNoMoreThan(8).seconds(),
                Click.on(FIRST_PRODUCT)
        );
    }
}

