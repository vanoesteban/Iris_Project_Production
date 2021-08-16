package Controllers;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

public interface iDriver {

    public static CreateDriver getInstance() {
        return null;
    }

    public void setDriver(String browser, String platform, String environment, Map<String, Object>...optPreferences) throws IOException;

    public WebDriver getDriver();

    public void closeDriver();

}
