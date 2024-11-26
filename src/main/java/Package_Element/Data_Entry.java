package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Data_Entry {

    WebDriver driver;

    @FindBy(linkText = "DATA ENTRY")
    public static WebElement lbl_Data_entry;

    @FindBy(linkText = "MISSI")
    public static WebElement lbl_Missi;

    @FindBy(xpath = "//*[@id=\"year\"]")
    public static WebElement dd_Year;

    @FindBy(xpath = "//*[@id=\"month\"]")
    public static WebElement dd_Month;

    @FindBy(xpath = "//input[@placeholder='Enter or select ECN:']")
    public static WebElement txt_ecn;


    @FindBy(xpath ="//button[contains(.,'Go back to dashboard')]")
    public static WebElement btn_Back_Dashboard;

    @FindBy(xpath = "//*[@id=\"selectEcnModal___BV_modal_footer_\"]/div/div/div[2]/button")
    public static WebElement btn_Submit;

    @FindBy(linkText = "PPS")
    public static WebElement lbl_PPS;

    @FindBy(xpath = "//*[@id=\"missi-form\"]/div/div[1]/div[1]/h4")
    public static WebElement lbl_Gen_info;

    @FindBy(xpath ="//*[@id=\"content\"]/form/div/div[1]/div[1]/h4")
    public static WebElement lbl_Gen_info_PPS;

    @FindBy(xpath = "//input[@placeholder='Enter Establishment Control Number (ECN)']")
    public static WebElement txt_ECN;

    @FindBy(xpath = "//label[text()='Business Name:']")
    public static WebElement lbl_D_name;

    @FindBy(xpath = "//input[@placeholder='Enter business name']")
    public static WebElement txt_D_name;

    @FindBy(xpath = "//label[text()='Business Address:']")
    public static WebElement lbl_D_Add;

    @FindBy(xpath = "//input[@placeholder='Enter business address']")
    public static WebElement txt_D_Add;

    @FindBy(xpath = "//label[text()='TIN:']")
    public static WebElement lbl_D_TIN;

    @FindBy(xpath = "//input[@placeholder='Enter TIN']")
    public static WebElement txt_D_TIN;

    @FindBy(xpath = "//label[text()='2009 PSIC Code (IND):']")
    public static WebElement lbl_D_IND;

    @FindBy(xpath = "//input[@placeholder='Enter PSIC code (IND)']")
    public static WebElement txt_D_IND;


    @FindBy(xpath = "//*[@id=\"__BVID__50__BV_label_\"]")
    public static WebElement lbl_D_Main_Eco;

    @FindBy(xpath = "//*[@id=\"__BVID__50\"]/div/small/em/text()")
    public static WebElement lbl_D_Main_Eco2;

    @FindBy(xpath = "//textarea[@placeholder='Enter main economic activity']")
    public static WebElement txt_D_Main_Eco;

    @FindBy(xpath = "//*[@id=\"__BVID__61__BV_label_\"]")
    public static WebElement lbl_B_name;

    @FindBy(xpath = "//input[@placeholder='Enter business name']")
    public static WebElement txt_B_name;

    @FindBy(xpath ="//*[@id=\"__BVID__52\"]")
    public static WebElement D_table;


    @FindBy(xpath = "//*[@id=\"formDataLN01_M1\"]")
    public static WebElement T_1;
    @FindBy(xpath = "//*[@id=\"formDataLN01_M2\"]")
    public static WebElement T_2;
    @FindBy(xpath = "//*[@id=\"formDataLN01_M3\"]")
    public static WebElement T_3;
    @FindBy(xpath = "//*[@id=\"formDataLN01_M4\"]")
    public static WebElement T_4;
    @FindBy(xpath = "//*[@id=\"formDataLN01_M5\"]")
    public static WebElement T_5;
    @FindBy(xpath = "//*[@id=\"formDataLN01_M6\"]")
    public static WebElement T_6;
    @FindBy(xpath = "//*[@id=\"formDataLN01_M7\"]")
    public static WebElement T_7;


    @FindBy(xpath = "//*[@id=\"formDataLN02_M1\"]")
    public static WebElement W_1;
    @FindBy(xpath = "//*[@id=\"formDataLN02_M2\"]")
    public static WebElement W_2;
    @FindBy(xpath = "//*[@id=\"formDataLN02_M3\"]")
    public static WebElement W_3;
    @FindBy(xpath = "//*[@id=\"formDataLN02_M4\"]")
    public static WebElement W_4;
    @FindBy(xpath = "//*[@id=\"formDataLN02_M5\"]")
    public static WebElement W_5;
    @FindBy(xpath = "//*[@id=\"formDataLN02_M6\"]")
    public static WebElement W_6;
    @FindBy(xpath = "//*[@id=\"formDataLN02_M7\"]")
    public static WebElement W_7;


    @FindBy(xpath = "//*[@id=\"formDataLN03_M1\"]")
    public static WebElement P_1;
    @FindBy(xpath = "//*[@id=\"formDataLN03_M2\"]")
    public static WebElement P_2;
    @FindBy(xpath = "//*[@id=\"formDataLN03_M3\"]")
    public static WebElement P_3;
    @FindBy(xpath = "//*[@id=\"formDataLN03_M4\"]")
    public static WebElement P_4;
    @FindBy(xpath = "//*[@id=\"formDataLN03_M5\"]")
    public static WebElement P_5;
    @FindBy(xpath = "//*[@id=\"formDataLN03_M6\"]")
    public static WebElement P_6;
    @FindBy(xpath = "//*[@id=\"formDataLN03_M7\"]")
    public static WebElement P_7;


    @FindBy(xpath = "//*[@id=\"formDataLN04_M1\"]")
    public static WebElement M_1;
    @FindBy(xpath = "//*[@id=\"formDataLN04_M2\"]")
    public static WebElement M_2;
    @FindBy(xpath = "//*[@id=\"formDataLN04_M3\"]")
    public static WebElement M_3;
    @FindBy(xpath = "//*[@id=\"formDataLN04_M4\"]")
    public static WebElement M_4;
    @FindBy(xpath = "//*[@id=\"formDataLN04_M5\"]")
    public static WebElement M_5;
    @FindBy(xpath = "//*[@id=\"formDataLN04_M6\"]")
    public static WebElement M_6;
    @FindBy(xpath = "//*[@id=\"formDataLN04_M7\"]")
    public static WebElement M_7;


    @FindBy(xpath = "//*[@id=\"formDataLN05_M1\"]")
    public static WebElement PW_1;
    @FindBy(xpath = "//*[@id=\"formDataLN05_M2\"]")
    public static WebElement PW_2;
    @FindBy(xpath = "//*[@id=\"formDataLN05_M3\"]")
    public static WebElement PW_3;
    @FindBy(xpath = "//*[@id=\"formDataLN05_M4\"]")
    public static WebElement PW_4;
    @FindBy(xpath = "//*[@id=\"formDataLN05_M5\"]")
    public static WebElement PW_5;
    @FindBy(xpath = "//*[@id=\"formDataLN05_M6\"]")
    public static WebElement PW_6;
    @FindBy(xpath = "//*[@id=\"formDataLN05_M7\"]")
    public static WebElement PW_7;



    @FindBy(xpath = "//*[@id=\"formDataLN06_M1\"]")
    public static WebElement OT_1;
    @FindBy(xpath = "//*[@id=\"formDataLN06_M2\"]")
    public static WebElement OT_2;
    @FindBy(xpath = "//*[@id=\"formDataLN06_M3\"]")
    public static WebElement OT_3;
    @FindBy(xpath = "//*[@id=\"formDataLN06_M4\"]")
    public static WebElement OT_4;
    @FindBy(xpath = "//*[@id=\"formDataLN06_M5\"]")
    public static WebElement OT_5;
    @FindBy(xpath = "//*[@id=\"formDataLN06_M6\"]")
    public static WebElement OT_6;
    @FindBy(xpath = "//*[@id=\"formDataLN06_M7\"]")
    public static WebElement OT_7;


    @FindBy(xpath = "//*[@id=\"formDataLN07_M1\"]")
    public static WebElement TC_1;
    @FindBy(xpath = "//*[@id=\"formDataLN07_M2\"]")
    public static WebElement TC_2;
    @FindBy(xpath = "//*[@id=\"formDataLN07_M3\"]")
    public static WebElement TC_3;
    @FindBy(xpath = "//*[@id=\"formDataLN07_M4\"]")
    public static WebElement TC_4;
    @FindBy(xpath = "//*[@id=\"formDataLN07_M5\"]")
    public static WebElement TC_5;
    @FindBy(xpath = "//*[@id=\"formDataLN07_M6\"]")
    public static WebElement TC_6;
    @FindBy(xpath = "//*[@id=\"formDataLN07_M7\"]")
    public static WebElement TC_7;



    @FindBy(xpath = "//*[@id=\"formDataLN08_M1\"]")
    public static WebElement TS_1;
    @FindBy(xpath = "//*[@id=\"formDataLN08_M2\"]")
    public static WebElement TS_2;
    @FindBy(xpath = "//*[@id=\"formDataLN08_M3\"]")
    public static WebElement TS_3;
    @FindBy(xpath = "//*[@id=\"formDataLN08_M4\"]")
    public static WebElement TS_4;
    @FindBy(xpath = "//*[@id=\"formDataLN08_M5\"]")
    public static WebElement TS_5;
    @FindBy(xpath = "//*[@id=\"formDataLN08_M6\"]")
    public static WebElement TS_6;
    @FindBy(xpath = "//*[@id=\"formDataLN08_M7\"]")
    public static WebElement TS_7;


    @FindBy(xpath = "//*[@id=\"formDataLN09_M1\"]")
    public static WebElement SM_1;
    @FindBy(xpath = "//*[@id=\"formDataLN09_M2\"]")
    public static WebElement SM_2;
    @FindBy(xpath = "//*[@id=\"formDataLN09_M3\"]")
    public static WebElement SM_3;
    @FindBy(xpath = "//*[@id=\"formDataLN09_M4\"]")
    public static WebElement SM_4;
    @FindBy(xpath = "//*[@id=\"formDataLN09_M5\"]")
    public static WebElement SM_5;
    @FindBy(xpath = "//*[@id=\"formDataLN09_M6\"]")
    public static WebElement SM_6;
    @FindBy(xpath = "//*[@id=\"formDataLN09_M7\"]")
    public static WebElement SM_7;


    @FindBy(xpath = "//*[@id=\"formDataLN10_M1\"]")
    public static WebElement SW_1;
    @FindBy(xpath = "//*[@id=\"formDataLN10_M2\"]")
    public static WebElement SW_2;
    @FindBy(xpath = "//*[@id=\"formDataLN10_M3\"]")
    public static WebElement SW_3;
    @FindBy(xpath = "//*[@id=\"formDataLN10_M4\"]")
    public static WebElement SW_4;
    @FindBy(xpath = "//*[@id=\"formDataLN10_M5\"]")
    public static WebElement SW_5;
    @FindBy(xpath = "//*[@id=\"formDataLN10_M6\"]")
    public static WebElement SW_6;
    @FindBy(xpath = "//*[@id=\"formDataLN10_M7\"]")
    public static WebElement SW_7;


    @FindBy(xpath = "//*[@id=\"formDataLN11_M1\"]")
    public static WebElement SWE_1;
    @FindBy(xpath = "//*[@id=\"formDataLN11_M2\"]")
    public static WebElement SWE_2;
    @FindBy(xpath = "//*[@id=\"formDataLN11_M3\"]")
    public static WebElement SWE_3;
    @FindBy(xpath = "//*[@id=\"formDataLN11_M4\"]")
    public static WebElement SWE_4;
    @FindBy(xpath = "//*[@id=\"formDataLN11_M5\"]")
    public static WebElement SWE_5;
    @FindBy(xpath = "//*[@id=\"formDataLN11_M6\"]")
    public static WebElement SWE_6;
    @FindBy(xpath = "//*[@id=\"formDataLN11_M7\"]")
    public static WebElement SWE_7;


    @FindBy(xpath = "//*[@id=\"formDataLN12_M1\"]")
    public static WebElement TE_1;
    @FindBy(xpath = "//*[@id=\"formDataLN12_M2\"]")
    public static WebElement TE_2;
    @FindBy(xpath = "//*[@id=\"formDataLN12_M3\"]")
    public static WebElement TE_3;
    @FindBy(xpath = "//*[@id=\"formDataLN12_M4\"]")
    public static WebElement TE_4;
    @FindBy(xpath = "//*[@id=\"formDataLN12_M5\"]")
    public static WebElement TE_5;
    @FindBy(xpath = "//*[@id=\"formDataLN12_M6\"]")
    public static WebElement TE_6;
    @FindBy(xpath = "//*[@id=\"formDataLN12_M7\"]")
    public static WebElement TE_7;

    @FindBy(xpath = "//*[@id=\"formDataLN13_M1\"]")
    public static WebElement TVP_1;
    @FindBy(xpath = "//*[@id=\"formDataLN13_M2\"]")
    public static WebElement TVP_2;
    @FindBy(xpath = "//*[@id=\"formDataLN13_M3\"]")
    public static WebElement TVP_3;
    @FindBy(xpath = "//*[@id=\"formDataLN13_M4\"]")
    public static WebElement TVP_4;
    @FindBy(xpath = "//*[@id=\"formDataLN13_M5\"]")
    public static WebElement TVP_5;
    @FindBy(xpath = "//*[@id=\"formDataLN13_M6\"]")
    public static WebElement TVP_6;
    @FindBy(xpath = "//*[@id=\"formDataLN13_M7\"]")
    public static WebElement TVP_7;

    @FindBy(xpath = "//*[@id=\"formDataLN14_M1\"]")
    public static WebElement VP_1;
    @FindBy(xpath = "//*[@id=\"formDataLN14_M2\"]")
    public static WebElement VP_2;
    @FindBy(xpath = "//*[@id=\"formDataLN14_M3\"]")
    public static WebElement VP_3;
    @FindBy(xpath = "//*[@id=\"formDataLN14_M4\"]")
    public static WebElement VP_4;
    @FindBy(xpath = "//*[@id=\"formDataLN14_M5\"]")
    public static WebElement VP_5;
    @FindBy(xpath = "//*[@id=\"formDataLN14_M6\"]")
    public static WebElement VP_6;
    @FindBy(xpath = "//*[@id=\"formDataLN14_M7\"]")
    public static WebElement VP_7;

    @FindBy(xpath = "//*[@id=\"formDataLN15_M1\"]")
    public static WebElement VPDS_1;
    @FindBy(xpath = "//*[@id=\"formDataLN15_M2\"]")
    public static WebElement VPDS_2;
    @FindBy(xpath = "//*[@id=\"formDataLN15_M3\"]")
    public static WebElement VPDS_3;
    @FindBy(xpath = "//*[@id=\"formDataLN15_M4\"]")
    public static WebElement VPDS_4;
    @FindBy(xpath = "//*[@id=\"formDataLN15_M5\"]")
    public static WebElement VPDS_5;
    @FindBy(xpath = "//*[@id=\"formDataLN15_M6\"]")
    public static WebElement VPDS_6;
    @FindBy(xpath = "//*[@id=\"formDataLN15_M7\"]")
    public static WebElement VPDS_7;


    @FindBy(xpath = "//*[@id=\"formDataLN18_M1\"]")
    public static WebElement TR_1;
    @FindBy(xpath = "//*[@id=\"formDataLN18_M2\"]")
    public static WebElement TR_2;
    @FindBy(xpath = "//*[@id=\"formDataLN18_M3\"]")
    public static WebElement TR_3;
    @FindBy(xpath = "//*[@id=\"formDataLN18_M4\"]")
    public static WebElement TR_4;
    @FindBy(xpath = "//*[@id=\"formDataLN18_M5\"]")
    public static WebElement TR_5;
    @FindBy(xpath = "//*[@id=\"formDataLN18_M6\"]")
    public static WebElement TR_6;
    @FindBy(xpath = "//*[@id=\"formDataLN18_M7\"]")
    public static WebElement TR_7;


    @FindBy(xpath = "//*[@id=\"formDataLN19_M1\"]")
    public static WebElement SMA_1;
    @FindBy(xpath = "//*[@id=\"formDataLN19_M2\"]")
    public static WebElement SMA_2;
    @FindBy(xpath = "//*[@id=\"formDataLN19_M3\"]")
    public static WebElement SMA_3;
    @FindBy(xpath = "//*[@id=\"formDataLN19_M4\"]")
    public static WebElement SMA_4;
    @FindBy(xpath = "//*[@id=\"formDataLN19_M5\"]")
    public static WebElement SMA_5;
    @FindBy(xpath = "//*[@id=\"formDataLN19_M6\"]")
    public static WebElement SMA_6;
    @FindBy(xpath = "//*[@id=\"formDataLN19_M7\"]")
    public static WebElement SMA_7;


    @FindBy(xpath = "//*[@id=\"formDataLN20_M1\"]")
    public static WebElement SDM_1;
    @FindBy(xpath = "//*[@id=\"formDataLN20_M2\"]")
    public static WebElement SDM_2;
    @FindBy(xpath = "//*[@id=\"formDataLN20_M3\"]")
    public static WebElement SDM_3;
    @FindBy(xpath = "//*[@id=\"formDataLN20_M4\"]")
    public static WebElement SDM_4;
    @FindBy(xpath = "//*[@id=\"formDataLN20_M5\"]")
    public static WebElement SDM_5;
    @FindBy(xpath = "//*[@id=\"formDataLN20_M6\"]")
    public static WebElement SDM_6;
    @FindBy(xpath = "//*[@id=\"formDataLN20_M7\"]")
    public static WebElement SDM_7;


    @FindBy(xpath = "//*[@id=\"formDataLN21_M1\"]")
    public static WebElement SEE_1;
    @FindBy(xpath = "//*[@id=\"formDataLN21_M2\"]")
    public static WebElement SEE_2;
    @FindBy(xpath = "//*[@id=\"formDataLN21_M3\"]")
    public static WebElement SEE_3;
    @FindBy(xpath = "//*[@id=\"formDataLN21_M4\"]")
    public static WebElement SEE_4;
    @FindBy(xpath = "//*[@id=\"formDataLN21_M5\"]")
    public static WebElement SEE_5;
    @FindBy(xpath = "//*[@id=\"formDataLN21_M6\"]")
    public static WebElement SEE_6;
    @FindBy(xpath = "//*[@id=\"formDataLN21_M7\"]")
    public static WebElement SEE_7;


    @FindBy(xpath = "//*[@id=\"formDataLN24_M1\"]")
    public static WebElement OI_1;
    @FindBy(xpath = "//*[@id=\"formDataLN24_M2\"]")
    public static WebElement OI_2;
    @FindBy(xpath = "//*[@id=\"formDataLN24_M3\"]")
    public static WebElement OI_3;
    @FindBy(xpath = "//*[@id=\"formDataLN24_M4\"]")
    public static WebElement OI_4;
    @FindBy(xpath = "//*[@id=\"formDataLN24_M5\"]")
    public static WebElement OI_5;
    @FindBy(xpath = "//*[@id=\"formDataLN24_M6\"]")
    public static WebElement OI_6;
    @FindBy(xpath = "//*[@id=\"formDataLN24_M7\"]")
    public static WebElement OI_7;


    @FindBy(xpath = "//*[@id=\"formDataLN25_M1\"]")
    public static WebElement FP_1;
    @FindBy(xpath = "//*[@id=\"formDataLN25_M2\"]")
    public static WebElement FP_2;
    @FindBy(xpath = "//*[@id=\"formDataLN25_M3\"]")
    public static WebElement FP_3;
    @FindBy(xpath = "//*[@id=\"formDataLN25_M4\"]")
    public static WebElement FP_4;
    @FindBy(xpath = "//*[@id=\"formDataLN25_M5\"]")
    public static WebElement FP_5;
    @FindBy(xpath = "//*[@id=\"formDataLN25_M6\"]")
    public static WebElement FP_6;
    @FindBy(xpath = "//*[@id=\"formDataLN25_M7\"]")
    public static WebElement FP_7;


    @FindBy(xpath = "//*[@id=\"formDataLN26_M1\"]")
    public static WebElement WIP_1;
    @FindBy(xpath = "//*[@id=\"formDataLN26_M2\"]")
    public static WebElement WIP_2;
    @FindBy(xpath = "//*[@id=\"formDataLN26_M3\"]")
    public static WebElement WIP_3;
    @FindBy(xpath = "//*[@id=\"formDataLN26_M4\"]")
    public static WebElement WIP_4;
    @FindBy(xpath = "//*[@id=\"formDataLN26_M5\"]")
    public static WebElement WIP_5;
    @FindBy(xpath = "//*[@id=\"formDataLN26_M6\"]")
    public static WebElement WIP_6;
    @FindBy(xpath = "//*[@id=\"formDataLN26_M7\"]")
    public static WebElement WIP_7;


    @FindBy(xpath = "//*[@id=\"formDataLN27_M1\"]")
    public static WebElement RM_1;
    @FindBy(xpath = "//*[@id=\"formDataLN27_M2\"]")
    public static WebElement RM_2;
    @FindBy(xpath = "//*[@id=\"formDataLN27_M3\"]")
    public static WebElement RM_3;
    @FindBy(xpath = "//*[@id=\"formDataLN27_M4\"]")
    public static WebElement RM_4;
    @FindBy(xpath = "//*[@id=\"formDataLN27_M5\"]")
    public static WebElement RM_5;
    @FindBy(xpath = "//*[@id=\"formDataLN27_M6\"]")
    public static WebElement RM_6;
    @FindBy(xpath = "//*[@id=\"formDataLN27_M7\"]")
    public static WebElement RM_7;


    @FindBy(xpath = "//*[@id=\"__BVID__619\"]")
    public static WebElement CU_1;
    @FindBy(xpath = "//*[@id=\"__BVID__622\"]")
    public static WebElement CU_2;
    @FindBy(xpath = "//*[@id=\"__BVID__625\"]")
    public static WebElement CU_3;
    @FindBy(xpath = "//*[@id=\"__BVID__628\"]")
    public static WebElement CU_4;
    @FindBy(xpath = "//*[@id=\"__BVID__631\"]")
    public static WebElement CU_5;
    @FindBy(xpath = "//*[@id=\"__BVID__634\"]")
    public static WebElement CU_6;
    @FindBy(xpath = "//*[@id=\"__BVID__637\"]")
    public static WebElement CU_7;

    @FindBy(xpath = "//textarea[@id='formDataLN29_M1']")
    public static WebElement Remarks_1;
    @FindBy(xpath = "//textarea[@id='formDataLN29_M2']")
    public static WebElement Remarks_2;
    @FindBy(xpath = "//textarea[@id='formDataLN29_M3']")
    public static WebElement Remarks_3;
    @FindBy(xpath = "//textarea[@id='formDataLN29_M4']")
    public static WebElement Remarks_4;
    @FindBy(xpath = "//textarea[@id='formDataLN29_M5']")
    public static WebElement Remarks_5;
    @FindBy(xpath = "//textarea[@id='formDataLN29_M6']")
    public static WebElement Remarks_6;
    @FindBy(xpath = "//textarea[@id='formDataLN29_M7']")
    public static WebElement Remarks_7;

    @FindBy(xpath = "//*[@id=\"formDataLN17\"]")
    public static WebElement Reason_1;

    @FindBy(xpath = "//*[@id=\"formDataLN23\"]")
    public static WebElement Reason_2;

    @FindBy(xpath = "//*[@id=\"formDataLN30\"]")
    public static WebElement Reason_3;
    @FindBy(xpath = "//input[@placeholder='Enter product 1']")
    public static WebElement txt_Prod1;

    @FindBy(xpath = "//input[@placeholder='Enter product 2']")
    public static WebElement txt_Prod2;

    @FindBy(xpath = "//input[@placeholder='Enter product 3']")
    public static WebElement txt_Prod3;

    @FindBy(xpath = "//input[@placeholder='Enter product 4']")
    public static WebElement txt_Prod4;

    @FindBy(xpath = "//input[@placeholder='Enter product 5']")
    public static WebElement txt_Prod5;

    @FindBy(xpath = "//input[@placeholder='Enter name of accomplishing officer']")
    public static WebElement Accom_officer;

    @FindBy(xpath = "//input[@placeholder='Enter designation']")
    public static WebElement designation;

    @FindBy(xpath = "//input[@placeholder='Enter contact number']")
    public static WebElement contact;

    @FindBy(xpath = "//input[@placeholder='Enter email address']")
    public static WebElement e_address;

    @FindBy(xpath = "//input[@placeholder='Enter company website']")
    public static WebElement website;

    @FindBy(xpath ="//button[contains(.,'Add New Product')]")
    public static WebElement Add_P;

    @FindBy(xpath = "//input[@placeholder='Enter product name']")
    public static WebElement txt_Prod_name;

    @FindBy(xpath = "//input[@placeholder='Enter product description']")
    public static WebElement txt_desc;

    @FindBy(xpath = "//input[@placeholder='Enter brand name']")
    public static WebElement txt_brand;

    @FindBy(xpath = "//input[@placeholder='Enter specification']")
    public static WebElement txt_specs;

    @FindBy(xpath = "//input[@placeholder='Enter unit of measure']")
    public static WebElement txt_measure;

    @FindBy(xpath = "//input[@placeholder='Enter percent share']")
    public static WebElement txt_share;

    @FindBy(xpath = "//*[@id=\"commodityPsic\"]")
    public static WebElement dd_PSIC;

    @FindBy(xpath ="//button[contains(.,'Submit')]")
    public static WebElement btn_submit_pps;

    @FindBy(xpath ="//button[contains(.,'Select')]")
    public static WebElement btn_select;

    @FindBy(xpath ="//button[contains(.,'Edit')]")
    public static WebElement btn_edit;

    @FindBy(xpath ="//button[contains(.,'Update')]")
    public static WebElement btn_update;

    @FindBy(xpath ="//button[contains(.,'Delete')]")
    public static WebElement btn_delete;

    @FindBy(xpath ="//button[contains(.,'Yes')]")
    public static WebElement btn_yes;

    @FindBy(xpath ="//button[contains(.,'Cancel')]")
    public static WebElement btn_cancel;
    @FindBy(xpath ="//button[contains(.,'Proceed to data entry')]")
    public static WebElement btn_Proceed;

    @FindBy(xpath = "//*[@id=\"__BVID__227\"]")
    public static WebElement txt_PPS_Prod_M1;

    @FindBy(xpath = "//*[@id=\"__BVID__231\"]")
    public static WebElement txt_PPS_Prod_M2;

    @FindBy(xpath = "//*[@id=\"__BVID__235\"]")
    public static WebElement txt_PPS_Prod_M3;

    @FindBy(xpath = "//*[@id=\"__BVID__239\"]")
    public static WebElement txt_PPS_Prod_M4;

    @FindBy(xpath = "//*[@id=\"__BVID__243\"]")
    public static WebElement txt_PPS_Prod_M5;

    @FindBy(xpath = "//*[@id=\"__BVID__247\"]")
    public static WebElement txt_PPS_Prod_M6;

    @FindBy(xpath = "//*[@id=\"__BVID__251\"]")
    public static WebElement txt_PPS_Prod_M7;



    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for JULY 2023']")
    public static WebElement txt_PPS_Remarks_M1;

    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for AUGUST 2023']")
    public static WebElement txt_PPS_Remarks_M2;

    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for SEPTEMBER 2023']")
    public static WebElement txt_PPS_Remarks_M3;

    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for OCTOBER 2023']")
    public static WebElement txt_PPS_Remarks_M4;

    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for NOVEMBER 2023']")
    public static WebElement txt_PPS_Remarks_M5;

    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for DECEMBER 2023']")
    public static WebElement txt_PPS_Remarks_M6;

    @FindBy(xpath = "//textarea[@placeholder='Enter remarks for JANUARY 2024']")
    public static WebElement txt_PPS_Remarks_M7;

    @FindBy(xpath = "//*[@id=\"__BVID__227\"]")
    public static WebElement txt_PPS_CO_Prod_M1;

    @FindBy(xpath = "//*[@id=\"__BVID__231\"]")
    public static WebElement txt_PPS_CO_Prod_M2;

    @FindBy(xpath = "//*[@id=\"__BVID__235\"]")
    public static WebElement txt_PPS_CO_Prod_M3;

    @FindBy(xpath = "//*[@id=\"__BVID__239\"]")
    public static WebElement txt_PPS_CO_Prod_M4;

    @FindBy(xpath = "//*[@id=\"__BVID__243\"]")
    public static WebElement txt_PPS_CO_Prod_M5;

    @FindBy(xpath = "//*[@id=\"__BVID__247\"]")
    public static WebElement txt_PPS_CO_Prod_M6;

    @FindBy(xpath = "//*[@id=\"__BVID__251\"]")
    public static WebElement txt_PPS_CO_Prod_M7;



    @FindBy(xpath = "//*[@id=\"__BVID__256\"]")
    public static WebElement txt_PPS_CO_Remarks_M1;

    @FindBy(xpath = "//*[@id=\"__BVID__258\"]")
    public static WebElement txt_PPS_CO_Remarks_M2;

    @FindBy(xpath = "//*[@id=\"__BVID__260\"]")
    public static WebElement txt_PPS_CO_Remarks_M3;

    @FindBy(xpath = "//*[@id=\"__BVID__262\"]")
    public static WebElement txt_PPS_CO_Remarks_M4;

    @FindBy(xpath = "//*[@id=\"__BVID__264\"]")
    public static WebElement txt_PPS_CO_Remarks_M5;

    @FindBy(xpath = "//*[@id=\"__BVID__266\"]")
    public static WebElement txt_PPS_CO_Remarks_M6;

    @FindBy(xpath = "//*[@id=\"__BVID__268\"]")
    public static WebElement txt_PPS_CO_Remarks_M7;


    @FindBy(xpath = "//*[@id=\"__BVID__83\"]")
    public static WebElement ReasonPPS_1;

    @FindBy(xpath = "//input[@placeholder='Enter name of accomplishing officer']")
    public static WebElement txt_accom;

    @FindBy(xpath = "//input[@placeholder='Enter designation']")
    public static WebElement txt_desig;

    @FindBy(xpath = "//input[@placeholder='Enter email address']")
    public static WebElement txt_eadd;

    @FindBy(xpath = "//input[@placeholder='Enter company website']")
    public static WebElement txt_compweb;

    @FindBy(xpath ="//*[@id=\"__BVID__51__BV_label_\"]")
    public static WebElement lbl_D_Main_Eco_PPS;

    @FindBy(xpath ="//*[@id=\"__BVID__51__BV_label_\"]")
    public  static WebElement lbl_D_Main_Eco2_PPS;

    public Data_Entry (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }

}
