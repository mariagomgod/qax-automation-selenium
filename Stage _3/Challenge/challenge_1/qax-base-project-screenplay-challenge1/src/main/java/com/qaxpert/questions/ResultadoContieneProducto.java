package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class ResultadoContieneProducto implements Question<Boolean> {

    private final String texto;

    public ResultadoContieneProducto(String texto) {
        this.texto = texto;
    }

    public static ResultadoContieneProducto conNombre(String texto) {
        return new ResultadoContieneProducto(texto);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String fuente = BrowseTheWeb.as(actor).getDriver().getPageSource();
        return fuente != null && fuente.toLowerCase().contains(texto.toLowerCase());
    }
}

