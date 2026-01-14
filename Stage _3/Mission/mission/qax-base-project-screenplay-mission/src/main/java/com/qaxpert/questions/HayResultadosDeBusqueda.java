package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopSearchResultsPage.PRODUCT_ITEMS;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class HayResultadosDeBusqueda implements Question<Boolean> {

    public static HayResultadosDeBusqueda visibles() {
        return new HayResultadosDeBusqueda();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            actor.attemptsTo(
                    WaitUntil.the(PRODUCT_ITEMS, isVisible()).forNoMoreThan(10).seconds()
            );
        } catch (Exception ignored) {}

        return WebElementQuestion.the(PRODUCT_ITEMS)
                .answeredBy(actor)
                .isCurrentlyVisible();
    }
}
