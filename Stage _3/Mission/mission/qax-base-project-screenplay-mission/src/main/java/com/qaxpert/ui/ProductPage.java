package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ProductPage {

    public static final Target OPTION_DROPDOWN = Target.the("dropdown")
            .locatedBy("//div[contains(@class,'vi-wpvs-variation-button-select') and not(contains(@class,'disabled'))][1]");

    public static final Target FIRST_VARIATION_OPTION = Target.the("primera variación disponible")
            .locatedBy("//div[contains(@class, 'vi-wpvs-variation-wrap-option') and not(contains(@class, 'vi-wpvs-hidden'))]//div[contains(@class, 'vi-wpvs-option-wrap')][2]/span[contains(@class, 'vi-wpvs-option-select')]");

    public static final Target BTN_BUY_NOW = Target.the("botón Comprar ahora")
            .locatedBy("//button[@name='wc-quick-buy-now' and @type='submit']");

    public static final Target BTN_ADD_TO_CART = Target.the("botón Añadir al carrito")
            .locatedBy("//button[contains(@class,'single_add_to_cart_button') and contains(normalize-space(.),'Añadir al carrito')]");
}
