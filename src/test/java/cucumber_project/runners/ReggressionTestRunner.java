package cucumber_project.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/cucumber_project/features",
        glue = "cucumber_project.steps",
        stepNotifications = true,
        tags = "@Regression",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/Cucumber.json"
        })
public class ReggressionTestRunner {

    @AfterClass
    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/Cucumber.json");

        String projectName = "Cucumber Test Automation Demo";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Platform", "Windows");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}