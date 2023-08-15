package Base;

import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import static io.appium.java_client.touch.offset.PointOption.point;

public class ElementAction extends BaseUtil {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public Properties config() throws IOException {
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        return configFile;
    }

    public ElementAction(AppiumDriver driver) throws IOException {
        Long time = Long.valueOf(config().getProperty("timeWait"));
        wait = new WebDriverWait(driver, time);
    }

    public void waitAndClick(MobileElement by) {
        wait.until(ExpectedConditions.visibilityOf(by)).click();
    }

    public void click(AppiumDriver<MobileElement> driver, MobileElement by) {
        by.click();
    }

    public void isUnClickable(MobileElement by) {
        String isElement = by.getAttribute("clickable");
        if (isElement == "true") {
            throw new ElementClickInterceptedException("Error element is clickable");
        }
    }

    public void isUnAccessible(MobileElement by) {
        String isElement = by.getAttribute("accessible");
        if (isElement == "true") {
            throw new ElementClickInterceptedException("Error element is clickable");
        }
    }

    public void isClickable(MobileElement by) {
        String isElement = by.getAttribute("clickable");
        if (isElement == "false") {
            throw new ElementClickInterceptedException("Error, element is not clickable");
        }
    }

    public void hideKeyboardIOS(AppiumDriver<MobileElement> driver) {
        driver.findElementByName("Return").click();
    }

    public void scrollDownIOS(AppiumDriver<MobileElement> driver, String name) {
        JavascriptExecutor js = driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("name", name);
        js.executeScript("mobile: scroll", params);
    }

    public boolean isElementPresent(MobileElement element) {
        boolean isElementDisplayed;
        try {
            isElementDisplayed = element.isDisplayed();
        } catch (NoSuchElementException e) {
            isElementDisplayed = false;
        }
        return isElementDisplayed;
    }

    public boolean isElementEnable(MobileElement element) {
        boolean isElementEnabled;
        try {
            isElementEnabled = element.isEnabled();
        } catch (NoSuchElementException e) {
            isElementEnabled = false;
        }
        return isElementEnabled;
    }

    public WebElement waitAndFindElement(MobileElement by) {
        return wait.until(ExpectedConditions.visibilityOf(by));
    }

    public String getText(MobileElement element) {
        return waitAndFindElement(element).getText();
    }

    public void sendText(MobileElement by, String text) {
        waitAndFindElement(by).sendKeys(text);
    }

    public void clickEnter(MobileElement by) {
        waitAndFindElement(by).sendKeys(Keys.ENTER);
    }

    public void clearText(MobileElement element) {
        waitAndFindElement(element).clear();
    }

