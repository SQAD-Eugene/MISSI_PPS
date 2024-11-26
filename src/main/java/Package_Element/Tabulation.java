package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Tabulation {
    WebDriver driver;

    @FindBy(linkText = "TABULATION")
    public static WebElement lbl_Tabulation;

    @FindBy(xpath ="//label[contains(.,'Survey: ')]")
    public static WebElement lbl_Survey;

    @FindBy(xpath ="//label[contains(.,'Year: ')]")
    public static WebElement lbl_Year;

    @FindBy(xpath ="//label[contains(.,'Month: ')]")
    public static WebElement lbl_Month;

    @FindBy(xpath ="//label[contains(.,'List of Table: ')]")
    public static WebElement lbl_List_of_table;

    @FindBy(xpath ="//select[contains(.,'-- Select Survey --')]")
    public static WebElement dd_Survey;

    @FindBy(xpath ="//select[contains(.,' -- Select Year --')]")
    public static WebElement dd_Year;
    @FindBy(xpath ="//select[contains(.,' -- Select Month --')]")
    public static WebElement dd_Month;
    @FindBy(xpath ="//select[contains(.,'-- List of Tables --')]")
    public static WebElement dd_List_tab;

    @FindBy(xpath ="//button[contains(.,'Search')]")
    public static WebElement btn_Search;
    public Tabulation (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
