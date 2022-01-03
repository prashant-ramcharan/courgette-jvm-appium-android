package steps;

import courgette.api.CourgetteMobileDeviceAllocator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Objects;

import static java.lang.String.format;

public class TestSteps {
    private AndroidDriver<WebElement> driver;
    private AppiumDriverLocalService service;
    private Scenario currentScenario;

    @Before
    public void before(Scenario scenario) {
        if (System.getenv("ANDROID_HOME") == null) {
            throw new RuntimeException("ANDROID_HOME environment variable is missing!");
        }
        currentScenario = scenario;
        service = createAppiumDriverLocalService();
        driver = createAndriodDriver(service.getUrl());
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("I launch the app")
    public void iLaunchTheApp() {
        driver.launchApp();
    }

    @When("I open {} menu")
    public void iOpenAppMenu(String menu) {
        driver.findElementByAndroidUIAutomator(format("new UiSelector().textContains(\"%s\")", menu)).click();
    }

    @Then("I verify {} is shown")
    public void iVerifyLabelIsShown(String label) {
        Assert.assertTrue(driver.findElement(By.xpath(format("//*[@text='%s']", label))).isDisplayed());
    }

    private AndroidDriver<WebElement> createAndriodDriver(final URL serverURL) {
        File app = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("apps/ApiDemos-debug.apk")).getFile());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("avd", CourgetteMobileDeviceAllocator.DEVICE_NAME);
        capabilities.setCapability("udid", CourgetteMobileDeviceAllocator.UDID);
        capabilities.setCapability("systemPort", CourgetteMobileDeviceAllocator.PARALLEL_PORT);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("autoLaunch", false);

        currentScenario.log(format("Android Device: %s", capabilities.getCapability("avd")));

        return new AndroidDriver<>(serverURL, capabilities);
    }

    private AppiumDriverLocalService createAppiumDriverLocalService() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = serviceBuilder.build();
        service.start();
        return service;
    }
}