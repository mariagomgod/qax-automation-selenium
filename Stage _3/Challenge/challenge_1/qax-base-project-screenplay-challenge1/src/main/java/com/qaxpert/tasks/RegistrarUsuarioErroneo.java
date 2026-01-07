package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.qaxpert.ui.RegisterPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegistrarUsuarioErroneo implements Task {

    // Variables que guardan los datos del usuario
    private final String username;
    private final String email;
    private final String password;

    // Constructor
    // Aquí recibimos los datos que vamos a escribir en el formulario
    public RegistrarUsuarioErroneo(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Este método permite crear el Task de forma legible desde el Step Definition
    public static RegistrarUsuarioErroneo conContraseñaDiferentes(String username, String email, String password) {
        return instrumented(RegistrarUsuarioErroneo.class,username, email, password);
    }

    // Aquí definimos las acciones que hace el usuario en la pantalla de registro
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Enter.theValue(username).into(TXT_USERNAME),
                Enter.theValue(email).into(TXT_EMAIL),
                Enter.theValue(password).into(TXT_PASSWORD),
                Enter.theValue(password+"test").into(TXT_CONFIRM_PASSWORD),
                Click.on(BTN_REGISTER)

        );
    }
}