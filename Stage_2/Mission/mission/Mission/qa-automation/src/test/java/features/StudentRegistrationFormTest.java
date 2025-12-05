package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.StudentRegistrationFormPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudentRegistrationFormTest extends BaseTest {

    private StudentRegistrationFormPage studentRegistrationFormPage;
    private static final String Form_Url = "automation-practice-form";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        driver.get(baseURL + Form_Url);
        studentRegistrationFormPage = new StudentRegistrationFormPage(driver);
    }

    @Test(priority = 1)
    public void fill_in_name_and_contact_fields_with_valid_information() {
        // Generamos los datos del formulario de TestDataFactory
        String firstName = TestDataFactory.randomFirstName();
        String lastName  = TestDataFactory.randomLastName();
        String email     = TestDataFactory.randomSafeEmail();
        String mobile    = TestDataFactory.randomNumericString(10); // 10 dígitos

        // Rellenar los campos del formulario First Name, Last Name, Email, Gender y Mobile con datos válidos
        studentRegistrationFormPage.fillBasicContactInfo(firstName, lastName, email, mobile);

        // Verificar que los campos muestran los valores ingresados
        Assert.assertEquals(studentRegistrationFormPage.getFirstNameValue(), firstName);
        Assert.assertEquals(studentRegistrationFormPage.getLastNameValue(), lastName);
        Assert.assertEquals(studentRegistrationFormPage.getEmailValue(), email);
        Assert.assertEquals(studentRegistrationFormPage.getMobileValue(), mobile);

        // NO ASSERT del campo Gender: Toda la explicación está documentada en: casos_de_prueba.md
    }

    @Test(priority = 2)
    public void select_birth_date_using_datepicker() {
        String expectedDay   = "15";
        String expectedMonth = "March";
        String expectedYear  = "1990";

        // Seleccionar la fecha
        studentRegistrationFormPage.selectDateOfBirth(expectedDay, expectedMonth, expectedYear);

        String actualDateValue = studentRegistrationFormPage.getDateOfBirthValue();

        // Verificar que el campo Date of Birth muestra la fecha seleccionada en el formato esperado
        Assert.assertTrue(
                actualDateValue.contains("15") && actualDateValue.contains("Mar") && actualDateValue.contains("1990"),
                "La fecha mostrada no es la esperada. Valor actual: " + actualDateValue
        );
    }

    @Test(priority = 3)
    public void select_multiple_subjects_from_autocomplete() {
        String subject1 = "Computer Science";
        String subject2 = "Commerce";

        // Escribir "Com" y seleccionar "Computer Science" y "Commerce"
        studentRegistrationFormPage.selectSubjectFromAutocomplete(subject1);
        studentRegistrationFormPage.selectSubjectFromAutocomplete(subject2);

        // Verificar que ambos subjects permanecen seleccionados en el campo Subjects
        List<String> selectedSubjects = studentRegistrationFormPage.getSelectedSubjects();

        Assert.assertTrue(
                selectedSubjects.contains(subject1),
                "El subject " + subject1 + " no está seleccionado"
        );

        Assert.assertTrue(
                selectedSubjects.contains(subject2),
                "El subject " + subject2 + " no está seleccionado"
        );
    }

    @Test(priority = 4)
    public void select_two_hobbies_and_upload_picture() {
        // Seleccionar los hobbies Sports y Music
        studentRegistrationFormPage.selectSportsHobby();
        studentRegistrationFormPage.selectMusicHobby();

        // Subir una imagen de prueba
        String projectRoot = System.getProperty("user.dir");
        Path picturePath = Paths.get(projectRoot, "src", "test", "resources", "images", "test-picture.png");
        String absolutePath = picturePath.toString();

        studentRegistrationFormPage.uploadPicture(absolutePath);

        // Verificar que los hobbies están seleccionados
        Assert.assertTrue(studentRegistrationFormPage.isSportsHobbySelected(),
                "El hobby Sports no está seleccionado");
        Assert.assertTrue(studentRegistrationFormPage.isMusicHobbySelected(),
                "El hobby Music no está seleccionado");

        // Verificar que el campo Picture tiene un valor (nombre del archivo)
        String pictureValue = studentRegistrationFormPage.getUploadedPictureValue();
        Assert.assertTrue(
                pictureValue != null && pictureValue.contains("test-picture"),
                "La imagen no parece haberse subido correctamente. Valor actual: " + pictureValue
        );
    }

    @Test(priority = 5)
    public void fill_current_address_field() {
        // Generamos los datos del formulario de TestDataFactory
        String street = TestDataFactory.randomStreetAddress();
        String city   = TestDataFactory.randomCity();
        String fullAddress = street + ", " + city;

        // Introducir una dirección en Current Address
        studentRegistrationFormPage.setCurrentAddress(fullAddress);

        // Verificar que el campo muestra exactamente lo que se ha escrito
        Assert.assertEquals(
                studentRegistrationFormPage.getCurrentAddressValue(),
                fullAddress,
                "Current Address no coincide con el valor esperado"
        );
    }

    @Test(priority = 6)
    public void select_state_and_city_dropdowns() {
        String state = "NCR";
        String city  = "Delhi";

        // Seleccionar State
        studentRegistrationFormPage.selectState(state);

        // Seleccionar City
        studentRegistrationFormPage.selectCity(city);

        // Verificar que los valores seleccionados son los esperados
        Assert.assertEquals(studentRegistrationFormPage.getSelectedState(),
                state,
                "El State seleccionado no es correcto"
        );

        Assert.assertEquals(studentRegistrationFormPage.getSelectedCity(),
                city, "La City seleccionada no es correcta"
        );
    }

    @Test(priority = 7)
    public void submit_form_and_calidate_confirmation_table() {
        // Generar datos del TestDataFactory
        String firstName = TestDataFactory.randomFirstName();
        String lastName  = TestDataFactory.randomLastName();
        String email     = TestDataFactory.randomSafeEmail();
        String mobile    = TestDataFactory.randomNumericString(10);

        String street = TestDataFactory.randomStreetAddress();
        String cityAddress = TestDataFactory.randomCity();
        String address = street + ", " + cityAddress;

        // Generar datos fijos para facilitar los asserts
        String gender = "Female";

        String day   = "15";
        String month = "March";
        String year  = "1990";

        String subject1 = "Computer Science";
        String subject2 = "Commerce";

        String state = "NCR";
        String city  = "Delhi";

        String pictureFileName = "test-picture.png";

        // Rellenar formulario
        studentRegistrationFormPage.fillBasicContactInfo(firstName, lastName, email, mobile);
        studentRegistrationFormPage.selectFemaleGender();
        studentRegistrationFormPage.selectDateOfBirth(day, month, year);
        studentRegistrationFormPage.selectSubjectFromAutocomplete(subject1);
        studentRegistrationFormPage.selectSubjectFromAutocomplete(subject2);
        studentRegistrationFormPage.selectSportsHobby();

        String projectRoot = System.getProperty("user.dir");
        Path picturePath = Paths.get(projectRoot, "src", "test", "resources", "images", pictureFileName);
        studentRegistrationFormPage.uploadPicture(picturePath.toString());

        studentRegistrationFormPage.setCurrentAddress(address);

        studentRegistrationFormPage.selectState(state);
        studentRegistrationFormPage.selectCity(city);

        studentRegistrationFormPage.submitForm();

        // Validaciones en el modal
        Assert.assertTrue(
                studentRegistrationFormPage.isConfirmationModalVisible(),
                "La tabla de confirmación no se mostró tras hacer Submit"
        );

        // Student Name
        Assert.assertEquals(
                studentRegistrationFormPage.getStudentNameFromModal(),
                firstName + " " + lastName
        );

        // Gender
        Assert.assertEquals(
                studentRegistrationFormPage.getGenderFromModal(),
                gender
        );

        // Date of Birth (ej: "15 March,1990")
        String dateOfBirthValue = studentRegistrationFormPage.getDateOfBirthFromModal();
        Assert.assertTrue(
                dateOfBirthValue.contains(day) &&
                        dateOfBirthValue.contains(month) &&
                        dateOfBirthValue.contains(year),
                "La fecha de nacimiento en la tabla no es la esperada: " + dateOfBirthValue
        );

        // Subjects
        String subjectsValue = studentRegistrationFormPage.getSubjectsFromModal();
        Assert.assertTrue(subjectsValue.contains(subject1));
        Assert.assertTrue(subjectsValue.contains(subject2));

        // Address
        Assert.assertEquals(
                studentRegistrationFormPage.getAddressFromModal(),
                address
        );

        // State and City
        String stateCityValue = studentRegistrationFormPage.getStateAndCityFromModal();
        Assert.assertTrue(
                stateCityValue.contains(state) && stateCityValue.contains(city),
                "El State and City en la tabla no es el esperado: " + stateCityValue
        );
    }
}
