package Base;

import org.openqa.selenium.remote.CapabilityType;

public interface androidCapabilityType extends CapabilityType {
    String APP_PACKAGE = "appPackage";
    String APP_PACKAGE_VALUE = "appPackageValue";
    String APP_ACTIVITY = "appActivity";
    String APP_ACTIVITY_VALUE = "appActivityValue";
    String DEVICE_NAME = "deviceName";
    String DEVICE_PLATFORM = "devicePlatform";
    String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
    String NO_RESET = "noReset";
    String NO_RESET_VALUE = "noResetValue";
    String PLATFORM_VERSION = "platformVersion";
    String SERVER = "server";
    String PORT = "port";
    String ENVIRONMENT_TYPE = "environmentType";
    String UDID = "udid";
    String AUTO_GRANT_PERMISSION = "autoGrantPermissions";
}
