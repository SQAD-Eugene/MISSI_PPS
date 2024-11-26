package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class List_of_sample_estab {
    WebDriver driver;

    @FindBy(linkText = "RECORD MANAGEMENT")
    public static WebElement lbl_Record_Management;
    @FindBy(linkText = "LIST OF SAMPLE ESTABLISHMENTS")
    public static WebElement lbl_list_of_sample;

    @FindBy(xpath ="//select[contains(.,'-- Select Year of Establishments --')]")
    public static WebElement dd_List_estab;

    @FindBy(xpath ="//button[contains(.,'Show')]")
    public static WebElement btn_show;

    @FindBy(id ="per-page-select")
    public static WebElement dd_Per_page;

    @FindBy(xpath = "//input[@placeholder='Type to Search']")
    public static WebElement txt_search;





    public List_of_sample_estab(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
