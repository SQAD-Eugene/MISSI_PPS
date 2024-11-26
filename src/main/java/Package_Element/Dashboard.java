package Package_Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {

    WebDriver driver;
    @FindBy(linkText ="RECEIPT & CONTROL")
    public static WebElement lbl_Receipt_and_control_CO;
    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[1]")
    public static WebElement lbl_co_name;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[1]")
    public static WebElement lbl_rsso_name;
    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[1]")
    public static WebElement lbl_po_sup_name;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[2]")
    public static WebElement lbl_co_position;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[2]")
    public static WebElement lbl_po_sup_position;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[3]")
    public static WebElement lbl_co_location;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[3]")
    public static WebElement lbl_po_sup_location;

    @FindBy(linkText = "DASHBOARD")
    public static WebElement lbl_dashboard;

    @FindBy(linkText = "RECEIPT & CONTROL" )
    public static WebElement lbl_Receipt_and_control;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/nav/ul/li[3]/a")
    public static WebElement lbl_dataentry;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/nav/ul/li[8]/a")
    public static WebElement lbl_Utilities;

    @FindBy(linkText = "LOGOUT")
    public static WebElement lbl_logout;

    @FindBy(xpath ="//p[text()='killa zawa']")
    public WebElement lbl_co_name_CO_Staff;

    @FindBy(xpath ="//p[text()='CO - Staff ']")
    public WebElement lbl_co_position_CO_Staff;

    @FindBy(xpath ="//p[text()='(Central Office)']")
    public WebElement lbl_co_location_CO_Staff;

    @FindBy(linkText = "DASHBOARD")
    public WebElement lbl_dashboard_CO_Staff;

    @FindBy(linkText = "RECEIPT & CONTROL")
    public WebElement lbl_Receipt_and_control_CO_Staff;

    @FindBy(linkText = "DATA ENTRY")
    public WebElement lbl_dataentry_CO_Staff;

    @FindBy(linkText = "UTILITIES")
    public WebElement lbl_Utilities_CO_Staff;

    @FindBy(linkText = "LOGOUT")
    public WebElement lbl_logout_CO_Staff;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[1]")
    public static WebElement lbl_po_name_PO;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[2]")
    public static WebElement lbl_po_position_PO;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/div/div[2]/p[3]")
    public static WebElement lbl_po_location_PO;

    @FindBy(linkText = "DASHBOARD") //*[@id="navbar"]/ul/li[1]/a
    public static WebElement lbl_dashboard_PO;

    @FindBy(linkText = "RECEIPT & CONTROL")
    public static WebElement lbl_Receipt_and_control_PO;

    @FindBy(linkText = "DATA ENTRY")
    public static WebElement lbl_dataentry_PO;

    @FindBy(linkText = "UTILITIES")
    public static WebElement lbl_Utilities_PO;

    @FindBy(linkText = "LOGOUT")
    public static WebElement lbl_logout_PO;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[1]/div/div/label")
    public static WebElement switch_questionnaire;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]/div[1]/div/button[1]")
    public static WebElement btn_Nat;

    @FindBy(xpath ="//button[contains(.,'Regional')]")
    public static WebElement btn_Reg;

    @FindBy(xpath ="//button[contains(.,'Provincial')]")
    public static WebElement btn_Prov;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[3]/div[1]/h5[1]")
    public static WebElement GRAP_MISSI_SUMMARY;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[3]/div[2]/h5[1]")
    public static WebElement GRAP_MISSI_RATE_SAMPLE;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[5]/div/div/h5[1]")
    public static WebElement GRAP_MISSI_RATE_IND;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]/div[2]/h5[1]")
    public static WebElement GRAP_MISSI_Reg_Status;

    @FindBy(xpath ="//*[@id=\"content\"]/div/div/div/div[2]/div[2]/h5[1]")
    public static WebElement GRAP_MISSI_Reg_Status_Reg;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]/div[2]/h5[2]")
    public static WebElement GRAP_MISSI_Prov_Status;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]/div[2]/h5[2]")
    public static WebElement GRAP_PPS_Prov_Status;


    @FindBy(xpath ="//button[contains(.,'National')]")
    public static WebElement btn_national;


    @FindBy(xpath ="//button[contains(.,'Region')]")
    public static WebElement btn_region;


    @FindBy(xpath ="//button[contains(.,'Provincial')]")
    public static WebElement btn_provincial;

    @FindBy(xpath = "//*[@id=\"statProvince\"]")
    public static WebElement dd_prov;


    public Dashboard (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }
}
