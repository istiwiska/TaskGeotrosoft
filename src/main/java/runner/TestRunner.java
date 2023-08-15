package runner;

import Base.BaseUtil;
import com.github.javafaker.Faker;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.runner.RunWith;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Properties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/"},
        glue = {"steps"},
        publish = true,
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/report.html",
                "json:target/report.json",
                "junit:target/report.xml"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public static void teardown(ITestContext a) throws IOException, ParseException {
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
    }
}
