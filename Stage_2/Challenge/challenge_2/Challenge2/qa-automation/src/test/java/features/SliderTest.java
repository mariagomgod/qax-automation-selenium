package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.SliderPage;

public class SliderTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private SliderPage sliderPage;
    private static final String Slider_Url = "Slider.html";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + Slider_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        sliderPage = new SliderPage(driver);
    }

    @Test(priority = 1)
    public void testSlider() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que el slider está visible
        Assert.assertTrue(
                sliderPage.getSliderContainer().isDisplayed(),
                "El contenedor del slider debería estar visible"
        );

        // Guardar el valor inicial asociado al slider
        String initialSliderCssValue = sliderPage.getSliderPositionStyle();

        // Verificar que el slider handler está visible y arrastrar hasta una posición específica
        Assert.assertTrue(
                sliderPage.getSliderHandler().isDisplayed(),
                "El slider handler debería estar visible"
        );
        sliderPage.moveSliderHorizontally(60);

        // Verificar que el valor visible o atributo asociado al slider cambia con respecto al valor inicial
        String firstCssValue = sliderPage.getSliderPositionStyle();
        Assert.assertNotEquals(
                firstCssValue,
                initialSliderCssValue,
                "La posición CSS del slider debería haber cambiado respecto al valor inicial"
        );
    }
}
