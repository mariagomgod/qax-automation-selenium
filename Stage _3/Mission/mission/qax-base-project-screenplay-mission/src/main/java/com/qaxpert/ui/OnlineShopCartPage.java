package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopCartPage {

    public static final Target CART_MAIN = Target.the("contenedor principal del carrito")
            .locatedBy("//section[@id='main' and .//div[contains(@class,'cart-grid')]]");

    public static final Target CART_ITEMS = Target.the("items del carrito")
            .locatedBy("//div[@id='cart-subtotal-products']/span[contains(@class,'js-subtotal') and normalize-space()]");

    public static final Target FIRST_ITEM_NAME = Target.the("nombre del primer producto en el carrito")
            .locatedBy("//div[contains(@class, 'product-line-grid-body')]//a[@class='label' and normalize-space()]");

    public static final Target FIRST_ITEM_UNIT_PRICE = Target.the("precio unitario del primer producto en el carrito")
            .locatedBy("//div[@class='current-price']/span[@class='price' and normalize-space()]");

    public static final Target CART_TOTAL = Target.the("total del carrito")
            .locatedBy("//div[@id='cart-subtotal-products']/span[@class='value' and normalize-space()]");

    public static final Target FIRST_ITEM_REMOVE = Target.the("eliminar primer producto del carrito")
            .locatedBy("//a[@class='remove-from-cart'][1]");

    public static final Target EMPTY_CART_MESSAGE = Target.the("mensaje carrito vacío")
            .locatedBy("//div[contains(@class, 'cart-overview')]/span[@class='no-items' and normalize-space()]");
}

