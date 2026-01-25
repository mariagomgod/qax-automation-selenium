package stepdefinitions;

public class CarritoContext {
    public static String precioDetalle;
    public static String metodoPago;

    public static void reset() {
        precioDetalle = null;
        metodoPago = null;
    }
}

