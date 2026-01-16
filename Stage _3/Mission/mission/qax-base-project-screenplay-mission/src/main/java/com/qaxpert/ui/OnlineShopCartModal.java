package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopCartModal {

    public static final Target MODAL = Target.the("modal de carrito")
            .locatedBy("//div[@id='blockcart-modal']//div[@class='modal-content']");

    public static final Target MODAL_CART_TOTAL = Target.the("total del carrito en el modal")
            .locatedBy("//div[@id='blockcart-modal']//div[@class='modal-content']//div[@class='cart-content']/p[2]/span[@class='value' and normalize-space()]");
}

