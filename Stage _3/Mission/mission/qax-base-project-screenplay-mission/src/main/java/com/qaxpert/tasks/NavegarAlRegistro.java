package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.qaxpert.ui.HomePage.BTN_GO_TO_REGISTER;

public class NavegarAlRegistro implements Task {

    // Este método es una "fábrica".
    // Sirve para crear el Task de una forma legible cuando lo usamos en el Step Definition.
    public static NavegarAlRegistro desdeElHome() {
        return instrumented(NavegarAlRegistro.class);
    }

    // Actor = la persona que usa la aplicación (usuario de prueba).
    // Este método define QUÉ HACE exactamente el actor (usuario).
    @Override
    public <T extends Actor> void performAs(T actor) {
        // attemptsTo = "intenta hacer lo siguiente"
        actor.attemptsTo(
                 Click.on(BTN_GO_TO_REGISTER)
        );
    }
}