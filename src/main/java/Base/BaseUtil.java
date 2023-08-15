package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseUtil {

    public static URL url;
    public static DesiredCapabilities capabilities;
    public static AppiumDriver<MobileElement> driver;
    public static WebDriver webDriver;
    public static String RUNNER = "";

    public void setupTest(String device) throws IOException, InterruptedException {
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        setupAppium(configFile.getProperty("typeRun"));
    }

    public void setupAppium(String urlServer) throws IOException, InterruptedException {
        Properties configFile = new Properties();
        configFile.load(BaseUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        String build_name = System.getenv("BROWSERSTACK_BUILD_NAME");
        String typeOS = configFile.getProperty("typeOS");
        String typeRun = configFile.getProperty("typeRun");
        String check = System.getProperty("os");
        String build_name_LT = System.getProperty("build");
        String deviceName;
        String osVersion;

        deviceName = "EMULATOR";
        osVersion = null;
        RUNNER = typeOS;


        System.out.println("DEVICE TYPE :"+RUNNER);
        System.out.println("BUILD NAME :"+build_name_LT);
        System.out.println("TYPE RUN :"+urlServer);
        System.out.println("DEVICE NAME :"+deviceName);
        System.out.println("OS VERSION :"+osVersion);

        capabilities = new DesiredCapabilities();
        try {
            url = new URL(configFile.getProperty(urlServer));
            switch (urlServer) {
                case "localURL":
                    if (Objects.equals(typeOS, "ANDROID")){
                        capabilities.setCapability(MobileCapabilityType.UDID, configFile.getProperty("udid"));
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configFile.getProperty("androidPlatformName"));
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, configFile.getProperty("androidPlatformVersion"));
                        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, configFile.getProperty("androidAppPackage"));
                        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, configFile.getProperty("androidAppActivity"));
                        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
                    }else {
                        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, "true");
                        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configFile.getProperty("devicenameIOS"));
                        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configFile.getProperty("platformnameIOS"));
                        capabilities.setCapability(MobileCapabilityType.UDID, configFile.getProperty("udidIOS"));
                        capabilities.setCapability(MobileCapabilityType.APP, configFile.getProperty("bundleidIOS"));
                        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, configFile.getProperty("automationnameIOS"));
                        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                        capabilities.setCapability("appium:settings[snapshotMaxDepth]", "62");
                    }

                    driver = new AppiumDriver<MobileElement>(url, capabilities);
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Long.parseLong(configFile.getProperty("timeWait")), TimeUnit.SECONDS);
        driver.hideKeyboard();

        configFile.setProperty("Device Name", "udid");
        configFile.setProperty("Android Version", "androidPlatformVersion");
        File file = new File("target\\allure-results");
        FileOutputStream fileOut = new FileOutputStream(file);
        configFile.store(fileOut, "Device Info");
        fileOut.close();
    }
}