package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopProductDetailPage {

    public static final Target PRODUCT_TITLE = Target.the("título del producto en detalle")
            .locatedBy("//h1[contains(@class,'h1') and normalize-space()]");

    public static final Target SIZE_SELECT = Target.the("selector de talla")
            .locatedBy("//select[@id='group_1' and @name='group[1]']");

    public static final Target SIZE_SELECTED_OPTION = Target.the("talla seleccionada")
            .locatedBy("//select[@id='group_1']/option[@selected='selected']");

    public static final Target COLOR_RADIOS = Target.the("radios de color")
            .locatedBy("//div[@class='clearfix product-variants-item']//ul[@id='group_2']");

    public static final Target SECOND_COLOR_RADIO = Target.the("segundo radio de color")
            .locatedBy("//ul[@id='group_2']/li[2]//input[@type='radio' and @name='group[2]' and contains(@class,'input-color')]");

    public static final Target QTY_INPUT = Target.the("cantidad")
            .locatedBy("//input[@id='quantity_wanted' and @name='qty' and @type='number']");

    public static final Target UNIT_PRICE = Target.the("precio unitario")
            .locatedBy("//span[@itemprop='price' and normalize-space()]");

    public static final Target BTN_ADD_TO_CART = Target.the("botón añadir al carrito")
            .locatedBy("//button[@data-button-action='add-to-cart' and not(@disabled)]");

    public static final Target MODAL_BTN_PROCEED_TO_CHECKOUT = Target.the("botón Proceed to checkout de la modal")
            .locatedBy("//div[@class='cart-content-btn']/a[contains(@class, 'btn')]");
}