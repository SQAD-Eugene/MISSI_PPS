package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Reports {

    WebDriver driver;

    @FindBy(linkText = "REPORTS")
    public static WebElement lbl_Reports;

    @FindBy(linkText = "WORKSHEET")
    public static WebElement lbl_Worksheet;

    @FindBy(linkText = "MONITORING")
    public static WebElement lbl_Monitoring;

    @FindBy(linkText = "CONTROL LIST")
    public static WebElement lbl_Control_list;

    @FindBy(xpath ="//label[contains(.,'Survey: ')]")
    public static WebElement lbl_Survey;

    @FindBy(xpath ="//label[contains(.,'Year: ')]")
    public static WebElement lbl_Year;

    @FindBy(xpath ="//label[contains(.,'Month: ')]")
    public static WebElement lbl_Month;

    @FindBy(xpath ="//label[contains(.,'Categories: ')]")
    public static WebElement lbl_Categories;

//    @FindBy(xpath ="//label[contains(.,'Control List: ')]")
//    public static WebElement lbl_Control_List;

    @FindBy(xpath ="//select[contains(.,'-- Select Survey -- ')]")
    public static WebElement dd_Survey;

    @FindBy(xpath ="//select[contains(.,' -- Select Year --')]")
    public static WebElement dd_Year;

    @FindBy(xpath ="//select[contains(.,' -- Select Month --')]")
    public static WebElement dd_Month;

    @FindBy(xpath ="//select[contains(.,'-- Select Control List --')]")
    public static WebElement dd_Control_List;

    @FindBy(xpath ="//select[contains(.,'-- Select Category --')]")
    public static WebElement dd_Category;

    @FindBy(xpath ="//button[contains(.,'Search')]")
    public static WebElement btn_Search;

    @FindBy(xpath ="//button[contains(.,'Cancel')]")
    public static WebElement btn_Cancel;
    public Reports (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
