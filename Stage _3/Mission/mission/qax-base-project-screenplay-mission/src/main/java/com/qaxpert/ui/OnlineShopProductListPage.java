package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopProductListPage {

    public static final Target PRODUCT_ITEMS = Target.the("items de producto")
            .locatedBy("//div[contains(@class,'products')]/article[contains(@class,'product-miniature') and contains(@class,'js-product-miniature')]");

    // Primer botón/enlace Quick view dentro del primer item
    public static final Target FIRST_QUICK_VIEW = Target.the("quick view del primer producto")
            .locatedBy("(//a[@data-link-action='quickview' and contains(@class,'quick-view')])[1]");

}
