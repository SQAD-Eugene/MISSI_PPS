package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Data_validation {
    WebDriver driver;

    @FindBy(linkText = "DATA VALIDATION")
    public static WebElement lbl_Data_validation;

    @FindBy(linkText = "DATA ENTRY")
    public static WebElement lbl_Data_entry;

    @FindBy(linkText = "MISSI")
    public static WebElement lbl_Missi;

    @FindBy(linkText = "PPS")
    public static WebElement lbl_pps;

    @FindBy(xpath ="//label[contains(.,'MISSI Validation')]")
    public static WebElement modal_missi;

    @FindBy(xpath ="//label[contains(.,'Select Year:')]")
    public static WebElement lbl_Year;

    @FindBy(xpath ="//select[contains(.,'Select year')]")
    public static WebElement dd_Year;

    @FindBy(xpath ="//select[contains(.,'Select month')]")
    public static WebElement dd_Month;

    @FindBy(linkText = "Go To Questionnaire")
    public static WebElement btn_Back_Dashboard;

    @FindBy(xpath = "//button[contains(.,'Go back to dashboard')]")
    public static WebElement btn_Back_DashboardPO;

    @FindBy(xpath ="//button[contains(.,'Submit')]")
    public static WebElement btn_submit;

   // @FindBy(xpath ="//*[@title='Validate']")
   @FindBy(css = "svg[aria-label='file earmark check']")
    public static WebElement btn_icon;

    @FindBy(xpath ="//*[@title='Validate']")
    public static WebElement btn_icon_pps;

    @FindBy(xpath ="//button[contains(.,'Go To Questionnaire')]")
    public static WebElement btn_Questionnaire;

    @FindBy(xpath ="//button[contains(.,'Hide Error List')]")
    public static WebElement btn_close;




    public Data_validation (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