    public void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Two texts are not equal!" + "Actual: " + actual + " Expected: " + expected);
    }

    public void assertTrue(Boolean value) {
        Assert.assertTrue(value);
    }

    public void assertFalse(Boolean value) {
        Assert.assertFalse(value);
    }

    public void waitVisibility(WebElement mobileElement) {
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickByCoordinate(AppiumDriver<MobileElement> driver, Integer x, Integer y) {
        new TouchAction<>(driver).tap(point(x, y)).release().perform();
    }

    public String getAttributeElem(MobileElement by, String att) {
        return waitAndFindElement(by).getAttribute(att);
    }

    public WebElement scrollDownToText(AppiumDriver<MobileElement> driver, String text) {
        return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollToEnd(10).scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
    }

    public WebElement scrollUpToText(AppiumDriver<MobileElement> driver, String text) {
        return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()" +
                ".scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + text + "\"));"));
    }

    public void scrollDownTillFind(AppiumDriver<MobileElement> driver, MobileElement by, Integer height) {
        boolean check = true;
        int check2 = 0;
        while (check) {
            try {
                waitVisibility(by);
                check = false;
            } catch (Exception e) {
                check = true;
                scrollByPercentage(driver,50,height,50,5);
                check2++;
                System.out.println(check2);
                if (check2 > 4) break;
            }
        }
    }

    public void scrollUpTillFind(AppiumDriver<MobileElement> driver, MobileElement by, Integer height) {
        boolean check = true;
        int check2 = 0;
        while (check) {
            try {
                waitVisibility(by);
                check = false;
            } catch (Exception e) {
                check = true;
                scrollByPercentage(driver,50,100-height,50,100);
                check2++;
                System.out.println(check2);
                if (check2 > 4) break;
            }
        }

    }

    public void scrollByPercentage(AppiumDriver<MobileElement> driver, Integer fromx, Integer fromy, Integer tox, Integer toy) {
        Dimension windowSize = driver.manage().window().getSize();
        int fromX = windowSize.width * fromx / 100;
        int fromY = windowSize.height * fromy / 100;
        int toX = windowSize.width * tox / 100;
        int toY = windowSize.height * toy / 100;
        new TouchAction<>(driver).longPress(point(fromX, fromY)).moveTo(point(toX, toY)).release().perform();
    }

    public Integer coordinateWidhtByPercentage(AppiumDriver<MobileElement> driver, Integer x) {
        Dimension windowSize = driver.manage().window().getSize();
        return windowSize.width * x / 100;
    }

    public Integer coordinateHeightByPercentage(AppiumDriver<MobileElement> driver, Integer x) {
        Dimension windowSize = driver.manage().window().getSize();
        return windowSize.height * x / 100;
    }

    public String fakeEmail() {
        Faker faker = new Faker();
        String email = faker.name().firstName() + faker.number().numberBetween(100, 9999) + "@pintutest.co.id";
        return email;
    }

    public boolean isElementNotPresent(MobileElement element) {
        boolean isElementDisplayed;
        try {
            isElementDisplayed = element.isDisplayed();
        } catch (NoSuchElementException e) {
            isElementDisplayed = true;
        }
        return isElementDisplayed;
    }

    public String fakeName() {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        Integer randomNumber = faker.number().numberBetween(100, 999);
        String fix_name = name+randomNumber.toString();
        return fix_name;
    }

    public String fakeNumberBank() {
        Faker faker = new Faker();
        Integer bank = faker.number().numberBetween(100, 999);
        String bank_number = bank.toString();
        return bank_number;
    }

    public void dragDrop(AppiumDriver<MobileElement> driver, MobileElement element1, MobileElement element2) {
        TouchAction dragNDrop = new TouchAction(driver)
                .longPress(ElementOption.element(element1))
                .moveTo(ElementOption.element(element2))
                .release();
        dragNDrop.perform();
    }


    public String fakeEmail2() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString() + "@pintutest.co.id";
        return saltStr;
    }

    public void isTextPresent(AppiumDriver<MobileElement> driver, String _text) throws IOException {
        String txtToBeSearch = "//*[contains(@text,'" + _text + "')]";
        if (!isOSAndroid()) {
            txtToBeSearch = "//*[contains(@label,'" + _text + "')]";
        }
        MobileElement txt = driver.findElement(MobileBy.xpath(txtToBeSearch));
    }

    public void isValuePresent(AppiumDriver<MobileElement> driver, String _text) throws IOException {
        String txtToBeSearch = "//*[contains(@text,'" + _text + "')]";
        if (!isOSAndroid()) {
            txtToBeSearch = "//*[contains(@value,'" + _text + "')]";
        }
        driver.findElement(MobileBy.xpath(txtToBeSearch));
    }

    public void isTextNotPresent(AppiumDriver<MobileElement> driver, String _text) throws IOException {
        String txtToBeSearch = "//*[contains(@text,'" + _text + "')]";
        if (!isOSAndroid()) {
            txtToBeSearch = "//*[contains(@label,'" + _text + "')]";
        }
        boolean isPresent = false;
        try {
            MobileElement txt = driver.findElement(MobileBy.xpath(txtToBeSearch));
            isPresent = true;
            throw new NoSuchElementException("Error element " + txtToBeSearch + " expected to be not exist but actual is exist");
        } catch (Exception e) {
            if (isPresent) {
                throw new NoSuchElementException("Error element " + txtToBeSearch + " expected to be not exist but actual is exist");
            }
        }
    }

    public void isValueNotPresent(AppiumDriver<MobileElement> driver, String _text) throws IOException {
        String txtToBeSearch = "//*[contains(@text,'" + _text + "')]";
        if (!isOSAndroid()) {
            txtToBeSearch = "//*[contains(@value,'" + _text + "')]";
        }
        boolean isPresent = false;
        try {
            MobileElement txt = driver.findElement(MobileBy.xpath(txtToBeSearch));
            isPresent = true;
            throw new NoSuchElementException("Error element " + txtToBeSearch + " expected to be not exist but actual is exist");
        } catch (Exception e) {
            if (isPresent) {
                throw new NoSuchElementException("Error element " + txtToBeSearch + " expected to be not exist but actual is exist");
            }
        }
    }

    public boolean cekEnvStag() throws IOException {
        boolean cek;
        if (System.getProperty("build") != null) {
            cek = System.getProperty("build").contains("Staging");
        } else {
            cek = config().getProperty("typeProfilling").contains("Stag");
        }
        return cek;
    }

    public boolean isOSAndroid() throws IOException {
        boolean isAndroid;
        if (System.getProperty("os") != null) {
            isAndroid = System.getProperty("os").equalsIgnoreCase("ANDROID");
        }
        else {
            isAndroid = config().getProperty("typeOS").equalsIgnoreCase("ANDROID");
        }
        return isAndroid;
    }

    public boolean isSelected(MobileElement element) {
        boolean isElementSelected;
        try {
            isElementSelected = element.isSelected();
        } catch (NoSuchElementException e) {
            isElementSelected = false;
        }
        return isElementSelected;
    }
}

