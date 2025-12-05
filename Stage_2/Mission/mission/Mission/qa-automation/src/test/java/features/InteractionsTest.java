package features;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.InteractionsPage;

public class InteractionsTest extends BaseTest {

    private InteractionsPage interactionsPage;
    private String baseURL;

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        interactionsPage = new InteractionsPage(driver);
    }

    @Test(priority = 1)
    public void

}
