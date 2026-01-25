package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopOrderPage {

    public static final Target BTN_CONTINUE_ADDRESSES = Target.the("botón Continue de Adresses")
            .locatedBy("//button[@type='submit' and @name='confirm-addresses']");

    public static final Target BTN_CONTINUE_SHIPPING_METHOD = Target.the("botón Continue de Shipping Method")
            .locatedBy("//button[@type='submit' and @name='confirmDeliveryOption']");

    public static final Target SELECT_PAY_BY_CHECK = Target.the("selección opción Pay by Check")
            .locatedBy("//div[@id='payment-option-1-container']//input[@id='payment-option-1']");

    public static final Target SELECT_PAY_BY_BANK_WIRE = Target.the("selección opción Pay by Bank Wire")
            .locatedBy("//div[@id='payment-option-2-container']//input[@id='payment-option-2']");

    public static final Target SELECT_SERVICE_AGREEMENT = Target.the("selección opción service agreement")
            .locatedBy("//form[@id='conditions-to-approve']//input[@id='conditions_to_approve[terms-and-conditions]']");

    public static final Target BTN_ORDER_WITH_AN_OBLIGATION_TO_PAY = Target.the("botón Order with an obligation to pay")
            .locatedBy("//div[@id='payment-confirmation']//button[@type='submit' and contains(@class, 'btn') and normalize-space()]");

}
