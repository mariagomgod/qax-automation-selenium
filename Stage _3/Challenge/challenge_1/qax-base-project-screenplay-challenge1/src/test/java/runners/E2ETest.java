package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner encargado de ejecutar los escenarios smoke
 * relacionados con el registro de usuarios
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "pretty"
        }
)
public class E2ETest {
}