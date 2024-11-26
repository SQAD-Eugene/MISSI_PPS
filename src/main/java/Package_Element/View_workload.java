package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class View_workload {

    WebDriver driver;

    @FindBy(linkText = "RECEIPT & CONTROL")
    public static WebElement lbl_Receipt_and_control;
//    @FindBy(xpath = "//label[contains(.,'Year: ')]")
    @FindBy(linkText = "VIEW WORKLOAD")
    public static WebElement lbl_view_workload;

    @FindBy(xpath = "//*[@id=\"__BVID__11\"]/div/nav/ul/li[2]/a/p")
    public static WebElement lbl_view_workload_CO_Staff;

    @FindBy(linkText = "Year: ")
    public static WebElement lbl_Year;


    @FindBy(xpath = "//select[contains(.,' -- Select year --')]")
    public static WebElement dd_Year;

    @FindBy(xpath = "//label[contains(.,'Month: ')]")
    public static WebElement lbl_Month;

    @FindBy(xpath = "//*[@id=\"monthSelect\"]")
    public static WebElement dd_Month;


    @FindBy(xpath = "//label[contains(.,'Survey: ')]")
    public static WebElement lbl_Survey;

    @FindBy(xpath = "//*[@id=\"surveySelect\"]")
    public static WebElement dd_Survey;

    @FindBy(xpath = "//label[contains(.,'Province: ')]")
    public static WebElement lbl_Province;

    @FindBy(xpath ="//text[contains(.,'4')]")
    public static WebElement DD_dis;

    @FindBy(xpath = "//*[@id=\"OfficeSelect\"]")
    public static WebElement dd_Province;

    @FindBy(xpath = "/html/body/div/div[2]/div/div/div[5]/button")
    public static WebElement btn_Search;

    @FindBy(xpath = "//button[contains(.,'Search')]")
    public static WebElement btn_Search_PO_Staff;

    @FindBy(xpath = "//button[contains(.,'Search')]")
    public static WebElement btn_Search_rsso;

    @FindBy(xpath ="//*[@id=\"mosSelect\"]")
    public static  WebElement dd_mode_of_submit;
    @FindBy(xpath = "//button[contains(.,'Search')]")
    public static WebElement btn_Search_PO;

    @FindBy(xpath = "//*[@id=\"__BVID__117__BV_label_\"]")
    public static WebElement lbl_Page;

    @FindBy(xpath = "//label[contains(.,'Per page')]")
    public static WebElement lbl_Page_PO;


    @FindBy(xpath = "//*[@id=\"per-page-select\"]")
    public static WebElement dd_Page;

    @FindBy(xpath = "//label[contains(.,'Search')]")
    public static WebElement lbl_Search;

    @FindBy(xpath = "//label[contains(.,'Search')]")
    public static WebElement lbl_Search_PO_staff;

    @FindBy(xpath ="//*[@id=\"__BVID__57__BV_label_\"]")
    public static WebElement lbl_Search_PO_Staff;

    @FindBy(xpath = "//*[@id=\"__BVID__53__BV_label_\"]")
    public static WebElement lbl_Search_PO;

    @FindBy(xpath = "//input[@placeholder='Type to Search']")
    public static WebElement txt_Search;

    @FindBy(xpath = "//button[contains(.,'Log')]")
    public static WebElement btn_log;

    @FindBy(xpath = "//button[contains(.,'Log')]")
    public static WebElement btn_log_CO;

    @FindBy(xpath ="//*[@id=\"__BVID__61\"]/tbody/tr/td[7]/div/button[1]")
    public static WebElement btn_log_PO;
    @FindBy(xpath = "//*[@id=\"viewWorkModal___BV_modal_title_\"]/h4")
    public static WebElement lbl_Company;

    @FindBy(xpath = "//*[@id=\"viewWorkModal___BV_modal_body_\"]/div[1]/div[1]")
    public static WebElement lbl_ECN;

    @FindBy(xpath = "//*[@id=\"viewWorkModal___BV_modal_body_\"]/div[1]/div[1]")
    public static WebElement lbl_industry;

    @FindBy(xpath = "//*[@id=\"viewWorkModal___BV_modal_body_\"]/div[2]/div[1]")
    public static WebElement lbl_Prov;

    @FindBy(xpath = "//Select[contains(.,' Select Municipality ')]")
    public static WebElement lbl_Mun;

    @FindBy(xpath = "//*[@id=\"viewWorkModal___BV_modal_body_\"]/div[2]/div[3]")
    public static WebElement lbl_Bry;

    @FindBy(xpath = "//*[@id=\"disSelect__value_\"]")
    public static WebElement dd_Date_Distribution;


    @FindBy(xpath ="//*[@id=\"__BVID__137__calendar-wrapper_\"]/footer/div/button[3]")
    public static WebElement dd_Date_Distribution_close;

    @FindBy(xpath = "//*[@id=\"colSelect__value_\"]")
    public static WebElement dd_Date_Collection;

    @FindBy(xpath = "//*[@id=\"recSelect__value_\"]")
    public static WebElement dd_Date_Receipt_PO;

    @FindBy(xpath = "//*[@id=\"serSelect\"]")
    public static WebElement dd_Status;

    @FindBy(xpath = "//*[@id=\"mosSelect\"]")
    public static WebElement dd_mode;

    @FindBy(xpath ="//*[@id=\"provinceSelect\"]")
    public static WebElement dd_Prov;

    @FindBy(xpath ="//*[@id=\"provinceSelect\"]")
    public static WebElement dd_Prov_CO;

    @FindBy(xpath ="//select[contains(.,' Select Municipality ')]")
    public static WebElement dd_Mun;

    @FindBy(xpath ="//select[contains(.,' Select Barangay ')]")
    public static WebElement dd_Bgy;

    @FindBy(xpath = "//*[@id=\"cp-input\"]")
    public static WebElement txt_Cont_P;

    @FindBy(xpath = "//*[@id=\"cn-input\"]")
    public static WebElement txt_Cont_N;

    @FindBy(xpath = "//*[@id=\"ba-input\"]")
    public static WebElement txt_Business_Add;

    @FindBy(xpath = "//*[@id=\"col-name-input\"]")
    public static WebElement txt_coll_Name;

    @FindBy(xpath = "//*[@id=\"remarks-input\"]")
    public static WebElement txt_Remarks;

    @FindBy(xpath ="//button[contains(.,'Ok')]")
    public static WebElement btn_close;

    @FindBy(xpath ="//button[contains(.,'×')]")
    public static WebElement btn_X;

    @FindBy(xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view_PO;

    @FindBy(xpath = "//*[@id=\"__BVID__63\"]/tbody/tr[3]/td[7]/div/button[2]")
    public  static  WebElement btn_View_PO_physical;

    @FindBy(xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view;
    @FindBy(xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view1;

    @FindBy(xpath ="//*[@id=\"__BVID__65\"]/tbody/tr[1]/td[7]/div/button[2]")
    public static WebElement btn_view_CO;

    @FindBy (xpath ="//button[contains(.,'View')]")
    public static WebElement btn_view_CO_staff;

    @FindBy(xpath ="//*[@id=\"viewWorkModal___BV_modal_header_\"]/button")
    public static WebElement btn_close_view;
    @FindBy(xpath ="//button[contains(.,'Search')]")
    public static  WebElement btn_search_rsso;

    @FindBy(id ="__BVID__68__BV_label_")
    public static WebElement lbl_Per_page_rsso;
    @FindBy(xpath ="//*[@id=\"messagePromptModal___BV_modal_footer_\"]/button") //*[@id="messagePromptModal___BV_modal_footer_"]/button
    public static WebElement btn_error_ok_rsso;

    @FindBy(xpath = "//label[contains(.,'Year: ')]")
    public static WebElement lbl_Year_PO_Staff;

    public View_workload (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }


}
