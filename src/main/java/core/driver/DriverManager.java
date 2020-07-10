
package core.driver;

import core.azure.model.attachment.Attachment;
import com.github.javafaker.Faker;
import core.config.Configuration;
import cucumber.api.Scenario;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Collection;
import java.util.Locale;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
    public static final Configuration configuration = ConfigCache.getOrCreate(Configuration.class);
    public static Faker faker = new Faker(new Locale(configuration.faker()));
    public static Collection<Attachment> attachments;

    public static WebDriver getDriver() {
        return  driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static ThreadLocal<Scenario> getScenario() {
        return scenario;

    }

    public static void setScenario(Scenario scenario) {
        DriverManager.scenario.set(scenario);
    }

    public static void quit(Scenario scenario) {
        if (scenario.isFailed()) {
            attachments.add(new Attachment("png","Evidencia do erro apresentado na execução"));
        }
        DriverManager.driver.get().quit();
    }

    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatform().toString();
        String version = cap.getVersion();
        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }
}
