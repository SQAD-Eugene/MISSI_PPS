package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class View_Data {

    WebDriver driver;

    @FindBy(linkText = "RECORD MANAGEMENT")
    public static WebElement lbl_Record_management;

    @FindBy(linkText = "VIEW DATA")
    public static WebElement lbl_View_data;

    @FindBy(xpath ="//select[contains(.,'Select Survey Type')]")
    public static WebElement dd_survey;

    @FindBy(xpath ="//select[contains(.,'Select month')]")
    public static WebElement dd_Month;

    @FindBy(xpath ="//select[contains(.,'Select year')]")
    public static WebElement dd_Year;

    @FindBy(xpath ="//select[contains(.,'- Please Select -')]")
    public static WebElement dd_industry_class;

    @FindBy(xpath ="//select[contains(.,'- Please Select Industry Description-')]")
    public static WebElement dd_industry_desc;

    @FindBy(xpath ="//button[contains(.,'Show Data')]")
    public static WebElement btn_Show_data;






    public View_Data (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
