package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    // ====== CONTENEDOR PRINCIPAL CHECKOUT ======
    public static final Target CHECKOUT_FORM = Target.the("formulario de checkout")
            .locatedBy("//form[@name='checkout' and contains(@class,'woocommerce-checkout')]");

    // ====== BILLING (campos obligatorios) ======
    public static final Target EMAIL = Target.the("email")
            .locatedBy("//input[@id='billing_email' and @name='billing_email']");
    public static final Target FIRST_NAME = Target.the("nombre")
            .locatedBy("//input[@id='billing_first_name' and @name='billing_first_name']");

    public static final Target LAST_NAME = Target.the("apellido")
            .locatedBy("//input[@id='billing_last_name' and @name='billing_last_name']");

    public static final Target BILLING_ID = Target.the("cédula")
            .locatedBy("//input[@id='billing_id' and @name='billing_id']");

    public static final Target DOCUMENT_TYPE = Target.the("tipo identificación")
            .locatedBy("//select[@id='billing_document_type' and @name='billing_document_type']");

    public static final Target DOCUMENT_NUMBER = Target.the("número identificación")
            .locatedBy("//input[@id='billing_document_number' and @name='billing_document_number']");

    public static final Target PHONE = Target.the("teléfono")
            .locatedBy("//input[@id='billing_phone' and @name='billing_phone']");

    // Campos Opcionales
    public static final Target ADDRESS = Target.the("dirección")
            .locatedBy("//input[@id='billing_address_1' and @name='billing_address_1']");

    public static final Target CITY = Target.the("ciudad")
            .locatedBy("//input[@id='billing_city' and @name='billing_city']");

    // ====== ENVÍO (ya viene seleccionado por defecto) ======
    public static final Target SHIPPING_FIRST_OPTION = Target.the("primera opción de envío (checked por defecto)")
            .locatedBy("(//ul[@id='shipping_method']//input[contains(@name,'shipping_method')])[1]");

    // ====== PAGO (Wompi por defecto) ======
    public static final Target RADIO_WOMPI = Target.the("método de pago Wompi")
            .locatedBy("//input[@id='payment_method_wompi_wwp' and @name='payment_method' and @value='wompi_wwp']");

    public static final Target BTN_PLACE_ORDER = Target.the("botón pagar")
            .locatedBy("//button[@id='place_order' and @name='woocommerce_checkout_place_order' and @type='submit']");

    // ====== RESUMEN / ORDER REVIEW ======
    public static final Target ORDER_PRODUCT_PRICE = Target.the("precio del producto en resumen")
            .locatedBy("//div[@id='order_review']//tr[contains(@class,'cart_item')]//td[contains(@class,'product-total')]//span[contains(@class,'amount')]//bdi");

    public static final Target ORDER_REVIEW = Target.the("resumen de compra (order_review)")
            .locatedBy("//div[@id='order_review']");

    public static final Target ORDER_PRODUCT_NAME = Target.the("nombre del producto en resumen")
            .locatedBy("//div[@id='order_review']//div[contains(@class,'wcf-product-name')]");

    public static final Target ORDER_TOTAL = Target.the("total a pagar")
            .locatedBy("//div[@id='order_review']//tr[contains(@class,'order-total')]//span[contains(@class,'amount')]//bdi");

    // ====== ERRORES  ======
    public static final Target CHECKOUT_ERROR = Target.the("mensaje de error checkout")
            .locatedBy("//div[contains(@class,'woocommerce-notices-wrapper')]//*[self::li or self::div or self::p][normalize-space()]");
}
