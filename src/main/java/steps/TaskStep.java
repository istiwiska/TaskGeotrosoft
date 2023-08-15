package steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import testrail.gurock.APIException;

import java.io.IOException;


public class TaskStep extends BaseUtil {
    LoginScreenPage loginScreenPage;
    ProductScreenPage productScreenPage;
    DetailCartScreenPage detailCartScreenPage;
    CheckoutScreenPage checkoutScreenPage;

    public TaskStep() throws IOException {

        this.loginScreenPage = new LoginScreenPage(driver);
        this.productScreenPage = new ProductScreenPage(driver);
        this.detailCartScreenPage = new DetailCartScreenPage(driver);
        this.checkoutScreenPage = new CheckoutScreenPage(driver);

    }

    @Given("I see the login form")
    public void I_See_The_Login_form() throws IOException {
        loginScreenPage.verifyLoginButton();
    }

    @And("I enter email in login email profiling")
    public void I_enter_email_in_login_email_profiling() throws IOException {
        loginScreenPage.enterEmail();
    }

    @And("I enter password in login email profiling")
    public void I_enter_password_in_login_email_profiling() throws IOException {
        loginScreenPage.enterPassword();
    }

    @And("I click log in button")
    public void I_click_log_in_button() throws IOException {
        loginScreenPage.clickLoginButton();
    }

    @Then("I should see Products page with title")
    public void I_should_see_products_page_with_title() throws IOException {
        productScreenPage.verifyTitleProduct();
    }

    @When("^the product is listed with title \"([^\"]*)\" and price \"([^\"]*)\"$")
    public void theProductIsListedWithTitleAndPrice(String title, String price) throws IOException {
        productScreenPage.verifyTitleProductList();
    }

    @And("I click add to cart button")
    public void I_click_add_to_cart_button() throws IOException {
        productScreenPage.clickButtonAddCart();
    }

    @And("I click cart button")
    public void I_click_cart_button() throws IOException {
        productScreenPage.clickCartButton();
    }

    @Then("I should see product list")
    public void I_should_see_detail_cart_with_page() throws IOException {
        detailCartScreenPage.verifyTitleProductList();
    }

    @And("I click Checkout Button")
    public void I_click_checkout_button() throws IOException {
        detailCartScreenPage.clickCheckoutButton();
    }

    @Then("I should see Checkout page with title")
    public void I_should_see_checkout_page_with_title() throws IOException {
        checkoutScreenPage.verifyTitleCheckout();
    }

    @And("I enter first name")
    public void I_enter_first_name() throws IOException {
        checkoutScreenPage.enterFirstName();
    }

    @And("I enter last name")
    public void I_enter_last_name() throws IOException {
        checkoutScreenPage.enterLastName();
    }

    @And("I enter zip code")
    public void I_enter_zip_code() throws IOException {
        checkoutScreenPage.enterZipCode();
    }

    @And("I click continue button")
    public void I_click_continue_button() throws IOException {
        checkoutScreenPage.clickContinueButton();
    }

    @And("I click finish button")
    public void I_click_finish_button() throws IOException {
        checkoutScreenPage.clickFinishButton();
    }

    @Then("I should see Checkout Complete")
    public void I_should_see_checkout_complete() throws IOException {
        checkoutScreenPage.visibilitySuccessCheckout();
    }


}
