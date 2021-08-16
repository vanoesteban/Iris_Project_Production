package Controllers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class CreateDriver implements iDriver {

    //local variables
    private static CreateDriver instance = null;
    private static final int IMPLICIT_TIMEOUT = 0;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private Properties props = new Properties();

    //constructor
    private CreateDriver() {
    }


    public static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    /**
     * @param browser
     * @param platform
     * @param environment
     * @param optPreferences
     * @throws IOException
     */
    @SafeVarargs
    @Override
    public final void setDriver(String browser, String platform,
                                String environment,
                                Map<String, Object>... optPreferences)
            throws IOException {

        DesiredCapabilities caps = null;
        props.load(new FileInputStream(Global_Vars.SE_PROPS));

        switch (browser) {
            case "chrome":

                ChromeOptions chOptions = new ChromeOptions();
                Map<String, Object> chromePrefs =
                        new HashMap<String, Object>();
                chromePrefs.put("credentials_enable_service",
                        false);
                chOptions.setExperimentalOption("prefs",
                        chromePrefs);
                chOptions.addArguments("--disable-plugins",
                        "--disable-extensions",
                        "--disable-popup-blocking",
                        "ignore-certificate-errors");
                chOptions.setCapability(ChromeOptions.CAPABILITY,
                        chOptions);
                chOptions.setCapability("applicationCacheEnabled",
                        false);
                if (environment.equalsIgnoreCase("Production")) {
                    System.setProperty("webdriver.chrome.driver",
                            props.getProperty("chrome.driver.windows.path"));
                    webDriver.set(new
                            ChromeDriver(chOptions.merge(chOptions)));
                }
                break;

            case "firefox":

                FirefoxOptions ffOpts = new FirefoxOptions();
                FirefoxProfile ffProfile = new FirefoxProfile();

                ffProfile.setPreference("browser.autofocus", true);
                ffProfile.setPreference("browser.tabs.remote.autostart.2", false);

                ffOpts.setCapability(FirefoxDriver.PROFILE, ffProfile);
                ffOpts.setCapability("marionette", true);

                // then pass them to the local WebDriver
                if (environment.equalsIgnoreCase("Production")) {
                    System.setProperty("webdriver.gecko.driver", props.getProperty("gecko.driver.windows.path"));
                    webDriver.set(new FirefoxDriver(ffOpts.merge(ffOpts)));
                }

                break;

            case "edge":

                EdgeOptions edgeOpts = new EdgeOptions();

                if ( environment.equalsIgnoreCase("Production") ) {
                    System.setProperty("webdriver.edge.driver", props.getProperty("edge.driver.windows.path"));
                    webDriver.set(new EdgeDriver(edgeOpts.merge(edgeOpts)));
                }

                break;
        }

        getDriver().manage().window().maximize();
    }


    @Override
    public WebDriver getDriver() {
        return webDriver.get();
    }


    @Override
    public void closeDriver() {
        try {
            getDriver().quit();
        } catch (Exception e) {
            System.out.println("cannot quit Driver");
        }
    }

}
