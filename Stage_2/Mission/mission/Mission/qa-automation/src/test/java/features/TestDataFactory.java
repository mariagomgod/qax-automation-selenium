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

    public static String randomUsername() {
        return faker.name().username();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    // ------------------------------
    //  Contacto
    // ------------------------------
    public static String randomPhoneNumber() {
        return faker.phoneNumber().cellPhone();  // o .phoneNumber()
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

    public static String randomCountry() {
        return faker.address().country();
    }

    public static String randomPostalCode() {
        return faker.address().zipCode();
    }

    // ------------------------------
    //  Internet/ Login
    // ------------------------------
    public static String randomSafeEmail() {
        return faker.internet().safeEmailAddress();
    }

    public static String randomPassword() {
        // minLength=8, maxLength=16, includeUppercase, includeSpecial
        return faker.internet().password(8, 16, true, true);
    }

    public static String randomUrl() {
        return faker.internet().url();
    }

    // ------------------------------
    //  Datos de negocio
    // ------------------------------
    public static String randomCompanyName() {
        return faker.company().name();
    }

    public static String randomJobTitle() {
        return faker.job().title();
    }

    // ------------------------------
    //  Texto genérico
    // ------------------------------
    public static String randomSentence() {
        return faker.lorem().sentence();
    }

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
