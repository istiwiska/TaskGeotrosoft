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

public class ProductScreenPage extends BaseUtil {

    ElementAction action = new ElementAction(driver);

    @AndroidFindBy(xpath = "//*[@text='PRODUCTS']")
    public MobileElement titleProduct;

    @AndroidFindBy(xpath = "//*[@text='Sauce Labs Bike Light']")
    public MobileElement titleProductList;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    public MobileElement buttonCart;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[2]/android.widget.TextView")
    public MobileElement buttonAddToCart;


    public ProductScreenPage(AppiumDriver<MobileElement> driver) throws IOException {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public void verifyTitleProduct() {
        action.waitVisibility(titleProduct);
    }

    public void verifyTitleProductList() {
        action.waitVisibility(titleProductList);
    }

    public void clickCartButton(){
        action.waitAndClick(buttonCart);
    }

    public void clickButtonAddCart(){
        action.waitAndClick(buttonAddToCart);
    }

}
