package bo.com.test.prueba.tecnica.Runners;


import bo.com.test.prueba.tecnica.utils.excel.BeforeSuite;
import bo.com.test.prueba.tecnica.utils.excel.DataToFeature;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(RunnerPersonalizado.class)
@CucumberOptions(
        features="src/test/resources/features/",
        tags= "@apiRest",
        glue= "bo.com.test.prueba.tecnica.stepdefinitions",
        plugin = {"pretty","json:target/cucumber-reports/cucumber.json"},
        snippets=CucumberOptions.SnippetType.CAMELCASE,
        publish = true
)

public class RunnerTags {
    @BeforeSuite
    public static void test() throws InvalidFormatException, IOException {
        DataToFeature.overrideFeatureFiles("src/test/resources/features");
    }
}