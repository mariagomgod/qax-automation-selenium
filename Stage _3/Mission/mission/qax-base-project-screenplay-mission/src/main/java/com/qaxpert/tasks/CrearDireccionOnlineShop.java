package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopAddressFormPage.*;
import static com.qaxpert.ui.OnlineShopAddressesPage.LINK_ADD_FIRST_ADDRESS;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CrearDireccionOnlineShop implements Task {

    private final String alias;
    private final String direccion;
    private final String ciudad;
    private final String postal;
    private final String telefono;
    private final String paisVisibleText;
    private final String stateVisibleText;

    public CrearDireccionOnlineShop(String alias, String direccion, String ciudad, String postal,
                                    String telefono, String paisVisibleText, String stateVisibleText) {
        this.alias = alias;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.postal = postal;
        this.telefono = telefono;
        this.paisVisibleText = paisVisibleText;
        this.stateVisibleText = stateVisibleText;
    }

    public static CrearDireccionOnlineShop conDatos(String alias, String direccion, String ciudad, String postal,
                                                    String telefono, String paisVisibleText, String stateVisibleText) {
        return instrumented(CrearDireccionOnlineShop.class, alias, direccion, ciudad, postal, telefono, paisVisibleText, stateVisibleText);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ALIAS, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(alias).into(ALIAS),
                Enter.theValue(direccion).into(ADDRESS1),
                Enter.theValue(ciudad).into(CITY),
                Enter.theValue(postal).into(POSTCODE),
                Enter.theValue(telefono).into(PHONE),

                SelectFromOptions.byVisibleText(paisVisibleText).from(COUNTRY),

                WaitUntil.the(STATE, isVisible()).forNoMoreThan(10).seconds(),
                SelectFromOptions.byVisibleText(stateVisibleText).from(STATE),

                WaitUntil.the(BTN_SAVE, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(BTN_SAVE)
        );
    }
}

