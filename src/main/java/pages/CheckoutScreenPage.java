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

public class CheckoutScreenPage extends BaseUtil {

    ElementAction action = new ElementAction(driver);

    @AndroidFindBy(xpath = "//*[@text='CHECKOUT: INFORMATION']")
    public MobileElement titleCheckout;

    @AndroidFindBy(accessibility = "test-First Name")
    public MobileElement firstname_field;

    @AndroidFindBy(accessibility = "test-Last Name")
    public MobileElement lasttname_field;

    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    public MobileElement zip_field;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]/android.widget.TextView")
    public MobileElement buttonContinue;

    @AndroidFindBy(xpath = "//*[@text='FINISH']")
    public MobileElement buttonFinish;

    @AndroidFindBy(xpath = "//*[@text='THANK YOU FOR YOU ORDER']")
    public MobileElement textThankYou;


    public CheckoutScreenPage(AppiumDriver<MobileElement> driver) throws IOException {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public void verifyTitleCheckout() {
        action.waitVisibility(titleCheckout);
    }

    public void enterFirstName() {
        action.sendText(firstname_field,"test first");
    }

    public void enterLastName(){
        action.sendText(lasttname_field,"test last");
    }

    public void enterZipCode(){
        action.sendText(zip_field,"1122");
    }

    public void clickContinueButton(){
        action.waitVisibility(buttonContinue);
        action.waitAndClick(buttonContinue);
    }

    public void clickFinishButton(){
        action.scrollDownToText(driver,"FINISH");
        action.waitVisibility(buttonFinish);
        action.waitAndClick(buttonFinish);
        action.sleep(1);
    }

    public void visibilitySuccessCheckout(){
        action.waitVisibility(textThankYou);
    }

}
