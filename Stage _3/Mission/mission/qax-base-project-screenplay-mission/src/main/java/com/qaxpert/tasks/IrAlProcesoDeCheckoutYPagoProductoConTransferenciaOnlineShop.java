package com.qaxpert.tasks;

import static com.qaxpert.ui.OnlineShopOrderPage.SELECT_PAY_BY_BANK_WIRE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IrAlProcesoDeCheckoutYPagoProductoConTransferenciaOnlineShop extends IrAlProcesoDeCheckoutYPagoProductoOnlineShop {

    public IrAlProcesoDeCheckoutYPagoProductoConTransferenciaOnlineShop() {
        super(SELECT_PAY_BY_BANK_WIRE);
    }

    public static IrAlProcesoDeCheckoutYPagoProductoConTransferenciaOnlineShop ahora() {
        return instrumented(IrAlProcesoDeCheckoutYPagoProductoConTransferenciaOnlineShop.class);
    }
}
