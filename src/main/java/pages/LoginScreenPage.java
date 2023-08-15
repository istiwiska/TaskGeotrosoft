package pages;

import Base.BaseUtil;
import Base.ElementAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginScreenPage extends BaseUtil {

    ElementAction action = new ElementAction(driver);

    @AndroidFindBy(accessibility = "test-Username")
    public MobileElement email_field;

    @AndroidFindBy(accessibility = "test-Password")
    public MobileElement password_field;

    @AndroidFindBy(accessibility = "test-LOGIN")
    public MobileElement loginButton;


    public LoginScreenPage(AppiumDriver<MobileElement> driver) throws IOException {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public void enterEmail() throws IOException {
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        String env = configFile.getProperty("typeProfilling");

        String email = configFile.getProperty("email"+env+"Default");
        action.sendText(email_field,email);
    }

    public void enterPassword() throws IOException {
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        String env = configFile.getProperty("typeProfilling");

        String password = configFile.getProperty("pwd"+env+"Default");
        action.sendText(password_field,password);
    }

    public void clickLoginButton(){
        action.waitAndClick(loginButton);
    }

    public void verifyLoginButton(){
        action.waitVisibility(loginButton);
    }

}
