package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cawi_Registration {

    WebDriver driver;

    @FindBy(linkText = "RECEIPT & CONTROL")
    public static WebElement lbl_Receipt_and_control;
    @FindBy(linkText = "CAWI REGISTRATION")
    public static WebElement btn_Cawi_Reg;

    @FindBy(xpath = "//*[@id=\"year\"]")
    public static WebElement dd_Year;

    @FindBy(xpath = "//*[@id=\"surveySelect\"]")
    public static WebElement dd_Survey;

    @FindBy(xpath = "//*[@id=\"searchInput\"]")
    public static WebElement txt_ECN;

    @FindBy(xpath = "//button[contains(.,'Edit')]")
    public static WebElement btn_edit;

    @FindBy(xpath = "//button[contains(.,'Edit')]")
    public static WebElement btn_edit_CO_Staff;

    @FindBy(xpath = "/html/body/div/div[2]/div/div/div[3]/div/div/div/div/button")
    public static WebElement btn_search;

    @FindBy(xpath = "//input[@placeholder='Please input contact person name']")
    public static WebElement txtC_Person;

    @FindBy(xpath = "//input[@placeholder='Please input contact number. ex. 09XXXXXXXXX or 02XXXXXXXX']")
    public static WebElement txtC_number;

    @FindBy(xpath = "//input[@placeholder='Please input email address']")
    public static WebElement txtE_Address;

    @FindBy(xpath ="//*[@id=\"viewCawiAccountModal___BV_modal_footer_\"]/button[2]")
    public static WebElement btn_save;

    @FindBy (xpath ="/html/body/div/div[2]/div/div/div[3]/div/div/div/div/button")
    public static WebElement btn_Search;

    @FindBy(xpath = "//button[contains(.,'Close')]")
    public static WebElement btn_close;

    @FindBy(xpath ="//*[@id=\"messagePromptModal___BV_modal_footer_\"]/button")
    public static WebElement btn_error_ok_rsso;

    @FindBy (xpath ="//*[@id=\"messagePromptModal___BV_modal_footer_\"]/button")
    public static WebElement btn_error_ok_PO;
    public Cawi_Registration (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }

}
