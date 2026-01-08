package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.HomePage.BTN_LOGOUT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class CerrarSesion implements Task {

    public static CerrarSesion ahora() {
        return instrumented(CerrarSesion.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // Espera a que el botón esté listo para click
                WaitUntil.the(BTN_LOGOUT, isClickable()).forNoMoreThan(5).seconds(),
                Click.on(BTN_LOGOUT)
        );
    }
}
