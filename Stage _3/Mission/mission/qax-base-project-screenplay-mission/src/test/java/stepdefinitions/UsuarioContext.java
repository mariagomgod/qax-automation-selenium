package stepdefinitions;

public class UsuarioContext {
    public static String usuarioCreado;
    public static String emailCreado;
    public static String passwordCreado;

    public static void reset() {
        usuarioCreado = null;
        emailCreado = null;
        passwordCreado = null;
    }
}
