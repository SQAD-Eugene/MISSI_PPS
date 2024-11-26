package Package_Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Rec_and_cont {

    WebDriver driver;

    @FindBy(linkText = "RECEIPT & CONTROL")
    public static WebElement lbl_Receipt_and_control;
    @FindBy(linkText = "WORKLOAD ASSIGNMENT")
    public static WebElement lbl_work_assign;
    @FindBy(linkText = "WORKLOAD ASSIGNMENT") //*[@id="__BVID__19"]/div/nav/ul/li[1]/a/p
    public static WebElement lbl_work_assign_CO_Staff;
    @FindBy(linkText = "WORKLOAD ASSIGNMENT")
    public static WebElement lbl_work_assign_PO_Sup;
    @FindBy(linkText = "VIEW WORKLOAD")
    public static WebElement lbl_view_workload;

    @FindBy(linkText = "CAWI REGISTRATION")
    public static WebElement lbl_cawi_reg;

    @FindBy(linkText = "WORKLOAD REFERRAL")
    public static WebElement lbl_work_ref;

    //WORKLOAD ASSIGNMENT
    @FindBy(xpath = "//label[contains(.,'Year: ')]")
    public static WebElement lbl_Year;

    @FindBy(xpath ="//*[@id=\"yearSelect\"]")
    public static WebElement dd_Year;

    @FindBy (xpath="//label[contains(.,'Office: ')]")
    public static WebElement lbl_Province;

    @FindBy(xpath ="//*[@id=\"provinceSelect\"]")
    public static WebElement dd_Province;

    @FindBy(xpath ="//label[contains(.,'Municipality: ')]")
    public static WebElement lbl_Municipality;

    @FindBy(xpath ="//*[@id=\"municipalitySelect\"]")
    public static WebElement dd_Municipality;

    @FindBy(xpath ="//label[contains(.,'Survey: ')]")
    public static WebElement lbl_Survey;

    @FindBy(xpath ="//*[@id=\"surveySelect\"]")
    public static WebElement dd_Survey;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[1]/div[5]/button")
    public static WebElement btn_Search;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[1]/div[1]/div/label")
    public static WebElement lbl_Page;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[1]/div[1]/div/div/select")
    public static WebElement dd_Page;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[1]/div[2]/div/label")
    public static WebElement lbl_Search;

    @FindBy(xpath ="//*[@id=\"filter-input\"]")
    public static WebElement txt_Search;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[1]/div[2]/div/div/div/div/button")
    public static WebElement btn_Filter;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[2]/div/div/div/label")
    public static WebElement lbl_assgnd_not_assgnd;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[2]/div/div/div/div/select")
    public static WebElement dd_assgnd_not_assgnd;

    @FindBy(xpath = "//button[contains(.,'Assign')]")
    public static WebElement btn_Assign;

    @FindBy (xpath = "/html/body/div[2]/div[1]/div/div/footer/button[2]")
    public static WebElement btn_Assign_modal;

    @FindBy (xpath ="/html/body/div[3]/div[1]/div/div/footer/button")  //*[@id="updateSuccessModal___BV_modal_footer_"]/button
    public  static WebElement btn_close_modal;

    @FindBy(xpath = "//button[contains(.,'Remove')]")
    public static WebElement btn_remove;

    @FindBy (xpath ="//*[@id=\"removeModal___BV_modal_footer_\"]/button[2]")
    public static WebElement btn_remove_Yes;

    @FindBy (xpath= "//*[@id=\"reassignModal___BV_modal_footer_\"]/button[2]")
    public static  WebElement btn_YES;

    @FindBy(xpath ="//*[@id=\"assignModal___BV_modal_footer_\"]/button[1]")
    public static WebElement btn_cancel;
    @FindBy(xpath ="/html/body/div[2]/div[1]/div/div/div/select")
    public static WebElement dd_List_PO_Staff;

    @FindBy(xpath = "//*[@id=\"sidebar-no-header\"]/div/div/nav/ul/li[2]/a/div/div[1]/p")
    public static WebElement lbl_receipt_and_control;

    @FindBy(xpath ="//*[@id=\"messagePromptModal___BV_modal_footer_\"]/button")
    public static WebElement btn_Error_ok;

    @FindBy(xpath = "/html/body/div[3]/div[1]/div/div/footer/button")
    public static WebElement btn_close_modal_CO;

    @FindBy(xpath ="/html/body/div/div[2]/div/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/button[2]")
    public static WebElement btn_remove_CO;

    @FindBy (xpath = "/html/body/div/div[2]/div/div[2]/div[3]/table/tbody/tr[2]/td[7]/div/button[1]")
    public static  WebElement btn_Reassign_workload_CO;

    @FindBy (xpath = "//*[@id=\"reassignModal___BV_modal_footer_\"]/button[2]")
    public static WebElement btn_Reassign_Workload_modal_Yes_CO;

    @FindBy (xpath = "/html/body/div[3]/div[1]/div/div/div/select")
    public static WebElement btn_Reassign_workload_List_of_PO_staff_CO;

    @FindBy (xpath = "//*[@id=\"updateSuccessModal___BV_modal_footer_\"]/button")
    public static WebElement btn_reassign_close_modal;

    @FindBy (xpath = "//*[@id=\"reassignPoModal___BV_modal_footer_\"]/button[2]")
    public static WebElement btn_Reassign_workload_CO_modal;



    public Rec_and_cont (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }

}
