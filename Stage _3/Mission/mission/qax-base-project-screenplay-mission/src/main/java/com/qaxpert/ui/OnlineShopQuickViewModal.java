package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopQuickViewModal {

    public static final Target MODAL = Target.the("modal de quick view")
            .locatedBy("//div[contains(@class,'modal-content')][.//form[@id='add-to-cart-or-refresh']]");

    public static final Target PRODUCT_NAME = Target.the("nombre del producto en quick view")
            .locatedBy("//div[contains(@class,'modal-content')][.//form[@id='add-to-cart-or-refresh']]//h1[contains(@class,'h1')]");

    public static final Target PRODUCT_PRICE = Target.the("precio del producto en quick view")
            .locatedBy("//div[contains(@class,'modal-content')][.//form[@id='add-to-cart-or-refresh']]//span[@itemprop='price' and normalize-space()]");

    public static final Target BTN_ADD_TO_CART = Target.the("botón Add to cart en quick view")
            .locatedBy("//div[contains(@class,'modal-content')][.//form[@id='add-to-cart-or-refresh']]//button[@data-button-action='add-to-cart' or contains(@class,'add-to-cart')]");
}

