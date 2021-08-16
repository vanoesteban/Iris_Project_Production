package PageObjects.simpleUI;

import Controllers.CreateDriver;
import Controllers.Global_Vars;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePO <M extends WebElement> {
    public BasePO() throws Exception{
        PageFactory.initElements(CreateDriver.getInstance().getDriver(),this);
    }

    // abstract methods

    public abstract void verifyTitle();
    protected abstract void implicitWait();
    protected abstract void explicitWait_Visibility(M Element);
    protected abstract void explicitWait_exists(M Element);
    protected abstract void explicitWait_textPresentInElement(M Element);


    protected void click_link_text(String link) {

    }

    protected String get_text(M Element) {
        return Element.getText();

    }

    protected

    protected void sendKeysToElement(M Element, String data) {
        Element.sendKeys(data);
    }

    protected void clickElement(M Element) {
        Element.click();
    }

    protected void clearTextElement(M Element){
        Element.clear();
    }

    //actions class
    protected void rightClickElement(M Element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        Actions action = new Actions(driver);
        action.contextClick(Element).perform();
    }

    protected void moveToAnElement(M Element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        Actions action = new Actions(driver);
        action.moveToElement(Element).perform();
    }

    //javascript
    protected void click_element_js(M Element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", Element);
    }

    /**
     * loadPage method to navigate to Target URL
     *
     * @throws Exception
     */
    public void loadPage() throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, Global_Vars.TIMEOUT_WAIT);

        driver.navigate().to(Global_Vars.TARGET_URL);

        // wait for page URL
        exists.until( ExpectedConditions.refreshed(ExpectedConditions.urlContains(Global_Vars.TARGET_URL)) );

    }
}
