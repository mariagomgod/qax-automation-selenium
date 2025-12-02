package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatepickerPage extends BasePage {

    public DatepickerPage(WebDriver driver) { super(driver);}

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By datePickerDisabledInput = By.id("datepicker1");
    private By datePickerEnabledInput = By.id("datepicker2");
    private By calendar_1_next_btn = By.cssSelector("a.ui-datepicker-next.ui-corner-all");
    private By day9Dec2025 = By.xpath("//div[contains(@class,'ui-datepicker')]//td[@data-handler='selectDay' and @data-month='11' and @data-year='2025']/a[normalize-space()='9']");
    private By calendar_2_next_btn = By.xpath("//a[contains(@class,'datepick-cmd-next') and @title='Show the next month']");
    private By day30Dec2025 = By.xpath("//a[@title='Select Tuesday, Dec 30, 2025' and normalize-space()='30']");

    // ==============================
    //  Acciones
    // ==============================

    public WebElement getDisabledInput() {
        return waitForVisibility(datePickerDisabledInput);
    }

    public WebElement getEnabledInput() {
        return waitForVisibility(datePickerEnabledInput);
    }

    public void openDisabledDatepicker() {
        driver.findElement(datePickerDisabledInput).click();
    }

    public void openEnabledDatepicker() {
        driver.findElement(datePickerEnabledInput).click();
    }

    // Método reutilizable que: pulsa "next" hasta que el día concreto sea clicable y hace click en ese día
     private void selectDateByClickingNext(By nextBtn, By dayLocator) {
        for (int i = 0; i < 6; i++) {
            if (!driver.findElements(dayLocator).isEmpty()) {
                WebElement dayElement = waitForClickable(dayLocator);
                jsClickWebElement(dayElement);
                return;
            }
            WebElement next = waitForClickable(nextBtn);
            next.click();
        }
         throw new RuntimeException("No se encontró la fecha objetivo tras navegar 6 meses");
     }

    // Calendario 1: navega con el botón "next" hasta que aparezca el día 12/09/2025 y lo selecciona.
    public void select9Dec2025InDisabledDatepicker() {
        // Esperamos a que el input esté visible
        WebElement enabledInput = waitForVisibility(datePickerDisabledInput);
        enabledInput.click();
        selectDateByClickingNext(calendar_1_next_btn, day9Dec2025);
    }

    // Calendario 2: navega con el botón "next" hasta que aparezca el día 12/30/2025 y lo selecciona.
    public void select30Dec2025InEnabledDatepicker() {
        // Esperamos a que el input esté visible
        WebElement enabledInput = waitForVisibility(datePickerEnabledInput);
        enabledInput.click();
        selectDateByClickingNext(calendar_2_next_btn, day30Dec2025);
    }

    public String getDisabledInputValue() {
        return driver.findElement(datePickerDisabledInput).getAttribute("value");
    }

    public String getEnabledInputValue() {
        return driver.findElement(datePickerEnabledInput).getAttribute("value");
    }

    // Método para validar formato fecha
    public boolean isDateInExpectedFormat(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            formatter.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método para verificar que el valor del input refleja correctamente la fecha seleccionada
    public LocalDate parseDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, formatter);
    }
}
