package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Workload_Referral {


    WebDriver driver;

    @FindBy(linkText = "RECEIPT & CONTROL")
    public static WebElement lbl_Receipt_and_control;
    @FindBy(linkText = "WORKLOAD REFERRAL")
    public static WebElement lbl_Workload_Referral;

    @FindBy(xpath = "//*[@id=\"yearSelect\"]")
    public static WebElement dd_Year;

    @FindBy(xpath = "//*[@id=\"surveySelect\"]")
    public static WebElement dd_Survey;

    @FindBy(xpath = "//*[@id=\"referralSelect\"]")
    public static WebElement dd_Referral;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div[4]/button")
    public static WebElement btn_Search;

    @FindBy(xpath = "//*[@id=\"per-page-select\"]")
    public static WebElement dd_P_PAge;

    @FindBy(xpath = "//*[@id=\"filter-input\"]")
    public static WebElement txt_Search;

    @FindBy(xpath = "//*[@id=\"__BVID__53\"]/div/div/div/button")
    public static WebElement btn_Filter;

    @FindBy(xpath ="//*[@id=\"messagePromptModal___BV_modal_footer_\"]/button")
    public static WebElement btn_error_ok_PO;

    @FindBy(xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view;

    @FindBy(xpath ="//button[contains(.,'Close')]")
    public static WebElement btn_close;


//    @FindBy(xpath = "")
//    public static WebElement lbl_;

//    @FindBy(xpath = "")
//    public static WebElement lbl_;

//    @FindBy(xpath = "")
//    public static WebElement lbl_;

//    @FindBy(xpath = "")
//    public static WebElement lbl_;

//    @FindBy(xpath = "")
//    public static WebElement lbl_;



    public Workload_Referral (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
