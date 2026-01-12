package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import org.openqa.selenium.Keys;

import static com.qaxpert.ui.SearchPage.TXT_SEARCH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class BuscarProducto implements Task {

    private final String texto;

    public BuscarProducto(String texto) {
        this.texto = texto;
    }

    public static BuscarProducto porTexto(String texto) {
        return instrumented(BuscarProducto.class, texto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(texto).into(TXT_SEARCH),
                Hit.the(Keys.ENTER).into(TXT_SEARCH)
        );
    }
}

