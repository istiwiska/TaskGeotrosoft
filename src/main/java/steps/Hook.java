package steps;

import Base.BaseUtil;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.SkipException;
import testrail.gurock.APIException;
import testrail.gurock.TestRailManager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Properties;


public class Hook extends BaseUtil {

    public BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void setUp() throws IOException, InterruptedException {
        System.out.println("HOOK BEFORE TEST");
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        setupTest(configFile.getProperty("typeTest"));
    }

    @After
    public void closeAppSwitch(Scenario scenario) throws IOException, APIException {
        driver.closeApp();
        driver.quit();
        System.out.println("HOOK AFTER");
    }



}