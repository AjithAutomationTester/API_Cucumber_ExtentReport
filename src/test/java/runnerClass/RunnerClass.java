
package runnerClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"F:\\Optisol\\SBN.API.ExtentReport\\src\\test\\resources\\featurefiles\\home.feature"},
		glue = {"stepDefinition"},
		plugin = {"json:target/cucumber2.json", "de.monochromata.cucumber.report.PrettyReports:target/cucumber"})

public class RunnerClass {
	
		@AfterClass
		public static void teardown() {


			File reportOutputDirectory = new File("target/cucumber-report");
			List<String> jsonFiles = new ArrayList<String>();
			jsonFiles.add("target/cucumber2.json");



			Configuration configuration = new Configuration(reportOutputDirectory, "API SBN");

			configuration.addClassifications("Platform", "Windows");
			configuration.addClassifications("App-Name", "SBN");
			configuration.addClassifications("Tested By", "Ajith Kumar");



			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();
		}
}
