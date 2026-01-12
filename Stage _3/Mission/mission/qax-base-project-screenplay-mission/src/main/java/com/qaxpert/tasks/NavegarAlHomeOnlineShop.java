package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopHomePage.HOME_CONTAINER;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavegarAlHomeOnlineShop implements Task {

    public static NavegarAlHomeOnlineShop porUrl() {
        return instrumented(NavegarAlHomeOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("http://www.testingyes.com/onlineshop/"),
                WaitUntil.the(HOME_CONTAINER, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}