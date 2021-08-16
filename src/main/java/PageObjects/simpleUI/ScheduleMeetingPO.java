package PageObjects.simpleUI;

import Controllers.CreateDriver;
import Controllers.Global_Vars;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class ScheduleMeetingPO<M extends WebElement> extends BasePO<M>{

    public ScheduleMeetingPO() throws Exception {
        super();
    }


    int min = 0000;
    int max = 9999;
    int random_int = (int) Math.floor(Math.random()*(max-min+1)+min);
    LocalDate date = LocalDate.now();
    static final int participant=0;

    String meeting_guid = "QA_Meeting_" + date + "_" + random_int ;
    String meeting_reason = "QA_Reason_"  + date + "_" + random_int;
    String meeting_provider = "38820534-7615-41a0-8150-5d604808111a";
    String meeting_client = "Esteban Islas";

    @Override
    public void verifyTitle() {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        Assert.assertEquals(driver.getTitle(), Global_Vars.PAGE_TITLE, "Verify Page Title");
    }

    @Override
    protected void implicitWait() {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Override
    protected void explicitWait_Visibility(M Element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Global_Vars.TIMEOUT_ELEMENT);
        wait.until(ExpectedConditions.visibilityOf(Element));
    }

    @Override
    protected void explicitWait_exists(M Element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Global_Vars.TIMEOUT_ELEMENT);
    }

    @Override
    protected void explicitWait_textPresentInElement(M Element, String client){
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Global_Vars.TIMEOUT_ELEMENT);
        wait.until(ExpectedConditions.textToBePresentInElement(Element, client));
    }

    @FindBy(id = "open-schedule-dialog-btn")
    protected M schedule_btn;

    @FindBy(id= "invitations")
    protected M meeting_lst;

    //Schedule meeting modal elements
    @FindBy(id = "schedule-meeting-business")
    protected M schedule_business_txt;

    @FindBy(id = "schedule-meeting-guid")
    protected M schedule_meeting_guid_txt;

    @FindBy(id = "schedule-meeting-reason")
    protected M schedule_meeting_reason_txt;

    @FindBy(id ="schedule-meeting-provider")
    protected M schedule_meeting_provider_txt;

    @FindBy(id = "schedule-meeting-customers")
    protected M schedule_meeting_customer_txt;

    @FindBy(id="schedule-meeting-search-btn")
    protected M schedule_search_btn;

    @FindBy(id = "schedule-meeting-close-btn")
    protected M schedule_close_btn;

    @FindBy(id ="schedule-meeting-schedule-btn")
    protected M schedule_schedule_btn;

    //Search modal elements
    @FindBy(css="#search-customer-dlg > div > div > div")
    protected M search_modal;

    @FindBy(id="search-customer-name")
    protected M search_customer_name_txt;

    @FindBy(id="search-customer-search")
    protected M search_magnifying_glass_btn;

    @FindBy(id="search-customer-select")
    protected M search_customer_added_txt;

    @FindBy(id="search-customer-close-btn")
    protected M search_close_btn;

    @FindBy(id="add-customer-schedule-btn")
    protected M search_add_btn;


    public void fill_info_for_meeting() throws InterruptedException {
        clickElement(schedule_btn);
        explicitWait_Visibility(schedule_meeting_guid_txt);
        sendKeysToElement(schedule_meeting_guid_txt, meeting_guid);
        sendKeysToElement(schedule_meeting_reason_txt, meeting_reason);
        clearTextElement(schedule_meeting_provider_txt);
        sendKeysToElement(schedule_meeting_provider_txt,meeting_provider);
        System.out.println("Meeting guid created:" + meeting_guid);
        clickElement(schedule_search_btn);
        explicitWait_Visibility(search_modal);
        sendKeysToElement(search_customer_name_txt, meeting_client);
        clickElement(search_magnifying_glass_btn);
        explicitWait_textPresentInElement(search_customer_added_txt, meeting_client);
        clickElement(search_add_btn);
        explicitWait_Visibility(schedule_meeting_guid_txt);

    }

    public void schedule_meeting() throws InterruptedException{
        fill_info_for_meeting();
        clickElement(schedule_schedule_btn);
        explicitWait_Visibility(meeting_lst);
    }

    public void cancel_schedule_meeting() throws InterruptedException{
        fill_info_for_meeting();
        clickElement(schedule_close_btn);
    }

    }
