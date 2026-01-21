package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AgregarProductoExistenteAlCarritoOnlineShop implements Task {

    public static AgregarProductoExistenteAlCarritoOnlineShop ahora() {
        return instrumented(AgregarProductoExistenteAlCarritoOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                BuscarProductoOnlineShop.porTermino("hummingbird"),
                AbrirDetalleProductoExistenteOnlineShop.desdeElListado(),
                AgregarAlCarritoDesdeDetalleOnlineShop.ahora()
        );
    }
}