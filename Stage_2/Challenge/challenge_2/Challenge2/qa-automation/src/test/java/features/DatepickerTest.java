package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.DatepickerPage;

import java.time.LocalDate;

public class DatepickerTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private DatepickerPage datepickerPage;
    private static final String Datepicker_Url = "Datepicker.html";


    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + Datepicker_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        datepickerPage = new DatepickerPage(driver);
    }

    @Test(priority = 1)
    public void testDatepicker() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificamos que los inputs están visibles
        Assert.assertTrue(datepickerPage.getDisabledInput().isDisplayed(),
                "El input Datepicker Disabled no está visible "
        );
        Assert.assertTrue(datepickerPage.getEnabledInput().isDisplayed(),
                "El input Datepicker Enabled no está visible "
        );

        // ------------------------------
        //  LOGICA PRIMER DATEPICKER (Datepicker Disabled)
        // ------------------------------
        datepickerPage.openDisabledDatepicker();
        datepickerPage.select9Dec2025InDisabledDatepicker();

        // Comprobamos el valor y verificamos que el formato es el esperado
        String datepickerDisabledValue = datepickerPage.getDisabledInputValue();
        Assert.assertFalse(
                datepickerDisabledValue.isEmpty(),
                "El primer input está vacío tras seleccionar una fecha"
        );
        String datepickerDisabledPattern = "MM/dd/yyyy";
        Assert.assertTrue(
                datepickerPage.isDateInExpectedFormat(datepickerDisabledValue, datepickerDisabledPattern),
                "El formato de la fecha del primer input no es el esperado: " + datepickerDisabledValue
        );
        // Verificamos que la fecha es exactamente la que hemos seleccionado (12/09/2025)
        LocalDate parseDate1 = datepickerPage.parseDate(datepickerDisabledValue, datepickerDisabledPattern);
        Assert.assertEquals(LocalDate.of(2025, 12, 9), parseDate1);

        // ------------------------------
        //  LOGICA SEGUNDO DATEPICKER (Datepicker Enabled)
        // ------------------------------
        datepickerPage.openEnabledDatepicker();
        datepickerPage.select30Dec2025InEnabledDatepicker();

        String datepickerEnabledValue = datepickerPage.getEnabledInputValue();
        Assert.assertFalse(
                datepickerEnabledValue.isEmpty(),
                "El segundo input está vacío tras seleccionar una fecha"
        );
        String datepickerEnabledPattern = "MM/dd/yyyy";
        Assert.assertTrue(
                datepickerPage.isDateInExpectedFormat(datepickerEnabledValue, datepickerEnabledPattern),
                "El formato de la fecha del segundo input no es el esperado: " + datepickerEnabledValue
        );
        // Verificamos que la fecha es exactamente la que hemos seleccionado (12/30/2025)
        LocalDate parseDate2 = datepickerPage.parseDate(datepickerEnabledValue, datepickerEnabledPattern);
        Assert.assertEquals(LocalDate.of(2025, 12, 30), parseDate2);
    }
}
