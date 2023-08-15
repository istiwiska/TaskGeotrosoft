package pages;

import Base.BaseUtil;
import Base.ElementAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.time.Duration;

public class DetailCartScreenPage extends BaseUtil {

    ElementAction action = new ElementAction(driver);

    @AndroidFindBy(xpath = "//*[@text='Sauce Labs Bike Light']")
    public MobileElement titleProductList;

    @AndroidFindBy(xpath = "//*[@text='CHECKOUT']")
    public MobileElement buttonCheckout;


    public DetailCartScreenPage(AppiumDriver<MobileElement> driver) throws IOException {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public void verifyTitleProductList() {
        action.waitVisibility(titleProductList);
    }

    public void clickCheckoutButton(){
        action.waitAndClick(buttonCheckout);
    }

}
