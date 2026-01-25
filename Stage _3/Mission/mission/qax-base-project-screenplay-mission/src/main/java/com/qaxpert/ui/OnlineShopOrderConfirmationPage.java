package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopOrderConfirmationPage {
    public static final Target CONFIRMATION_ORDER_MESSAGE = Target.the("mensaje confirmación del pedido")
            .locatedBy("//section[@id='content-hook_order_confirmation']//h3[contains(@class, 'card-title') and normalize-space()]");

    public static final Target FINAL_ORDER_RESUME = Target.the("resumen final del pedido")
            .locatedBy("//section[@id='content-hook_order_confirmation']//h3[contains(@class, 'card-title') and normalize-space()]");

    public static final Target FINAL_ORDER_ITEMS = Target.the("resumen final del/de los producto(s)")
            .locatedBy("//div[@id='order-items']//div[@class='order-confirmation-table']//div[contains(@class, 'details') and normalize-space()]");

    public static final Target PRICE_ORDER = Target.the("precio del pedido")
            .locatedBy("//div[@class='order-confirmation-table']/table/tbody/tr[1]/td[2]");

    public static final Target PAYMENT_METHOD = Target.the("método de pago")
            .locatedBy("//div[@id='order-details']/ul/li[2]");
}
