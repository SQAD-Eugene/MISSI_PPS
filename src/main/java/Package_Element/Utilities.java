package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Utilities {

    WebDriver driver;


    @FindBy(linkText = "UTILITIES")
    public static WebElement lbl_Utilities;
//    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/nav/ul/li[8]/a")
//    public static WebElement lbl_Utilities;

    @FindBy(linkText = "ACTIVE USERS")
    public static WebElement lbl_Active_User;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div/button")
    public static WebElement btn_create_user;

    @FindBy(xpath = "//input[@placeholder='Enter first name']")
    public static WebElement txt_Fname;

    @FindBy(xpath = "//input[@placeholder='Enter middle name (optional)']")
    public static WebElement txt_Mname;

    @FindBy(xpath = "//input[@placeholder='Enter last name']")
    public static WebElement txt_Lname;

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    public static WebElement txt_Email;

    @FindBy(xpath ="//*[@id=\"__BVID__112\"]")
    public static WebElement dd_Prov_Off;

    @FindBy(xpath = "//*[@id=\"__BVID__114\"]")
    public static WebElement dd_User_role;

    @FindBy(xpath = "//input[@placeholder='Enter Employee ID']")
    public static WebElement txt_Emp_ID;

    @FindBy(xpath = "//*[@id=\"__BVID__112\"]")
    public static WebElement dd_RSSO;

    @FindBy(xpath = "//*[@id=\"modal-create-new-user___BV_modal_body_\"]/div/div[5]/div/div/label")
    public static WebElement Rad_RSSO;

    @FindBy(xpath = "//*[@id=\"modal-create-new-user___BV_modal_footer_\"]/button[1]")
    public static WebElement btn_cancel;

    @FindBy(xpath = "//*[@id=\"per-page-select\"]")
    public static WebElement dd_Ppage;

    @FindBy(xpath = "//*[@id=\"filter-input\"]")
    public static WebElement txt_Search;

    @FindBy(xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view;

    @FindBy(xpath ="//button[contains(.,'Create')]")
    public static WebElement btn_create;

    @FindBy(xpath = "//*[@id=\"__BVID__44\"]/tbody/tr/td[9]/div/button")
    public static WebElement btn_view_CO_Staff;

    @FindBy(xpath ="//button[contains(.,'Update')]")
    public static WebElement btn_update;

    @FindBy(xpath ="//button[contains(.,'Deactivate')]")
    public static WebElement btn_deact;

    @FindBy(xpath = "//*[@id=\"viewUser___BV_modal_footer_\"]/button")
    public static WebElement btn_ok;

    @FindBy(xpath = "//input[@placeholder='Enter first name']")
    public static WebElement txt_FnamePO;

    @FindBy(xpath = "//input[@placeholder='Enter middle name (optional)']")
    public static WebElement txt_MnamePO;

    @FindBy(xpath = "//input[@placeholder='Enter last name']")
    public static WebElement txt_LnamePO;

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    public static WebElement txt_EmailPO;

    @FindBy(xpath ="//select[contains(.,' Select Role')]")
    public static WebElement dd_User_rolePO;

    @FindBy(xpath ="//input[@placeholder='Enter Employee ID']")
    public static WebElement txt_Emp_IDPO;

    @FindBy(xpath ="//select[contains(.,' Select Region ')]")
    public static WebElement dd_RSSOPO;

    @FindBy(xpath = "//*[@id=\"modal-create-new-user___BV_modal_body_\"]/div/div[5]/div/div/label")
    public static WebElement Rad_RSSOPO;

    @FindBy(xpath = "//select[contains(.,' Select Office ')]")
    public static WebElement dd_Prov_OffPO;

    @FindBy(xpath ="//button[contains(.,'View')]")
    public static WebElement btn_viewPO;

    @FindBy(xpath ="//button[contains(.,'Update')]")
    public static WebElement btn_updatePO;

    @FindBy(linkText = "INACTIVE USERS")
    public static WebElement lbl_Inactive;

    @FindBy(xpath = "//*[@id=\"viewUser___BV_modal_footer_\"]/button[1]")
    public static WebElement btn_cancel1;

    @FindBy(xpath ="//button[contains(.,'Activate')]")
    public static WebElement btn_activate;

    @FindBy(xpath = "//*[contains(text(),'15')]")
    public static WebElement user;

    @FindBy(xpath ="//button[contains(.,'Yes')]")
    public static WebElement btn_deact_Yes;

    @FindBy(xpath ="//button[contains(.,'Yes')]")
    public static WebElement btn_Activate_Yes;

    @FindBy(xpath ="//*[@id=\"viewUser___BV_modal_footer_\"]/button")
    public static WebElement btn_ok_CO_Staff;

    @FindBy(xpath = "//*[@id=\"viewUser___BV_modal_footer_\"]/button[1]")
    public static WebElement btn_cancel_PO_Sup;

    @FindBy(xpath = "//input[@placeholder='Enter first name']")
    public  static WebElement txt_Fname_PO;

    @FindBy(xpath = "//input[@placeholder='Enter middle name']")
    public static WebElement txt_Mname_PO;

    @FindBy(xpath = "//input[@placeholder='Enter last name']")
    public static WebElement txt_Lname_PO;

    @FindBy (xpath = "//*[@id=\"viewUser___BV_modal_footer_\"]/button[3]")
    public static WebElement btn_Update_PO;

    @FindBy (xpath = "//*[@id=\"saveUserModal___BV_modal_footer_\"]/button")
    public static WebElement btn_Update_modal_ok;

    @FindBy (xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view_PO;

    @FindBy (xpath = "//*[@id=\"viewUser___BV_modal_footer_\"]/button")
    public static WebElement btn_view_PO_ok;

    @FindBy(xpath ="//button[contains(.,'Cancel')]")
    public static WebElement btn_cancel2;

    @FindBy (xpath="//*[@id=\"__BVID__27\"]/div/nav/ul/li[2]/a/p")
    public static WebElement btn_inactive_user_PO_Staff;

    @FindBy(linkText = "LOGOUT")
    public static WebElement lbl_Logout;

    @FindBy(xpath ="//button[contains(.,'Proceed')]")
    public static WebElement btn_proceed;

    public Utilities (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
