package com.qaxpert.tasks;

import static com.qaxpert.ui.OnlineShopOrderPage.SELECT_PAY_BY_CHECK;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IrAlProcesoDeCheckoutYPagoProductoConChequeOnlineShop extends IrAlProcesoDeCheckoutYPagoProductoOnlineShop {

    public IrAlProcesoDeCheckoutYPagoProductoConChequeOnlineShop() {
        super(SELECT_PAY_BY_CHECK);
    }

    public static IrAlProcesoDeCheckoutYPagoProductoConChequeOnlineShop ahora() {
        return instrumented(IrAlProcesoDeCheckoutYPagoProductoConChequeOnlineShop.class);
    }
}
