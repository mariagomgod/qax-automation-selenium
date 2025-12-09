package features;

import com.github.javafaker.Faker;

public class TestDataFactory {

    private static final Faker faker = new Faker();

    // ------------------------------
    //  Identidad del usuario
    // ------------------------------
    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    // ------------------------------
    //  Dirección
    // ------------------------------
    public static String randomStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String randomCity() {
        return faker.address().city();
    }

    // ------------------------------
    //  Internet/ Login
    // ------------------------------
    public static String randomSafeEmail() {
        return faker.internet().safeEmailAddress();
    }

    // ------------------------------
    //  Datos de negocio
    // ------------------------------
    public static String randomJobTitle() {
        return faker.job().title();
    }

    // ------------------------------
    //  Texto genérico
    // ------------------------------
    public static String randomParagraph() {
        return faker.lorem().paragraph();
    }

    // ------------------------------
    //  Números/ ids simples
    // ------------------------------
    public static int randomInt(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String randomNumericString(int length) {
        return faker.number().digits(length); // ej: "483920"
    }
}
