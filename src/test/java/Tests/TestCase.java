package Tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/MyTest.feature", glue={"Tests"},
monochrome = true,
plugin={"pretty","junit:target/JunitReports/report.xml",
		"json:target/Jsonreport/report.json",
		"html:target/HtmlReport/HtmlForm.html"}
		)
public class TestCase {

}
