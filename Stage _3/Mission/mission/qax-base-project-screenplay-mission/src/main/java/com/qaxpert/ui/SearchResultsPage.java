package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class SearchResultsPage {

    public static final Target RESULTS = Target.the("lista de resultados")
            .locatedBy("//div[@data-elementor-type='jet_products_archive']//div[contains(@data-url,'https://amantesapescar.co/producto/')]");

    public static final Target FIRST_PRODUCT = Target.the("primer producto del listado")
            .locatedBy("//div[@data-elementor-type='jet_products_archive']//div[contains(@data-url,'https://amantesapescar.co/producto/')][1]");
}
