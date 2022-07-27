package br.com.mrms.mytasks.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        tags = "@status",
        // dryRun = true,
        stepNotifications = true,
        plugin = {
                "pretty",
                "html:target/reports/cucumber-reports-html",
                "rerun:target/rerun.txt"},
        strict = false,
        monochrome = false,
        features = "src/test/resources/features",
        glue =	{
                "br.com.mrms.mytasks.steps",
                "br.com.mrms.mytasks.config"
        }
)
public class StatusRunner {

    @BeforeClass
    public static void begin(){

    }
}
