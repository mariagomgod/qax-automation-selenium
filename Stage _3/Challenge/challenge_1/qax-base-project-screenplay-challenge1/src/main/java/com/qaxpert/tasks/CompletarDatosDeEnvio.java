package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.CheckoutPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletarDatosDeEnvio implements Task {
    // Completo solamente los datos obligatorios
    private final String email;
    private final String nombre;
    private final String apellido;
    private final String cedula;
    private final String tipoDoc;
    private final String numDoc;
    private final String telefono;

    public CompletarDatosDeEnvio(String email, String nombre, String apellido, String cedula, String tipoDoc, String numDoc, String telefono) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.telefono = telefono;
    }

    public static CompletarDatosDeEnvio con(String email, String nombre, String apellido, String cedula, String tipoDoc, String numDoc, String telefono) {
        return instrumented(CompletarDatosDeEnvio.class, email, nombre, apellido, cedula, tipoDoc, numDoc, telefono);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(FIRST_NAME, isVisible()).forNoMoreThan(8).seconds(),

                Enter.theValue(email).into(EMAIL),
                Enter.theValue(nombre).into(FIRST_NAME),
                Enter.theValue(apellido).into(LAST_NAME),
                Enter.theValue(cedula).into(BILLING_ID),

                // Seleccionamos por value (en este caso "cc")
                SelectFromOptions.byValue(tipoDoc).from(DOCUMENT_TYPE),

                Enter.theValue(numDoc).into(DOCUMENT_NUMBER),
                Enter.theValue(telefono).into(PHONE)
        );
    }
}

