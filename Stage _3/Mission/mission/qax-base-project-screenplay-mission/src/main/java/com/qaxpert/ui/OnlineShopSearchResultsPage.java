package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopSearchResultsPage {

    public static final Target PRODUCT_ITEMS = Target.the("items de producto")
            .locatedBy("//div[contains(@class,'products')]/article[contains(@class,'product-miniature') and contains(@class,'js-product-miniature')]");


    public static final Target NO_RESULTS_MESSAGE = Target.the("mensaje de no resultados")
            .locatedBy("//section[@id='content' and contains(@class,'page-not-found')]//h4[normalize-space()='Sorry for the inconvenience.']");
}
