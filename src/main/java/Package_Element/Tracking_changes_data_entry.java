package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Tracking_changes_data_entry {

    WebDriver driver;

    @FindBy(linkText = "RECORD MANAGEMENT")
    public static WebElement lbl_Record_Management;
    @FindBy(linkText = "TRACKING OF CHANGES IN DATA ENTRY")
    public static WebElement lbl_Tracking_of_changes;

    @FindBy(xpath ="//select[contains(.,'-- Select Year --')]")
    public static WebElement dd_Yr;

    @FindBy(xpath ="//select[contains(.,'-- Select Month --')]")
    public static WebElement dd_Mth;

    @FindBy(id ="surveyTypes")
    public static WebElement dd_Sur;

    @FindBy(id ="IdCode")
    public static WebElement dd_id_code;

    @FindBy(id ="per-page-select")
    public static WebElement dd_Ppage;

    @FindBy(xpath = "//input[@placeholder='Type to Search']")
    public static WebElement txt_Search;

    @FindBy(xpath ="//button[contains(.,'Filter')]")
    public static WebElement btn_Filter;

    @FindBy(xpath ="//button[contains(.,'Show')]")
    public static WebElement btn_Show;


    public Tracking_changes_data_entry(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
