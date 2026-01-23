package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.qaxpert.ui.OnlineShopAddressesPage.LINK_ADD_FIRST_ADDRESS;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavegarADireccionesOnlineShop implements Task {

    public static NavegarADireccionesOnlineShop porLink() {
        return instrumented(NavegarADireccionesOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LINK_ADD_FIRST_ADDRESS)
        );
    }
}
