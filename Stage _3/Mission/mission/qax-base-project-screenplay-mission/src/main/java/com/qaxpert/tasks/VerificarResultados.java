package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.SearchResultsPage.RESULTS;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VerificarResultados implements Task {

    public static VerificarResultados deBusqueda() {
        return instrumented(VerificarResultados.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(RESULTS, isVisible()).forNoMoreThan(8).seconds()
        );
    }
}

