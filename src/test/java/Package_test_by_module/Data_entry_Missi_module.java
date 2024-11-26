package Package_test_by_module;

import Package_Element.DataSet;
import Package_Element.Data_Entry;
import Package_Element.LoginPage;
import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Data_entry_Missi_module {

    static WebDriver driver;
    static DataSet dataset;
    static SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void beforeClass() throws IOException {

        //Chrome Browser
        System.setProperty("webdriver.chrome.driver", "C:\\CDver\\chromedriver-win64 (7)\\chromedriver-win64\\chromedriver" + ".exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //web link

//        driver.manage().deleteAllCookies();
//        driver.get("chrome://settings/clearBrowserData");
//        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
//        driver.get("http://172.16.1.141/missi_pps/login");
        driver.get("https://sqad-test.psa.gov.ph/");
//        driver.get("https://dev.psa.gov.ph/missi_pps/dps/login");

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("dataset.json"));
        dataset = gson.fromJson(reader, DataSet.class);

    }

    @Test(priority = 1, testName = "Login")
    public void testLogin1() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(60));


        eWait.until(ExpectedConditions.visibilityOf(login.btn_login_btn));
        login.btn_login_btn.click();

        int iteration = 1;
        for (DataSet credentials : dataset.test_credentialsPO) {
            login.tf_emailAdd.sendKeys(credentials.EMAIL);
            login.tf_pw.sendKeys(credentials.PW);
            login.btn_login.click();

            if (iteration >= 1 && iteration <= 2) {
                eWait.until(ExpectedConditions.visibilityOf(login.modal_error));
                softAssert.assertTrue(login.modal_error.isDisplayed(), "Missing Error Modal");
                softAssert.assertTrue(login.btn_errorOK.isDisplayed(), "Error button OK is missing");
                login.btn_errorOK.click();

            } else {

                eWait.until(ExpectedConditions.visibilityOfAllElements(login.lbl_email, login.lbl_pw));
                softAssert.assertTrue(login.lbl_email.isDisplayed(), "Email label is missing");
                softAssert.assertTrue(login.tf_emailAdd.isDisplayed(), "Missing email text field");
                System.out.println("required email? " + login.tf_emailAdd.getAttribute("required"));

                softAssert.assertTrue(login.lbl_pw.isDisplayed(), "Password label is missing");
                softAssert.assertTrue(login.tf_pw.isDisplayed(), "Missing password field");
                System.out.println("required password? " + login.tf_pw.getAttribute("required"));

                softAssert.assertTrue(login.btn_login.isDisplayed(), "Missing login button");
                login.btn_login.click();
            }
            iteration++;
        }
        softAssert.assertAll();
    }

    @Test(priority = 7, testName = "data_entry", enabled = true)
    public void data_entry() throws InterruptedException {

        Data_Entry data_entry = new Data_Entry(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(Data_Entry.lbl_Data_entry));
        Data_Entry.lbl_Data_entry.click();

        sleep(1000);
        Data_Entry.lbl_Missi.click();

        sleep(1000);
        Select Yr = new Select(Data_Entry.dd_Year);
        Yr.selectByIndex(1);

        Select Mth = new Select(Data_Entry.dd_Month);
        Mth.selectByIndex(4);

        sleep(1000);
        Data_Entry.txt_ecn.sendKeys(dataset.ECN_DATA_ENTRY_PO);

        sleep(1000);
        Data_Entry.btn_Submit.click();

//        sleep(1000);
//        wait.until(ExpectedConditions.visibilityOfAllElements(Package_Element.Data_Entry.lbl_Gen_info, Package_Element.Data_Entry.txt_ECN, Package_Element.Data_Entry.lbl_D_name, Package_Element.Data_Entry.txt_D_name, Package_Element.Data_Entry.lbl_D_Add,
//                Package_Element.Data_Entry.txt_D_Add, Package_Element.Data_Entry.lbl_D_TIN, Package_Element.Data_Entry.txt_D_TIN, Package_Element.Data_Entry.lbl_D_IND, Package_Element.Data_Entry.txt_D_IND, Package_Element.Data_Entry.lbl_D_Main_Eco));

        softAssert.assertTrue(Data_Entry.lbl_Gen_info.isDisplayed(), "Gen info label is missing");
        String ECN_lbl = Data_Entry.lbl_Gen_info.getText();
        System.out.println("System displays label " + ECN_lbl);

        softAssert.assertTrue(Data_Entry.txt_ECN.isDisplayed(), "Gen info text box is missing");
        String ECN_txt = Data_Entry.txt_ECN.getAttribute("value");
        System.out.println("Attribute " + ECN_txt);
        boolean isDisabled_ECN = Data_Entry.txt_ECN.getAttribute("disabled") != null && Data_Entry.txt_ECN.getAttribute("disabled").equals("true");
        if (isDisabled_ECN) {
            System.out.println("The ECN textbox is disabled.");
        } else {
            System.out.println("The ECN textbox is not disabled.");
        }

        softAssert.assertTrue(Data_Entry.lbl_D_name.isDisplayed(), "Business name label is missing");
        String B_name_lbl = Data_Entry.lbl_D_name.getText();
        System.out.println("System displays label " + B_name_lbl);

        softAssert.assertTrue(Data_Entry.txt_D_name.isDisplayed(), "Business name text box is missing");
        String B_name_txt = Data_Entry.txt_D_name.getAttribute("value");
        System.out.println("Attribute " + B_name_txt);

        boolean isDisabled_Dname = Data_Entry.txt_D_name.getAttribute("disabled") != null && Data_Entry.txt_D_name.getAttribute("disabled").equals("true");
        if (isDisabled_Dname) {
            System.out.println("The Business Name textbox is disabled.");
        } else {
            System.out.println("The Business Name textbox is not disabled.");
            Data_Entry.txt_D_name.sendKeys("Sample Text");
            Data_Entry.txt_D_name.clear();
            Data_Entry.txt_D_name.sendKeys("GOLDEN SEACRAFT MARINE CORP");
        }
        softAssert.assertTrue(Data_Entry.lbl_D_Add.isDisplayed(), "Business Address label is missing");
        String D_Add_lbl = Data_Entry.lbl_D_Add.getText();
        System.out.println("System displays label " + D_Add_lbl);

        softAssert.assertTrue(Data_Entry.txt_D_Add.isDisplayed(), "Business Address text box is missing");
        String D_add_txt = Data_Entry.txt_D_Add.getAttribute("value");
        System.out.println("Attribute " + D_add_txt);

        boolean isDisabled_Dadd = Data_Entry.txt_D_Add.getAttribute("disabled") != null && Data_Entry.txt_D_Add.getAttribute("disabled").equals("true");
        if (isDisabled_Dadd) {
            System.out.println("The Business Address textbox is disabled.");
        } else {
            System.out.println("The Business Address textbox is not disabled.");
            Data_Entry.txt_D_Add.sendKeys("Sample Text");
            Data_Entry.txt_D_Add.clear();
            Data_Entry.txt_D_Add.sendKeys("PUROK 4 BARANGAY 22");
        }

        softAssert.assertTrue(Data_Entry.lbl_D_TIN.isDisplayed(), "TIN label is missing");
        String D_tin_lbl = Data_Entry.lbl_D_TIN.getText();
        System.out.println("System displays label " + D_tin_lbl);

        softAssert.assertTrue(Data_Entry.txt_D_TIN.isDisplayed(), "TIN text box is missing");
        String D_tin_txt = Data_Entry.txt_D_TIN.getAttribute("value");
        System.out.println("Attribute " + D_tin_txt);

        boolean isDisabled_DTIN = Data_Entry.txt_D_TIN.getAttribute("disabled") != null && Data_Entry.txt_D_TIN.getAttribute("disabled").equals("true");
        if (isDisabled_DTIN) {
            System.out.println("The TIN textbox is disabled.");
        } else {
            System.out.println("The TIN textbox is not disabled.");
            Data_Entry.txt_D_TIN.sendKeys("Sample Text");
            Data_Entry.txt_D_TIN.clear();
            Data_Entry.txt_D_TIN.sendKeys("8701066");
        }
        softAssert.assertTrue(Data_Entry.lbl_D_IND.isDisplayed(), "IND label is missing");
        String D_IND_lbl = Data_Entry.lbl_D_IND.getText();
        System.out.println("System displays label " + D_IND_lbl);

        softAssert.assertTrue(Data_Entry.txt_D_IND.isDisplayed(), "IND text box is missing");
        String D_IND_txt = Data_Entry.txt_D_IND.getAttribute("value");
        System.out.println("Attribute " + D_IND_txt);

        boolean isDisabled_DIND = Data_Entry.txt_D_IND.getAttribute("disabled") != null && Data_Entry.txt_D_IND.getAttribute("disabled").equals("true");
        if (isDisabled_DIND) {
            System.out.println("The TIN textbox is disabled.");
        } else {
            System.out.println("The TIN textbox is not disabled.");
            Data_Entry.txt_D_IND.sendKeys("Sample Text");
            Data_Entry.txt_D_IND.clear();
            Data_Entry.txt_D_IND.sendKeys("C30111");
        }
//        softAssert.assertTrue(Data_Entry.lbl_D_Main_Eco.isDisplayed(), "Main Economic label is missing");
//        String D_Main_Eco_lbl = Data_Entry.lbl_D_Main_Eco.getText();
//        System.out.println("System displays label " + D_Main_Eco_lbl);
//
//        softAssert.assertTrue(Data_Entry.lbl_D_Main_Eco2.isDisplayed(), "Main Economic label is missing");
//        String D_Main_Eco2_lbl = Data_Entry.lbl_D_Main_Eco2.getText();
//        System.out.println("System displays label " + D_Main_Eco2_lbl);

        executor.executeScript("window.scrollBy(0, 500);");
        sleep(1000);
        softAssert.assertTrue(Data_Entry.txt_D_Main_Eco.isDisplayed(), "Main Economic text box is missing");
        String D_Main_txt = Data_Entry.txt_D_Main_Eco.getAttribute("value");
        System.out.println("Attribute " + D_Main_txt);

        boolean isDisabled_Main = Data_Entry.txt_D_Main_Eco.getAttribute("disabled") != null && Data_Entry.txt_D_Main_Eco.getAttribute("disabled").equals("true");
        if (isDisabled_Main) {
            System.out.println("The Main Economic (Principal) Activity textbox is disabled.");
        } else {
            System.out.println("The Main Economic (Principal) Activity textbox is not disabled.");
            Data_Entry.txt_D_Main_Eco.sendKeys("Sample Text");
            Data_Entry.txt_D_Main_Eco.clear();
            Data_Entry.txt_D_Main_Eco.sendKeys("SHIPREPAIR AND SHIPBUILDING");
        }

        System.out.println("____________________TABLE DISPLAY___________________");

        softAssert.assertTrue(Data_Entry.D_table.isDisplayed());
        String cell = Data_Entry.D_table.getText().replace("LN ITEM DESCRIPTION JULY 2022 AUGUST 2022 SEPTEMBER 2022 OCTOBER 2022 NOVEMBER 2022 DECEMBER 2022 JANUARY 2022 ", "LN\n ITEM\n DESCRIPTION\n JULY 2022\n AUGUST 2022\n SEPTEMBER 2022\n OCTOBER 2022\n NOVEMBER 2022\n DECEMBER 2022\n JANUARY 2023\n");
        System.out.print(" CELL TABLE " + "\n " + cell + "\n");

//        String LN1 = Package_Element.Data_Entry.T_01.getAttribute("value");
//        System.out.println("Attribute "+ LN1);
//
//        String I_TOT = Package_Element.Data_Entry.T_Total.getAttribute("value");
//        System.out.println("Attribute "+ I_TOT);

//        Package_Element.Data_Entry.T_Tot_i.click();

        boolean L1M1 = Data_Entry.T_1.getAttribute("disabled") != null && Data_Entry.T_1.getAttribute("disabled").equals("true");
        if (L1M1) {
            System.out.println("The Total employment JULY 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment JULY 2022 textbox is not disabled - FAILED.");
            Data_Entry.T_1.clear();
            Data_Entry.T_1.sendKeys("*(QWE");
            Data_Entry.T_1.clear();
            Data_Entry.T_1.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L1M2 = Data_Entry.T_2.getAttribute("disabled") != null && Data_Entry.T_2.getAttribute("disabled").equals("true");
        if (L1M2) {
            System.out.println("The Total employment August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment August 2022 textbox is not disabled - FAILED.");
            Data_Entry.T_2.clear();
            Data_Entry.T_2.sendKeys("*(QWE");
            Data_Entry.T_2.clear();
            Data_Entry.T_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L1M3 = Data_Entry.T_3.getAttribute("disabled") != null && Data_Entry.T_3.getAttribute("disabled").equals("true");
        if (L1M3) {
            System.out.println("The Total employment September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment September 2022 textbox is not disabled - FAILED.");
            Data_Entry.T_3.clear();
            Data_Entry.T_3.sendKeys("*(QWE");
            Data_Entry.T_3.clear();
            Data_Entry.T_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L1M4 = Data_Entry.T_4.getAttribute("disabled") != null && Data_Entry.T_4.getAttribute("disabled").equals("true");
        if (L1M4) {
            System.out.println("The Total employment October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment October 2022 textbox is not disabled - FAILED.");
            Data_Entry.T_4.clear();
            Data_Entry.T_4.sendKeys("*(QWE");
            Data_Entry.T_4.clear();
            Data_Entry.T_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L1M5 = Data_Entry.T_5.getAttribute("disabled") != null && Data_Entry.T_5.getAttribute("disabled").equals("true");
        if (L1M5) {
            System.out.println("The Total employment November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment November 2022 textbox is not disabled - FAILED.");
            Data_Entry.T_5.clear();
            Data_Entry.T_5.sendKeys("*(QWE");
            Data_Entry.T_5.clear();
            Data_Entry.T_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L1M6 = Data_Entry.T_6.getAttribute("disabled") != null && Data_Entry.T_6.getAttribute("disabled").equals("true");
        if (L1M6) {
            System.out.println("The Total employment December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment December 2022 textbox is not disabled - FAILED.");
            Data_Entry.T_6.clear();
            Data_Entry.T_6.sendKeys("*(QWE");
            Data_Entry.T_6.clear();
            Data_Entry.T_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L1M7 = Data_Entry.T_7.getAttribute("disabled") != null && Data_Entry.T_7.getAttribute("disabled").equals("true");
        if (L1M7) {
            System.out.println("The Total employment January 2023 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total employment January 2023 textbox is not disabled - FAILED.");
            Data_Entry.T_7.clear();
            Data_Entry.T_7.sendKeys("*(QWE");
            Data_Entry.T_7.clear();
            Data_Entry.T_7.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L2M1 = Data_Entry.W_1.getAttribute("disabled") != null && Data_Entry.W_1.getAttribute("disabled").equals("true");
        if (L2M1) {
            System.out.println("The Working owners and unpaid workers JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.W_1.clear();
            Data_Entry.W_1.sendKeys("*(QWE");
            Data_Entry.W_1.clear();
            Data_Entry.W_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Working owners and unpaid workers JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L2M2 = Data_Entry.W_2.getAttribute("disabled") != null && Data_Entry.W_2.getAttribute("disabled").equals("true");
        if (L2M2) {
            System.out.println("The Working owners and unpaid workers August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Working owners and unpaid workers August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.W_2.clear();
            Data_Entry.W_2.sendKeys("*(QWE");
            Data_Entry.W_2.clear();
            Data_Entry.W_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L2M3 = Data_Entry.W_3.getAttribute("disabled") != null && Data_Entry.W_3.getAttribute("disabled").equals("true");
        if (L2M3) {
            System.out.println("The Working owners and unpaid workers September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Working owners and unpaid workers September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.W_3.clear();
            Data_Entry.W_3.sendKeys("*(QWE");
            Data_Entry.W_3.clear();
            Data_Entry.W_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L2M4 = Data_Entry.W_4.getAttribute("disabled") != null && Data_Entry.W_4.getAttribute("disabled").equals("true");
        if (L2M4) {
            System.out.println("The Working owners and unpaid workers October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Working owners and unpaid workers October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.W_4.clear();
            Data_Entry.W_4.sendKeys("*(QWE");
            Data_Entry.W_4.clear();
            Data_Entry.W_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L2M5 = Data_Entry.W_5.getAttribute("disabled") != null && Data_Entry.W_5.getAttribute("disabled").equals("true");
        if (L2M5) {
            System.out.println("The Working owners and unpaid workers November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Working owners and unpaid workers November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.W_5.clear();
            Data_Entry.W_5.sendKeys("*(QWE");
            Data_Entry.W_5.clear();
            Data_Entry.W_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L2M6 = Data_Entry.W_6.getAttribute("disabled") != null && Data_Entry.W_6.getAttribute("disabled").equals("true");
        if (L2M6) {
            System.out.println("The Working owners and unpaid workers December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Working owners and unpaid workers December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.W_6.clear();
            Data_Entry.W_6.sendKeys("*(QWE");
            Data_Entry.W_6.clear();
            Data_Entry.W_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L2M7 = Data_Entry.W_7.getAttribute("disabled") != null && Data_Entry.W_7.getAttribute("disabled").equals("true");
        if (L2M7) {
            System.out.println("The Working owners and unpaid workers January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.W_7.clear();
            Data_Entry.W_7.sendKeys("*(QWE");
            Data_Entry.W_7.clear();
            Data_Entry.W_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Working owners and unpaid workers January 2023 textbox is not disabled and Numeric only - PASS.");


        }


        boolean L3M1 = Data_Entry.P_1.getAttribute("disabled") != null && Data_Entry.P_1.getAttribute("disabled").equals("true");
        if (L3M1) {
            System.out.println("The Paid employees JULY 2022 textbox is disabled - PASS.");
        } else {
            Data_Entry.P_1.clear();
            Data_Entry.P_1.sendKeys("*(QWE");
            Data_Entry.P_1.clear();
            Data_Entry.P_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Paid employees JULY 2022 textbox is not disabled - FAILED.");

        }

        boolean L3M2 = Data_Entry.P_2.getAttribute("disabled") != null && Data_Entry.P_2.getAttribute("disabled").equals("true");
        if (L3M2) {
            System.out.println("The Paid employees August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Paid employees August 2022 textbox is not disabled - FAILED.");
            Data_Entry.P_2.clear();
            Data_Entry.P_2.sendKeys("*(QWE");
            Data_Entry.P_2.clear();
            Data_Entry.P_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L3M3 = Data_Entry.P_3.getAttribute("disabled") != null && Data_Entry.P_3.getAttribute("disabled").equals("true");
        if (L3M3) {
            System.out.println("The Paid employees September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Paid employees September 2022 textbox is not disabled - FAILED.");
            Data_Entry.P_3.clear();
            Data_Entry.P_3.sendKeys("*(QWE");
            Data_Entry.P_3.clear();
            Data_Entry.P_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L3M4 = Data_Entry.P_4.getAttribute("disabled") != null && Data_Entry.P_4.getAttribute("disabled").equals("true");
        if (L3M4) {
            System.out.println("The Paid employees October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Paid employees October 2022 textbox is not disabled - FAILED.");
            Data_Entry.P_4.clear();
            Data_Entry.P_4.sendKeys("*(QWE");
            Data_Entry.P_4.clear();
            Data_Entry.P_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L3M5 = Data_Entry.P_5.getAttribute("disabled") != null && Data_Entry.P_5.getAttribute("disabled").equals("true");
        if (L3M5) {
            System.out.println("The Paid employees November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Paid employees November 2022 textbox is not disabled - FAILED.");
            Data_Entry.P_5.clear();
            Data_Entry.P_5.sendKeys("*(QWE");
            Data_Entry.P_5.clear();
            Data_Entry.P_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L3M6 = Data_Entry.P_6.getAttribute("disabled") != null && Data_Entry.P_6.getAttribute("disabled").equals("true");
        if (L3M6) {
            System.out.println("The Paid employees December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Paid employees December 2022 textbox is not disabled - FAILED.");
            Data_Entry.P_6.clear();
            Data_Entry.P_6.sendKeys("*(QWE");
            Data_Entry.P_6.clear();
            Data_Entry.P_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L3M7 = Data_Entry.P_7.getAttribute("disabled") != null && Data_Entry.P_7.getAttribute("disabled").equals("true");
        if (L3M7) {
            System.out.println("The Paid employees January 2023 textbox is disabled - PASS.");
        } else {
            Data_Entry.P_7.clear();
            Data_Entry.P_7.sendKeys("*(QWE");
            Data_Entry.P_7.clear();
            Data_Entry.P_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Paid employees January 2023 textbox is not disabled - FAILED.");

        }

        boolean L4M1 = Data_Entry.M_1.getAttribute("disabled") != null && Data_Entry.M_1.getAttribute("disabled").equals("true");
        if (L4M1) {
            System.out.println("The Managers and executives JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.M_1.clear();
            Data_Entry.M_1.sendKeys("*(QWE");
            Data_Entry.M_1.clear();
            Data_Entry.M_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Managers and executives JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L4M2 = Data_Entry.M_2.getAttribute("disabled") != null && Data_Entry.M_2.getAttribute("disabled").equals("true");
        if (L4M2) {
            System.out.println("The Managers and executives August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Managers and executives August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.M_2.clear();
            Data_Entry.M_2.sendKeys("*(QWE");
            Data_Entry.M_2.clear();
            Data_Entry.M_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L4M3 = Data_Entry.M_3.getAttribute("disabled") != null && Data_Entry.M_3.getAttribute("disabled").equals("true");
        if (L4M3) {
            System.out.println("The Managers and executives September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Managers and executives September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.M_3.clear();
            Data_Entry.M_3.sendKeys("*(QWE");
            Data_Entry.M_3.clear();
            Data_Entry.M_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L4M4 = Data_Entry.M_4.getAttribute("disabled") != null && Data_Entry.M_4.getAttribute("disabled").equals("true");
        if (L4M4) {
            System.out.println("The Managers and executives October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Managers and executives October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.M_4.clear();
            Data_Entry.M_4.sendKeys("*(QWE");
            Data_Entry.M_4.clear();
            Data_Entry.M_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L4M5 = Data_Entry.M_5.getAttribute("disabled") != null && Data_Entry.M_5.getAttribute("disabled").equals("true");
        if (L4M5) {
            System.out.println("The Managers and executives November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Managers and executives November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.M_5.clear();
            Data_Entry.M_5.sendKeys("*(QWE");
            Data_Entry.M_5.clear();
            Data_Entry.M_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L4M6 = Data_Entry.M_6.getAttribute("disabled") != null && Data_Entry.M_6.getAttribute("disabled").equals("true");
        if (L4M6) {
            System.out.println("The Managers and executives December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Managers and executives December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.M_6.clear();
            Data_Entry.M_6.sendKeys("*(QWE");
            Data_Entry.M_6.clear();
            Data_Entry.M_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L4M7 = Data_Entry.M_7.getAttribute("disabled") != null && Data_Entry.M_7.getAttribute("disabled").equals("true");
        if (L4M7) {
            System.out.println("The Managers and executives January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.M_7.clear();
            Data_Entry.M_7.sendKeys("*(QWE");
            Data_Entry.M_7.clear();
            Data_Entry.M_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Managers and executives January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L5M1 = Data_Entry.PW_1.getAttribute("disabled") != null && Data_Entry.PW_1.getAttribute("disabled").equals("true");
        if (L5M1) {
            System.out.println("The Production workers JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.PW_1.clear();
            Data_Entry.PW_1.sendKeys("*(QWE");
            Data_Entry.PW_1.clear();
            Data_Entry.PW_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Production workers JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L5M2 = Data_Entry.PW_2.getAttribute("disabled") != null && Data_Entry.PW_2.getAttribute("disabled").equals("true");
        if (L5M2) {
            System.out.println("The Production workers August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Production workers August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.PW_2.clear();
            Data_Entry.PW_2.sendKeys("*(QWE");
            Data_Entry.PW_2.clear();
            Data_Entry.PW_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L5M3 = Data_Entry.PW_3.getAttribute("disabled") != null && Data_Entry.PW_3.getAttribute("disabled").equals("true");
        if (L5M3) {
            System.out.println("The Production workers September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Production workers September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.PW_3.clear();
            Data_Entry.PW_3.sendKeys("*(QWE");
            Data_Entry.PW_3.clear();
            Data_Entry.PW_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L5M4 = Data_Entry.PW_4.getAttribute("disabled") != null && Data_Entry.PW_4.getAttribute("disabled").equals("true");
        if (L5M4) {
            System.out.println("The Production workers October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Production workers October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.PW_4.clear();
            Data_Entry.PW_4.sendKeys("*(QWE");
            Data_Entry.PW_4.clear();
            Data_Entry.PW_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L5M5 = Data_Entry.PW_5.getAttribute("disabled") != null && Data_Entry.PW_5.getAttribute("disabled").equals("true");
        if (L5M5) {
            System.out.println("The Production workers November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Production workers November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.PW_5.clear();
            Data_Entry.PW_5.sendKeys("*(QWE");
            Data_Entry.PW_5.clear();
            Data_Entry.PW_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L5M6 = Data_Entry.PW_6.getAttribute("disabled") != null && Data_Entry.PW_6.getAttribute("disabled").equals("true");
        if (L5M6) {
            System.out.println("The Production workers December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Production workers December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.PW_6.clear();
            Data_Entry.PW_6.sendKeys("*(QWE");
            Data_Entry.PW_6.clear();
            Data_Entry.PW_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L5M7 = Data_Entry.PW_7.getAttribute("disabled") != null && Data_Entry.PW_7.getAttribute("disabled").equals("true");
        if (L5M7) {
            System.out.println("The Production workers January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.PW_7.clear();
            Data_Entry.PW_7.sendKeys("*(QWE");
            Data_Entry.PW_7.clear();
            Data_Entry.PW_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Production workers January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L6M1 = Data_Entry.OT_1.getAttribute("disabled") != null && Data_Entry.OT_1.getAttribute("disabled").equals("true");
        if (L6M1) {
            System.out.println("The Other employees JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.OT_1.clear();
            Data_Entry.OT_1.sendKeys("*(QWE");
            Data_Entry.OT_1.clear();
            Data_Entry.OT_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Other employees JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L6M2 = Data_Entry.OT_2.getAttribute("disabled") != null && Data_Entry.OT_2.getAttribute("disabled").equals("true");
        if (L6M2) {
            System.out.println("The Other employees August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other employees August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OT_2.clear();
            Data_Entry.OT_2.sendKeys("*(QWE");
            Data_Entry.OT_2.clear();
            Data_Entry.OT_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L6M3 = Data_Entry.OT_3.getAttribute("disabled") != null && Data_Entry.OT_3.getAttribute("disabled").equals("true");
        if (L6M3) {
            System.out.println("The Other employees September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other employees September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OT_3.clear();
            Data_Entry.OT_3.sendKeys("*(QWE");
            Data_Entry.OT_3.clear();
            Data_Entry.OT_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L6M4 = Data_Entry.OT_4.getAttribute("disabled") != null && Data_Entry.OT_4.getAttribute("disabled").equals("true");
        if (L6M4) {
            System.out.println("The Other employees October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other employees October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OT_4.clear();
            Data_Entry.OT_4.sendKeys("*(QWE");
            Data_Entry.OT_4.clear();
            Data_Entry.OT_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L6M5 = Data_Entry.OT_5.getAttribute("disabled") != null && Data_Entry.OT_5.getAttribute("disabled").equals("true");
        if (L6M5) {
            System.out.println("The Other employees November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other employees November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OT_5.clear();
            Data_Entry.OT_5.sendKeys("*(QWE");
            Data_Entry.OT_5.clear();
            Data_Entry.OT_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L6M6 = Data_Entry.OT_6.getAttribute("disabled") != null && Data_Entry.OT_6.getAttribute("disabled").equals("true");
        if (L6M6) {
            System.out.println("The Other employees December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other employees December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OT_6.clear();
            Data_Entry.OT_6.sendKeys("*(QWE");
            Data_Entry.OT_6.clear();
            Data_Entry.OT_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L6M7 = Data_Entry.OT_7.getAttribute("disabled") != null && Data_Entry.OT_7.getAttribute("disabled").equals("true");
        if (L6M7) {
            System.out.println("The Other employees January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.OT_7.clear();
            Data_Entry.OT_7.sendKeys("*(QWE");
            Data_Entry.OT_7.clear();
            Data_Entry.OT_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Other employees January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L7M1 = Data_Entry.TC_1.getAttribute("disabled") != null && Data_Entry.TC_1.getAttribute("disabled").equals("true");
        if (L7M1) {
            System.out.println("The Total compensation (PHP) JULY 2022 textbox is disabled - PASS.");
        } else {
            Data_Entry.TC_1.clear();
            Data_Entry.TC_1.sendKeys("*(QWE");
            Data_Entry.TC_1.clear();
            Data_Entry.TC_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total compensation (PHP) JULY 2022 textbox is not disabled - FAILED.");

        }

        boolean L7M2 = Data_Entry.TC_2.getAttribute("disabled") != null && Data_Entry.TC_2.getAttribute("disabled").equals("true");
        if (L7M2) {
            System.out.println("The Total compensation (PHP) August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total compensation (PHP) August 2022 textbox is not disabled - FAILED.");
            Data_Entry.TC_2.clear();
            Data_Entry.TC_2.sendKeys("*(QWE");
            Data_Entry.TC_2.clear();
            Data_Entry.TC_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L7M3 = Data_Entry.TC_3.getAttribute("disabled") != null && Data_Entry.TC_3.getAttribute("disabled").equals("true");
        if (L7M3) {
            System.out.println("The Total compensation (PHP) September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total compensation (PHP) September 2022 textbox is not disabled - FAILED.");
            Data_Entry.TC_3.clear();
            Data_Entry.TC_3.sendKeys("*(QWE");
            Data_Entry.TC_3.clear();
            Data_Entry.TC_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L7M4 = Data_Entry.TC_4.getAttribute("disabled") != null && Data_Entry.TC_4.getAttribute("disabled").equals("true");
        if (L7M4) {
            System.out.println("The Total compensation (PHP) October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total compensation (PHP) October 2022 textbox is not disabled - FAILED.");
            Data_Entry.TC_4.clear();
            Data_Entry.TC_4.sendKeys("*(QWE");
            Data_Entry.TC_4.clear();
            Data_Entry.TC_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L7M5 = Data_Entry.TC_5.getAttribute("disabled") != null && Data_Entry.TC_5.getAttribute("disabled").equals("true");
        if (L7M5) {
            System.out.println("The Total compensation (PHP) November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total compensation (PHP) November 2022 textbox is not disabled - FAILED.");
            Data_Entry.TC_5.clear();
            Data_Entry.TC_5.sendKeys("*(QWE");
            Data_Entry.TC_5.clear();
            Data_Entry.TC_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L7M6 = Data_Entry.TC_6.getAttribute("disabled") != null && Data_Entry.TC_6.getAttribute("disabled").equals("true");
        if (L7M6) {
            System.out.println("The Total compensation (PHP) December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total compensation (PHP) December 2022 textbox is not disabled - FAILED.");
            Data_Entry.TC_6.clear();
            Data_Entry.TC_6.sendKeys("*(QWE");
            Data_Entry.TC_6.clear();
            Data_Entry.TC_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L7M7 = Data_Entry.TC_7.getAttribute("disabled") != null && Data_Entry.TC_7.getAttribute("disabled").equals("true");
        if (L7M7) {
            System.out.println("The Total compensation (PHP) January 2023 textbox is disabled - PASS.");
        } else {
            Data_Entry.TC_7.clear();
            Data_Entry.TC_7.sendKeys("*(QWE");
            Data_Entry.TC_7.clear();
            Data_Entry.TC_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total compensation (PHP) January 2023 textbox is not disabled - FAILED.");

        }


        boolean L8M1 = Data_Entry.TS_1.getAttribute("disabled") != null && Data_Entry.TS_1.getAttribute("disabled").equals("true");
        if (L8M1) {
            System.out.println("The Salaries and wages of other employees JULY 2022 textbox is disabled - PASS.");
        } else {
            Data_Entry.TS_1.clear();
            Data_Entry.TS_1.sendKeys("*(QWE");
            Data_Entry.TS_1.clear();
            Data_Entry.TS_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries and wages of other employees JULY 2022 textbox is not disabled - FAILED.");

        }

        boolean L8M2 = Data_Entry.TS_2.getAttribute("disabled") != null && Data_Entry.TS_2.getAttribute("disabled").equals("true");
        if (L8M2) {
            System.out.println("The Salaries and wages of other employees August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Salaries and wages of other employees August 2022 textbox is not disabled - FAILED.");
            Data_Entry.TS_2.clear();
            Data_Entry.TS_2.sendKeys("*(QWE");
            Data_Entry.TS_2.clear();
            Data_Entry.TS_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L8M3 = Data_Entry.TS_3.getAttribute("disabled") != null && Data_Entry.TS_3.getAttribute("disabled").equals("true");
        if (L8M3) {
            System.out.println("The Salaries and wages of other employees September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Salaries and wages of other employees September 2022 textbox is not disabled - FAILED.");
            Data_Entry.TS_3.clear();
            Data_Entry.TS_3.sendKeys("*(QWE");
            Data_Entry.TS_3.clear();
            Data_Entry.TS_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L8M4 = Data_Entry.TS_4.getAttribute("disabled") != null && Data_Entry.TS_4.getAttribute("disabled").equals("true");
        if (L8M4) {
            System.out.println("The Salaries and wages of other employees October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Salaries and wages of other employees October 2022 textbox is not disabled - FAILED.");
            Data_Entry.TS_4.clear();
            Data_Entry.TS_4.sendKeys("*(QWE");
            Data_Entry.TS_4.clear();
            Data_Entry.TS_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L8M5 = Data_Entry.TS_5.getAttribute("disabled") != null && Data_Entry.TS_5.getAttribute("disabled").equals("true");
        if (L8M5) {
            System.out.println("The Salaries and wages of other employees November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Salaries and wages of other employees November 2022 textbox is not disabled - FAILED.");
            Data_Entry.TS_5.clear();
            Data_Entry.TS_5.sendKeys("*(QWE");
            Data_Entry.TS_5.clear();
            Data_Entry.TS_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L8M6 = Data_Entry.TS_6.getAttribute("disabled") != null && Data_Entry.TS_6.getAttribute("disabled").equals("true");
        if (L8M6) {
            System.out.println("The Salaries and wages of other employees December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Salaries and wages of other employees December 2022 textbox is not disabled - FAILED.");
            Data_Entry.TS_6.clear();
            Data_Entry.TS_6.sendKeys("*(QWE");
            Data_Entry.TS_6.clear();
            Data_Entry.TS_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L8M7 = Data_Entry.TS_7.getAttribute("disabled") != null && Data_Entry.TS_7.getAttribute("disabled").equals("true");
        if (L8M7) {
            System.out.println("The Salaries and wages of other employees January 2023 textbox is disabled - PASS.");
        } else {
            Data_Entry.TS_7.clear();
            Data_Entry.TS_7.sendKeys("*(QWE");
            Data_Entry.TS_7.clear();
            Data_Entry.TS_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries and wages of other employees January 2023 textbox is not disabled - FAILED.");

        }


        boolean L9M1 = Data_Entry.SM_1.getAttribute("disabled") != null && Data_Entry.SM_1.getAttribute("disabled").equals("true");
        if (L9M1) {
            System.out.println("The Salaries of managers and executives JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SM_1.clear();
            Data_Entry.SM_1.sendKeys("*(QWE");
            Data_Entry.SM_1.clear();
            Data_Entry.SM_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries of managers and executives JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L9M2 = Data_Entry.SM_2.getAttribute("disabled") != null && Data_Entry.SM_2.getAttribute("disabled").equals("true");
        if (L9M2) {
            System.out.println("The Production workers August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Production workers August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SM_2.clear();
            Data_Entry.SM_2.sendKeys("*(QWE");
            Data_Entry.SM_2.clear();
            Data_Entry.SM_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L9M3 = Data_Entry.SM_3.getAttribute("disabled") != null && Data_Entry.SM_3.getAttribute("disabled").equals("true");
        if (L9M3) {
            System.out.println("The Salaries of managers and executives September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries of managers and executives September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SM_3.clear();
            Data_Entry.SM_3.sendKeys("*(QWE");
            Data_Entry.SM_3.clear();
            Data_Entry.SM_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L9M4 = Data_Entry.SM_4.getAttribute("disabled") != null && Data_Entry.SM_4.getAttribute("disabled").equals("true");
        if (L9M4) {
            System.out.println("The Salaries of managers and executives October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries of managers and executives October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SM_4.clear();
            Data_Entry.SM_4.sendKeys("*(QWE");
            Data_Entry.SM_4.clear();
            Data_Entry.SM_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L9M5 = Data_Entry.SM_5.getAttribute("disabled") != null && Data_Entry.SM_5.getAttribute("disabled").equals("true");
        if (L9M5) {
            System.out.println("The Salaries of managers and executives November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries of managers and executives November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SM_5.clear();
            Data_Entry.SM_5.sendKeys("*(QWE");
            Data_Entry.SM_5.clear();
            Data_Entry.SM_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L9M6 = Data_Entry.SM_6.getAttribute("disabled") != null && Data_Entry.SM_6.getAttribute("disabled").equals("true");
        if (L9M6) {
            System.out.println("The Salaries of managers and executives December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries of managers and executives December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SM_6.clear();
            Data_Entry.SM_6.sendKeys("*(QWE");
            Data_Entry.SM_6.clear();
            Data_Entry.SM_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L9M7 = Data_Entry.SM_7.getAttribute("disabled") != null && Data_Entry.SM_7.getAttribute("disabled").equals("true");
        if (L9M7) {
            System.out.println("The Salaries of managers and executives January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SM_7.clear();
            Data_Entry.SM_7.sendKeys("*(QWE");
            Data_Entry.SM_7.clear();
            Data_Entry.SM_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries of managers and executives January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L10M1 = Data_Entry.SW_1.getAttribute("disabled") != null && Data_Entry.SW_1.getAttribute("disabled").equals("true");
        if (L10M1) {
            System.out.println("The Salaries and wages of production workers JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SW_1.clear();
            Data_Entry.SW_1.sendKeys("*(QWE");
            Data_Entry.SW_1.clear();
            Data_Entry.SW_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries and wages of production workers JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L10M2 = Data_Entry.SW_2.getAttribute("disabled") != null && Data_Entry.SW_2.getAttribute("disabled").equals("true");
        if (L10M2) {
            System.out.println("The Salaries and wages of production workers August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of production workers August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SW_2.clear();
            Data_Entry.SW_2.sendKeys("*(QWE");
            Data_Entry.SW_2.clear();
            Data_Entry.SW_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L10M3 = Data_Entry.SW_3.getAttribute("disabled") != null && Data_Entry.SW_3.getAttribute("disabled").equals("true");
        if (L10M3) {
            System.out.println("The Salaries and wages of production workers September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of production workers September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SW_3.clear();
            Data_Entry.SW_3.sendKeys("*(QWE");
            Data_Entry.SW_3.clear();
            Data_Entry.SW_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L10M4 = Data_Entry.SW_4.getAttribute("disabled") != null && Data_Entry.SW_4.getAttribute("disabled").equals("true");
        if (L10M4) {
            System.out.println("The Salaries and wages of production workers October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of production workers October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SW_4.clear();
            Data_Entry.SW_4.sendKeys("*(QWE");
            Data_Entry.SW_4.clear();
            Data_Entry.SW_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L10M5 = Data_Entry.SW_5.getAttribute("disabled") != null && Data_Entry.SW_5.getAttribute("disabled").equals("true");
        if (L10M5) {
            System.out.println("The Salaries and wages of production workers November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of production workers November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SW_5.clear();
            Data_Entry.SW_5.sendKeys("*(QWE");
            Data_Entry.SW_5.clear();
            Data_Entry.SW_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L10M6 = Data_Entry.SW_6.getAttribute("disabled") != null && Data_Entry.SW_6.getAttribute("disabled").equals("true");
        if (L10M6) {
            System.out.println("The Salaries and wages of production workers December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of production workers December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SW_6.clear();
            Data_Entry.SW_6.sendKeys("*(QWE");
            Data_Entry.SW_6.clear();
            Data_Entry.SW_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L10M7 = Data_Entry.SW_7.getAttribute("disabled") != null && Data_Entry.SW_7.getAttribute("disabled").equals("true");
        if (L10M7) {
            System.out.println("The Salaries and wages of production workers January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SW_7.clear();
            Data_Entry.SW_7.sendKeys("*(QWE");
            Data_Entry.SW_7.clear();
            Data_Entry.SW_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries and wages of production workers January 2023 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L11M1 = Data_Entry.SWE_1.getAttribute("disabled") != null && Data_Entry.SWE_1.getAttribute("disabled").equals("true");
        if (L11M1) {
            System.out.println("The Salaries and wages of other employees JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SWE_1.clear();
            Data_Entry.SWE_1.sendKeys("*(QWE");
            Data_Entry.SWE_1.clear();
            Data_Entry.SWE_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries and wages of other employees JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L11M2 = Data_Entry.SWE_2.getAttribute("disabled") != null && Data_Entry.SWE_2.getAttribute("disabled").equals("true");
        if (L11M2) {
            System.out.println("The Salaries and wages of other employees August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of other employees August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SWE_2.clear();
            Data_Entry.SWE_2.sendKeys("*(QWE");
            Data_Entry.SWE_2.clear();
            Data_Entry.SWE_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L11M3 = Data_Entry.SWE_3.getAttribute("disabled") != null && Data_Entry.SWE_3.getAttribute("disabled").equals("true");
        if (L11M3) {
            System.out.println("The Salaries and wages of other employees September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of other employees September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SWE_3.clear();
            Data_Entry.SWE_3.sendKeys("*(QWE");
            Data_Entry.SWE_3.clear();
            Data_Entry.SWE_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L11M4 = Data_Entry.SWE_4.getAttribute("disabled") != null && Data_Entry.SWE_4.getAttribute("disabled").equals("true");
        if (L11M4) {
            System.out.println("The Salaries and wages of other employees October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of other employees October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SWE_4.clear();
            Data_Entry.SWE_4.sendKeys("*(QWE");
            Data_Entry.SWE_4.clear();
            Data_Entry.SWE_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L11M5 = Data_Entry.SWE_5.getAttribute("disabled") != null && Data_Entry.SWE_5.getAttribute("disabled").equals("true");
        if (L11M5) {
            System.out.println("The Salaries and wages of other employees November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of other employees November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SWE_5.clear();
            Data_Entry.SWE_5.sendKeys("*(QWE");
            Data_Entry.SWE_5.clear();
            Data_Entry.SWE_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L11M6 = Data_Entry.SWE_6.getAttribute("disabled") != null && Data_Entry.SWE_6.getAttribute("disabled").equals("true");
        if (L11M6) {
            System.out.println("The Salaries and wages of other employees December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Salaries and wages of other employees December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SWE_6.clear();
            Data_Entry.SWE_6.sendKeys("*(QWE");
            Data_Entry.SWE_6.clear();
            Data_Entry.SWE_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L11M7 = Data_Entry.SWE_7.getAttribute("disabled") != null && Data_Entry.SWE_7.getAttribute("disabled").equals("true");
        if (L11M7) {
            System.out.println("The Salaries and wages of other employees January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SWE_7.clear();
            Data_Entry.SWE_7.sendKeys("*(QWE");
            Data_Entry.SWE_7.clear();
            Data_Entry.SWE_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Salaries and wages of other employees January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L12M1 = Data_Entry.TE_1.getAttribute("disabled") != null && Data_Entry.TE_1.getAttribute("disabled").equals("true");
        if (L12M1) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.TE_1.clear();
            Data_Entry.TE_1.sendKeys("*(QWE");
            Data_Entry.TE_1.clear();
            Data_Entry.TE_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L12M2 = Data_Entry.TE_2.getAttribute("disabled") != null && Data_Entry.TE_2.getAttribute("disabled").equals("true");
        if (L12M2) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.TE_2.clear();
            Data_Entry.TE_2.sendKeys("*(QWE");
            Data_Entry.TE_2.clear();
            Data_Entry.TE_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L12M3 = Data_Entry.TE_3.getAttribute("disabled") != null && Data_Entry.TE_3.getAttribute("disabled").equals("true");
        if (L12M3) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.TE_3.clear();
            Data_Entry.TE_3.sendKeys("*(QWE");
            Data_Entry.TE_3.clear();
            Data_Entry.TE_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L12M4 = Data_Entry.TE_4.getAttribute("disabled") != null && Data_Entry.TE_4.getAttribute("disabled").equals("true");
        if (L12M4) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.TE_4.clear();
            Data_Entry.TE_4.sendKeys("*(QWE");
            Data_Entry.TE_4.clear();
            Data_Entry.TE_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L12M5 = Data_Entry.TE_5.getAttribute("disabled") != null && Data_Entry.TE_5.getAttribute("disabled").equals("true");
        if (L12M5) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.TE_5.clear();
            Data_Entry.TE_5.sendKeys("*(QWE");
            Data_Entry.TE_5.clear();
            Data_Entry.TE_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L12M6 = Data_Entry.TE_6.getAttribute("disabled") != null && Data_Entry.TE_6.getAttribute("disabled").equals("true");
        if (L12M6) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.TE_6.clear();
            Data_Entry.TE_6.sendKeys("*(QWE");
            Data_Entry.TE_6.clear();
            Data_Entry.TE_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L12M7 = Data_Entry.TE_7.getAttribute("disabled") != null && Data_Entry.TE_7.getAttribute("disabled").equals("true");
        if (L12M7) {
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.TE_7.clear();
            Data_Entry.TE_7.sendKeys("*(QWE");
            Data_Entry.TE_7.clear();
            Data_Entry.TE_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total employer's contribution to SSS/GSIS, ECC, etc January 2023 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L13M1 = Data_Entry.TVP_1.getAttribute("disabled") != null && Data_Entry.TVP_1.getAttribute("disabled").equals("true");
        if (L13M1) {
            System.out.println("The Total value of production (PHP) JULY 2022 textbox is disabled - PASS - PASS.");
        } else {
            Data_Entry.TVP_1.clear();
            Data_Entry.TVP_1.sendKeys("*(QWE");
            Data_Entry.TVP_1.clear();
            Data_Entry.TVP_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total value of production (PHP) JULY 2022 textbox is not disabled - FAILED.");

        }

        boolean L13M2 = Data_Entry.TVP_2.getAttribute("disabled") != null && Data_Entry.TVP_2.getAttribute("disabled").equals("true");
        if (L13M2) {
            System.out.println("The Total value of production (PHP) August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total value of production (PHP) August 2022 textbox is not disabled - FAILED.");
            Data_Entry.TVP_2.clear();
            Data_Entry.TVP_2.sendKeys("*(QWE");
            Data_Entry.TVP_2.clear();
            Data_Entry.TVP_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L13M3 = Data_Entry.TVP_3.getAttribute("disabled") != null && Data_Entry.TVP_3.getAttribute("disabled").equals("true");
        if (L13M3) {
            System.out.println("The Total value of production (PHP) September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total value of production (PHP) September 2022 textbox is not disabled - FAILED.");
            Data_Entry.TVP_3.clear();
            Data_Entry.TVP_3.sendKeys("*(QWE");
            Data_Entry.TVP_3.clear();
            Data_Entry.TVP_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L13M4 = Data_Entry.TVP_4.getAttribute("disabled") != null && Data_Entry.TVP_4.getAttribute("disabled").equals("true");
        if (L13M4) {
            System.out.println("The Total value of production (PHP) October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total value of production (PHP) October 2022 textbox is not disabled - FAILED.");
            Data_Entry.TVP_4.clear();
            Data_Entry.TVP_4.sendKeys("*(QWE");
            Data_Entry.TVP_4.clear();
            Data_Entry.TVP_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L13M5 = Data_Entry.TVP_5.getAttribute("disabled") != null && Data_Entry.TVP_5.getAttribute("disabled").equals("true");
        if (L13M5) {
            System.out.println("The Total value of production (PHP) November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total value of production (PHP) November 2022 textbox is not disabled - FAILED.");
            Data_Entry.TVP_5.clear();
            Data_Entry.TVP_5.sendKeys("*(QWE");
            Data_Entry.TVP_5.clear();
            Data_Entry.TVP_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L13M6 = Data_Entry.TVP_6.getAttribute("disabled") != null && Data_Entry.TVP_6.getAttribute("disabled").equals("true");
        if (L13M6) {
            System.out.println("The Total value of production (PHP) December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total value of production (PHP) December 2022 textbox is not disabled - FAILED.");
            Data_Entry.TVP_6.clear();
            Data_Entry.TVP_6.sendKeys("*(QWE");
            Data_Entry.TVP_6.clear();
            Data_Entry.TVP_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L13M7 = Data_Entry.TVP_7.getAttribute("disabled") != null && Data_Entry.TVP_7.getAttribute("disabled").equals("true");
        if (L13M7) {
            System.out.println("The Total value of production (PHP) January 2023 textbox is disabled - PASS.");
        } else {
            Data_Entry.TVP_7.clear();
            Data_Entry.TVP_7.sendKeys("*(QWE");
            Data_Entry.TVP_7.clear();
            Data_Entry.TVP_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total value of production (PHP) January 2023 textbox is not disabled - FAILED.");

        }


        boolean L14M1 = Data_Entry.VP_1.getAttribute("disabled") != null && Data_Entry.VP_1.getAttribute("disabled").equals("true");
        if (L14M1) {
            System.out.println("The Value of production for domestic market JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.VP_1.clear();
            Data_Entry.VP_1.sendKeys("*(QWE");
            Data_Entry.VP_1.clear();
            Data_Entry.VP_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Value of production for domestic market JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L14M2 = Data_Entry.VP_2.getAttribute("disabled") != null && Data_Entry.VP_2.getAttribute("disabled").equals("true");
        if (L14M2) {
            System.out.println("The Value of production for domestic market August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for domestic market August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VP_2.clear();
            Data_Entry.VP_2.sendKeys("*(QWE");
            Data_Entry.VP_2.clear();
            Data_Entry.VP_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L14M3 = Data_Entry.VP_3.getAttribute("disabled") != null && Data_Entry.VP_3.getAttribute("disabled").equals("true");
        if (L14M3) {
            System.out.println("The Value of production for domestic market September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for domestic market September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VP_3.clear();
            Data_Entry.VP_3.sendKeys("*(QWE");
            Data_Entry.VP_3.clear();
            Data_Entry.VP_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L14M4 = Data_Entry.VP_4.getAttribute("disabled") != null && Data_Entry.VP_4.getAttribute("disabled").equals("true");
        if (L14M4) {
            System.out.println("The Value of production for domestic market October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for domestic market October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VP_4.clear();
            Data_Entry.VP_4.sendKeys("*(QWE");
            Data_Entry.VP_4.clear();
            Data_Entry.VP_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L14M5 = Data_Entry.VP_5.getAttribute("disabled") != null && Data_Entry.VP_5.getAttribute("disabled").equals("true");
        if (L14M5) {
            System.out.println("The Value of production for domestic market November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for domestic market November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VP_5.clear();
            Data_Entry.VP_5.sendKeys("*(QWE");
            Data_Entry.VP_5.clear();
            Data_Entry.VP_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L14M6 = Data_Entry.VP_6.getAttribute("disabled") != null && Data_Entry.VP_6.getAttribute("disabled").equals("true");
        if (L14M6) {
            System.out.println("The Value of production for domestic market December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for domestic market December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VP_6.clear();
            Data_Entry.VP_6.sendKeys("*(QWE");
            Data_Entry.VP_6.clear();
            Data_Entry.VP_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L14M7 = Data_Entry.VP_7.getAttribute("disabled") != null && Data_Entry.VP_7.getAttribute("disabled").equals("true");
        if (L14M7) {
            System.out.println("The Value of production for domestic market January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.VP_7.clear();
            Data_Entry.VP_7.sendKeys("*(QWE");
            Data_Entry.VP_7.clear();
            Data_Entry.VP_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Value of production for domestic market January 2023 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L15M1 = Data_Entry.VPDS_1.getAttribute("disabled") != null && Data_Entry.VPDS_1.getAttribute("disabled").equals("true");
        if (L15M1) {
            System.out.println("The Value of production for direct exports/sold to exporters JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.VPDS_1.clear();
            Data_Entry.VPDS_1.sendKeys("*(QWE");
            Data_Entry.VPDS_1.clear();
            Data_Entry.VPDS_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Value of production for direct exports/sold to exporters JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L15M2 = Data_Entry.VPDS_2.getAttribute("disabled") != null && Data_Entry.VPDS_2.getAttribute("disabled").equals("true");
        if (L15M2) {
            System.out.println("The Value of production for direct exports/sold to exporters August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for direct exports/sold to exporters August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VPDS_2.clear();
            Data_Entry.VPDS_2.sendKeys("*(QWE");
            Data_Entry.VPDS_2.clear();
            Data_Entry.VPDS_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L15M3 = Data_Entry.VPDS_3.getAttribute("disabled") != null && Data_Entry.VPDS_3.getAttribute("disabled").equals("true");
        if (L15M3) {
            System.out.println("The Value of production for direct exports/sold to exporters September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for direct exports/sold to exporters September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VPDS_3.clear();
            Data_Entry.VPDS_3.sendKeys("*(QWE");
            Data_Entry.VPDS_3.clear();
            Data_Entry.VPDS_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L15M4 = Data_Entry.VPDS_4.getAttribute("disabled") != null && Data_Entry.VPDS_4.getAttribute("disabled").equals("true");
        if (L15M4) {
            System.out.println("The Value of production for direct exports/sold to exporters October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for direct exports/sold to exporters October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VPDS_4.clear();
            Data_Entry.VPDS_4.sendKeys("*(QWE");
            Data_Entry.VPDS_4.clear();
            Data_Entry.VPDS_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L15M5 = Data_Entry.VPDS_5.getAttribute("disabled") != null && Data_Entry.VPDS_5.getAttribute("disabled").equals("true");
        if (L15M5) {
            System.out.println("The Value of production for direct exports/sold to exporters November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for direct exports/sold to exporters November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VPDS_5.clear();
            Data_Entry.VPDS_5.sendKeys("*(QWE");
            Data_Entry.VPDS_5.clear();
            Data_Entry.VPDS_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L15M6 = Data_Entry.VPDS_6.getAttribute("disabled") != null && Data_Entry.VPDS_6.getAttribute("disabled").equals("true");
        if (L15M6) {
            System.out.println("The Value of production for direct exports/sold to exporters December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Value of production for direct exports/sold to exporters December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.VPDS_6.clear();
            Data_Entry.VPDS_6.sendKeys("*(QWE");
            Data_Entry.VPDS_6.clear();
            Data_Entry.VPDS_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L15M7 = Data_Entry.VPDS_7.getAttribute("disabled") != null && Data_Entry.VPDS_7.getAttribute("disabled").equals("true");
        if (L15M7) {
            System.out.println("The Value of production for direct exports/sold to exporters January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.VPDS_7.clear();
            Data_Entry.VPDS_7.sendKeys("*(QWE");
            Data_Entry.VPDS_7.clear();
            Data_Entry.VPDS_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Value of production for direct exports/sold to exporters January 2023 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L18M1 = Data_Entry.TR_1.getAttribute("disabled") != null && Data_Entry.TR_1.getAttribute("disabled").equals("true");
        if (L18M1) {
            System.out.println("The Total revenue/sales (PHP) JULY 2022 textbox is disabled - PASS.");
        } else {
            Data_Entry.TR_1.clear();
            Data_Entry.TR_1.sendKeys("*(QWE");
            Data_Entry.TR_1.clear();
            Data_Entry.TR_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total revenue/sales (PHP) JULY 2022 textbox is not disabled - FAILED.");

        }

        boolean L18M2 = Data_Entry.TR_2.getAttribute("disabled") != null && Data_Entry.TR_2.getAttribute("disabled").equals("true");
        if (L18M2) {
            System.out.println("The Total revenue/sales (PHP) August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total revenue/sales (PHP) August 2022 textbox is not disabled - FAILED.");
            Data_Entry.TR_2.clear();
            Data_Entry.TR_2.sendKeys("*(QWE");
            Data_Entry.TR_2.clear();
            Data_Entry.TR_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L18M3 = Data_Entry.TR_3.getAttribute("disabled") != null && Data_Entry.TR_3.getAttribute("disabled").equals("true");
        if (L18M3) {
            System.out.println("The Total revenue/sales (PHP) September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total revenue/sales (PHP) September 2022 textbox is not disabled - FAILED.");
            Data_Entry.TR_3.clear();
            Data_Entry.TR_3.sendKeys("*(QWE");
            Data_Entry.TR_3.clear();
            Data_Entry.TR_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L18M4 = Data_Entry.TR_4.getAttribute("disabled") != null && Data_Entry.TR_4.getAttribute("disabled").equals("true");
        if (L18M4) {
            System.out.println("The Total revenue/sales (PHP) October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total revenue/sales (PHP) October 2022 textbox is not disabled - FAILED.");
            Data_Entry.TR_4.clear();
            Data_Entry.TR_4.sendKeys("*(QWE");
            Data_Entry.TR_4.clear();
            Data_Entry.TR_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L18M5 = Data_Entry.TR_5.getAttribute("disabled") != null && Data_Entry.TR_5.getAttribute("disabled").equals("true");
        if (L18M5) {
            System.out.println("The Total revenue/sales (PHP) November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total revenue/sales (PHP) November 2022 textbox is not disabled - FAILED.");
            Data_Entry.TR_5.clear();
            Data_Entry.TR_5.sendKeys("*(QWE");
            Data_Entry.TR_5.clear();
            Data_Entry.TR_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L18M6 = Data_Entry.TR_6.getAttribute("disabled") != null && Data_Entry.TR_6.getAttribute("disabled").equals("true");
        if (L18M6) {
            System.out.println("The Total revenue/sales (PHP) December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Total revenue/sales (PHP) December 2022 textbox is not disabled - FAILED.");
            Data_Entry.TR_6.clear();
            Data_Entry.TR_6.sendKeys("*(QWE");
            Data_Entry.TR_6.clear();
            Data_Entry.TR_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L18M7 = Data_Entry.TR_7.getAttribute("disabled") != null && Data_Entry.TR_7.getAttribute("disabled").equals("true");
        if (L18M7) {
            System.out.println("The Total revenue/sales (PHP) January 2023 textbox is disabled - PASS.");
        } else {
            Data_Entry.TR_7.clear();
            Data_Entry.TR_7.sendKeys("*(QWE");
            Data_Entry.TR_7.clear();
            Data_Entry.TR_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Total revenue/sales (PHP) January 2023 textbox is not disabled - FAILED.");

        }


        boolean L19M1 = Data_Entry.SMA_1.getAttribute("disabled") != null && Data_Entry.SMA_1.getAttribute("disabled").equals("true");
        if (L19M1) {
            System.out.println("The Sales from manufacturing activity JULY 2022 textbox is disabled - PASS.");
        } else {
            Data_Entry.SMA_1.clear();
            Data_Entry.SMA_1.sendKeys("*(QWE");
            Data_Entry.SMA_1.clear();
            Data_Entry.SMA_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Sales from manufacturing activity JULY 2022 textbox is not disabled - FAILED.");

        }

        boolean L19M2 = Data_Entry.SMA_2.getAttribute("disabled") != null && Data_Entry.SMA_2.getAttribute("disabled").equals("true");
        if (L19M2) {
            System.out.println("The Sales from manufacturing activity August 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Sales from manufacturing activity August 2022 textbox is not disabled - FAILED.");
            Data_Entry.SMA_2.clear();
            Data_Entry.SMA_2.sendKeys("*(QWE");
            Data_Entry.SMA_2.clear();
            Data_Entry.SMA_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L19M3 = Data_Entry.SMA_3.getAttribute("disabled") != null && Data_Entry.SMA_3.getAttribute("disabled").equals("true");
        if (L19M3) {
            System.out.println("The Sales from manufacturing activity September 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Sales from manufacturing activity September 2022 textbox is not disabled - FAILED.");
            Data_Entry.SMA_3.clear();
            Data_Entry.SMA_3.sendKeys("*(QWE");
            Data_Entry.SMA_3.clear();
            Data_Entry.SMA_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L19M4 = Data_Entry.SMA_4.getAttribute("disabled") != null && Data_Entry.SMA_4.getAttribute("disabled").equals("true");
        if (L19M4) {
            System.out.println("The Sales from manufacturing activity October 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Sales from manufacturing activity October 2022 textbox is not disabled - FAILED.");
            Data_Entry.SMA_4.clear();
            Data_Entry.SMA_4.sendKeys("*(QWE");
            Data_Entry.SMA_4.clear();
            Data_Entry.SMA_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L19M5 = Data_Entry.SMA_5.getAttribute("disabled") != null && Data_Entry.SMA_5.getAttribute("disabled").equals("true");
        if (L19M5) {
            System.out.println("The Sales from manufacturing activity November 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Sales from manufacturing activity November 2022 textbox is not disabled - FAILED.");
            Data_Entry.SMA_5.clear();
            Data_Entry.SMA_5.sendKeys("*(QWE");
            Data_Entry.SMA_5.clear();
            Data_Entry.SMA_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L19M6 = Data_Entry.SMA_6.getAttribute("disabled") != null && Data_Entry.SMA_6.getAttribute("disabled").equals("true");
        if (L19M6) {
            System.out.println("The Sales from manufacturing activity December 2022 textbox is disabled - PASS.");
        } else {
            System.out.println("The Sales from manufacturing activity December 2022 textbox is not disabled - FAILED.");
            Data_Entry.SMA_6.clear();
            Data_Entry.SMA_6.sendKeys("*(QWE");
            Data_Entry.SMA_6.clear();
            Data_Entry.SMA_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L19M7 = Data_Entry.SMA_7.getAttribute("disabled") != null && Data_Entry.SMA_7.getAttribute("disabled").equals("true");
        if (L19M7) {
            System.out.println("The Sales from manufacturing activity January 2023 textbox is disabled - PASS.");
        } else {
            Data_Entry.SMA_7.clear();
            Data_Entry.SMA_7.sendKeys("*(QWE");
            Data_Entry.SMA_7.clear();
            Data_Entry.SMA_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Sales from manufacturing activity January 2023 textbox is not disabled - FAILED.");

        }


        boolean L20M1 = Data_Entry.SDM_1.getAttribute("disabled") != null && Data_Entry.SDM_1.getAttribute("disabled").equals("true");
        if (L20M1) {
            System.out.println("The Sales to domestic market JULY 2022 textbox is disabled - FAILED - PASS.");
        } else {
            Data_Entry.SDM_1.clear();
            Data_Entry.SDM_1.sendKeys("*(QWE");
            Data_Entry.SDM_1.clear();
            Data_Entry.SDM_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Sales to domestic market JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L20M2 = Data_Entry.SDM_2.getAttribute("disabled") != null && Data_Entry.SDM_2.getAttribute("disabled").equals("true");
        if (L20M2) {
            System.out.println("The Sales to domestic market August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to domestic market August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SDM_2.clear();
            Data_Entry.SDM_2.sendKeys("*(QWE");
            Data_Entry.SDM_2.clear();
            Data_Entry.SDM_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L20M3 = Data_Entry.SDM_3.getAttribute("disabled") != null && Data_Entry.SDM_3.getAttribute("disabled").equals("true");
        if (L20M3) {
            System.out.println("The Sales to domestic market September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to domestic market September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SDM_3.clear();
            Data_Entry.SDM_3.sendKeys("*(QWE");
            Data_Entry.SDM_3.clear();
            Data_Entry.SDM_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L20M4 = Data_Entry.SDM_4.getAttribute("disabled") != null && Data_Entry.SDM_4.getAttribute("disabled").equals("true");
        if (L20M4) {
            System.out.println("The Sales to domestic market October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to domestic market October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SDM_4.clear();
            Data_Entry.SDM_4.sendKeys("*(QWE");
            Data_Entry.SDM_4.clear();
            Data_Entry.SDM_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L20M5 = Data_Entry.SDM_5.getAttribute("disabled") != null && Data_Entry.SDM_5.getAttribute("disabled").equals("true");
        if (L20M5) {
            System.out.println("The Sales to domestic market November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to domestic market November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SDM_5.clear();
            Data_Entry.SDM_5.sendKeys("*(QWE");
            Data_Entry.SDM_5.clear();
            Data_Entry.SDM_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L20M6 = Data_Entry.SDM_6.getAttribute("disabled") != null && Data_Entry.SDM_6.getAttribute("disabled").equals("true");
        if (L20M6) {
            System.out.println("The Sales to domestic market December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to domestic market December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SDM_6.clear();
            Data_Entry.SDM_6.sendKeys("*(QWE");
            Data_Entry.SDM_6.clear();
            Data_Entry.SDM_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L20M7 = Data_Entry.SDM_7.getAttribute("disabled") != null && Data_Entry.SDM_7.getAttribute("disabled").equals("true");
        if (L20M7) {
            System.out.println("The Sales to domestic market January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SDM_7.clear();
            Data_Entry.SDM_7.sendKeys("*(QWE");
            Data_Entry.SDM_7.clear();
            Data_Entry.SDM_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Sales to domestic market January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L21M1 = Data_Entry.SEE_1.getAttribute("disabled") != null && Data_Entry.SEE_1.getAttribute("disabled").equals("true");
        if (L21M1) {
            System.out.println("The Sales to exporters/direct exports JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SEE_1.clear();
            Data_Entry.SEE_1.sendKeys("*(QWE");
            Data_Entry.SEE_1.clear();
            Data_Entry.SEE_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Sales to exporters/direct exports JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L21M2 = Data_Entry.SEE_2.getAttribute("disabled") != null && Data_Entry.SEE_2.getAttribute("disabled").equals("true");
        if (L21M2) {
            System.out.println("The Sales to exporters/direct exports August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to exporters/direct exports August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SEE_2.clear();
            Data_Entry.SEE_2.sendKeys("*(QWE");
            Data_Entry.SEE_2.clear();
            Data_Entry.SEE_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L21M3 = Data_Entry.SEE_3.getAttribute("disabled") != null && Data_Entry.SEE_3.getAttribute("disabled").equals("true");
        if (L21M3) {
            System.out.println("The Sales to exporters/direct exports September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to exporters/direct exports September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SEE_3.clear();
            Data_Entry.SEE_3.sendKeys("*(QWE");
            Data_Entry.SEE_3.clear();
            Data_Entry.SEE_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L21M4 = Data_Entry.SEE_4.getAttribute("disabled") != null && Data_Entry.SEE_4.getAttribute("disabled").equals("true");
        if (L21M4) {
            System.out.println("The Sales to exporters/direct exports October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to exporters/direct exports October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SEE_4.clear();
            Data_Entry.SEE_4.sendKeys("*(QWE");
            Data_Entry.SEE_4.clear();
            Data_Entry.SEE_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L21M5 = Data_Entry.SEE_5.getAttribute("disabled") != null && Data_Entry.SEE_5.getAttribute("disabled").equals("true");
        if (L21M5) {
            System.out.println("The Sales to exporters/direct exports November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to exporters/direct exports November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SEE_5.clear();
            Data_Entry.SEE_5.sendKeys("*(QWE");
            Data_Entry.SEE_5.clear();
            Data_Entry.SEE_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L21M6 = Data_Entry.SEE_6.getAttribute("disabled") != null && Data_Entry.SEE_6.getAttribute("disabled").equals("true");
        if (L21M6) {
            System.out.println("The Sales to exporters/direct exports December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Sales to exporters/direct exports December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.SEE_6.clear();
            Data_Entry.SEE_6.sendKeys("*(QWE");
            Data_Entry.SEE_6.clear();
            Data_Entry.SEE_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L21M7 = Data_Entry.SEE_7.getAttribute("disabled") != null && Data_Entry.SEE_7.getAttribute("disabled").equals("true");
        if (L21M7) {
            System.out.println("The Sales to exporters/direct exports January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.SEE_7.clear();
            Data_Entry.SEE_7.sendKeys("*(QWE");
            Data_Entry.SEE_7.clear();
            Data_Entry.SEE_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Sales to exporters/direct exports January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L24M1 = Data_Entry.OI_1.getAttribute("disabled") != null && Data_Entry.OI_1.getAttribute("disabled").equals("true");
        if (L24M1) {
            System.out.println("The Other income JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.OI_1.clear();
            Data_Entry.OI_1.sendKeys("*(QWE");
            Data_Entry.OI_1.clear();
            Data_Entry.OI_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Other income JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L24M2 = Data_Entry.OI_2.getAttribute("disabled") != null && Data_Entry.OI_2.getAttribute("disabled").equals("true");
        if (L24M2) {
            System.out.println("The Other income August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other income August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OI_2.clear();
            Data_Entry.OI_2.sendKeys("*(QWE");
            Data_Entry.OI_2.clear();
            Data_Entry.OI_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L24M3 = Data_Entry.OI_3.getAttribute("disabled") != null && Data_Entry.OI_3.getAttribute("disabled").equals("true");
        if (L24M3) {
            System.out.println("The Other income September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other income September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OI_3.clear();
            Data_Entry.OI_3.sendKeys("*(QWE");
            Data_Entry.OI_3.clear();
            Data_Entry.OI_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L24M4 = Data_Entry.OI_4.getAttribute("disabled") != null && Data_Entry.OI_4.getAttribute("disabled").equals("true");
        if (L24M4) {
            System.out.println("The Other income October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other income October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OI_4.clear();
            Data_Entry.OI_4.sendKeys("*(QWE");
            Data_Entry.OI_4.clear();
            Data_Entry.OI_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L24M5 = Data_Entry.OI_5.getAttribute("disabled") != null && Data_Entry.OI_5.getAttribute("disabled").equals("true");
        if (L24M5) {
            System.out.println("The Other income November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other income November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OI_5.clear();
            Data_Entry.OI_5.sendKeys("*(QWE");
            Data_Entry.OI_5.clear();
            Data_Entry.OI_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L24M6 = Data_Entry.OI_6.getAttribute("disabled") != null && Data_Entry.OI_6.getAttribute("disabled").equals("true");
        if (L24M6) {
            System.out.println("The Other income December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Other income December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.OI_6.clear();
            Data_Entry.OI_6.sendKeys("*(QWE");
            Data_Entry.OI_6.clear();
            Data_Entry.OI_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L24M7 = Data_Entry.OI_7.getAttribute("disabled") != null && Data_Entry.OI_7.getAttribute("disabled").equals("true");
        if (L24M7) {
            System.out.println("The Other income January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.OI_7.clear();
            Data_Entry.OI_7.sendKeys("*(QWE");
            Data_Entry.OI_7.clear();
            Data_Entry.OI_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Other income January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L25M1 = Data_Entry.FP_1.getAttribute("disabled") != null && Data_Entry.FP_1.getAttribute("disabled").equals("true");
        if (L25M1) {
            System.out.println("The Finished products JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.FP_1.clear();
            Data_Entry.FP_1.sendKeys("*(QWE");
            Data_Entry.FP_1.clear();
            Data_Entry.FP_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Finished products JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L25M2 = Data_Entry.FP_2.getAttribute("disabled") != null && Data_Entry.FP_2.getAttribute("disabled").equals("true");
        if (L25M2) {
            System.out.println("The Finished products August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Finished products August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.FP_2.clear();
            Data_Entry.FP_2.sendKeys("*(QWE");
            Data_Entry.FP_2.clear();
            Data_Entry.FP_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L25M3 = Data_Entry.FP_3.getAttribute("disabled") != null && Data_Entry.FP_3.getAttribute("disabled").equals("true");
        if (L25M3) {
            System.out.println("The Finished products September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Finished products September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.FP_3.clear();
            Data_Entry.FP_3.sendKeys("*(QWE");
            Data_Entry.FP_3.clear();
            Data_Entry.FP_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L25M4 = Data_Entry.FP_4.getAttribute("disabled") != null && Data_Entry.FP_4.getAttribute("disabled").equals("true");
        if (L25M4) {
            System.out.println("The Finished products October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Finished products October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.FP_4.clear();
            Data_Entry.FP_4.sendKeys("*(QWE");
            Data_Entry.FP_4.clear();
            Data_Entry.FP_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L25M5 = Data_Entry.FP_5.getAttribute("disabled") != null && Data_Entry.FP_5.getAttribute("disabled").equals("true");
        if (L25M5) {
            System.out.println("The Finished products November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Finished products November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.FP_5.clear();
            Data_Entry.FP_5.sendKeys("*(QWE");
            Data_Entry.FP_5.clear();
            Data_Entry.FP_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L25M6 = Data_Entry.FP_6.getAttribute("disabled") != null && Data_Entry.FP_6.getAttribute("disabled").equals("true");
        if (L25M6) {
            System.out.println("The Finished products December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Finished products December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.FP_6.clear();
            Data_Entry.FP_6.sendKeys("*(QWE");
            Data_Entry.FP_6.clear();
            Data_Entry.FP_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L25M7 = Data_Entry.FP_7.getAttribute("disabled") != null && Data_Entry.FP_7.getAttribute("disabled").equals("true");
        if (L25M7) {
            System.out.println("The Finished products January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.FP_7.clear();
            Data_Entry.FP_7.sendKeys("*(QWE");
            Data_Entry.FP_7.clear();
            Data_Entry.FP_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Finished products January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L26M1 = Data_Entry.WIP_1.getAttribute("disabled") != null && Data_Entry.WIP_1.getAttribute("disabled").equals("true");
        if (L26M1) {
            System.out.println("The Work-in-progress JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.WIP_1.clear();
            Data_Entry.WIP_1.sendKeys("*(QWE");
            Data_Entry.WIP_1.clear();
            Data_Entry.WIP_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Work-in-progress JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L26M2 = Data_Entry.WIP_2.getAttribute("disabled") != null && Data_Entry.WIP_2.getAttribute("disabled").equals("true");
        if (L26M2) {
            System.out.println("The Work-in-progress August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Work-in-progress August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.WIP_2.clear();
            Data_Entry.WIP_2.sendKeys("*(QWE");
            Data_Entry.WIP_2.clear();
            Data_Entry.WIP_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L26M3 = Data_Entry.WIP_3.getAttribute("disabled") != null && Data_Entry.WIP_3.getAttribute("disabled").equals("true");
        if (L26M3) {
            System.out.println("The Work-in-progress September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Work-in-progress September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.WIP_3.clear();
            Data_Entry.WIP_3.sendKeys("*(QWE");
            Data_Entry.WIP_3.clear();
            Data_Entry.WIP_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L26M4 = Data_Entry.WIP_4.getAttribute("disabled") != null && Data_Entry.WIP_4.getAttribute("disabled").equals("true");
        if (L26M4) {
            System.out.println("The Work-in-progress October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Work-in-progress October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.WIP_4.clear();
            Data_Entry.WIP_4.sendKeys("*(QWE");
            Data_Entry.WIP_4.clear();
            Data_Entry.WIP_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L26M5 = Data_Entry.WIP_5.getAttribute("disabled") != null && Data_Entry.WIP_5.getAttribute("disabled").equals("true");
        if (L26M5) {
            System.out.println("The Work-in-progress November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Work-in-progress November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.WIP_5.clear();
            Data_Entry.WIP_5.sendKeys("*(QWE");
            Data_Entry.WIP_5.clear();
            Data_Entry.WIP_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L26M6 = Data_Entry.WIP_6.getAttribute("disabled") != null && Data_Entry.WIP_6.getAttribute("disabled").equals("true");
        if (L26M6) {
            System.out.println("The Work-in-progress December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Work-in-progress December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.WIP_6.clear();
            Data_Entry.WIP_6.sendKeys("*(QWE");
            Data_Entry.WIP_6.clear();
            Data_Entry.WIP_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L26M7 = Data_Entry.WIP_7.getAttribute("disabled") != null && Data_Entry.WIP_7.getAttribute("disabled").equals("true");
        if (L26M7) {
            System.out.println("The Work-in-progress January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.WIP_7.clear();
            Data_Entry.WIP_7.sendKeys("*(QWE");
            Data_Entry.WIP_7.clear();
            Data_Entry.WIP_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Work-in-progress January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        boolean L27M1 = Data_Entry.RM_1.getAttribute("disabled") != null && Data_Entry.RM_1.getAttribute("disabled").equals("true");
        if (L27M1) {
            System.out.println("The Raw materials JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.RM_1.clear();
            Data_Entry.RM_1.sendKeys("*(QWE");
            Data_Entry.RM_1.clear();
            Data_Entry.RM_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Raw materials JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L27M2 = Data_Entry.RM_2.getAttribute("disabled") != null && Data_Entry.RM_2.getAttribute("disabled").equals("true");
        if (L27M2) {
            System.out.println("The Raw materials August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Raw materials August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.RM_2.clear();
            Data_Entry.RM_2.sendKeys("*(QWE");
            Data_Entry.RM_2.clear();
            Data_Entry.RM_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L27M3 = Data_Entry.RM_3.getAttribute("disabled") != null && Data_Entry.RM_3.getAttribute("disabled").equals("true");
        if (L27M3) {
            System.out.println("The Raw materials September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Raw materials September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.RM_3.clear();
            Data_Entry.RM_3.sendKeys("*(QWE");
            Data_Entry.RM_3.clear();
            Data_Entry.RM_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L27M4 = Data_Entry.RM_4.getAttribute("disabled") != null && Data_Entry.RM_4.getAttribute("disabled").equals("true");
        if (L27M4) {
            System.out.println("The Raw materials October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Raw materials October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.RM_4.clear();
            Data_Entry.RM_4.sendKeys("*(QWE");
            Data_Entry.RM_4.clear();
            Data_Entry.RM_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L27M5 = Data_Entry.RM_5.getAttribute("disabled") != null && Data_Entry.RM_5.getAttribute("disabled").equals("true");
        if (L27M5) {
            System.out.println("The Raw materials November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Raw materials November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.RM_5.clear();
            Data_Entry.RM_5.sendKeys("*(QWE");
            Data_Entry.RM_5.clear();
            Data_Entry.RM_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L27M6 = Data_Entry.RM_6.getAttribute("disabled") != null && Data_Entry.RM_6.getAttribute("disabled").equals("true");
        if (L27M6) {
            System.out.println("The Raw materials December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Raw materials December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.RM_6.clear();
            Data_Entry.RM_6.sendKeys("*(QWE");
            Data_Entry.RM_6.clear();
            Data_Entry.RM_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L27M7 = Data_Entry.RM_7.getAttribute("disabled") != null && Data_Entry.RM_7.getAttribute("disabled").equals("true");
        if (L27M7) {
            System.out.println("The Raw materials January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.RM_7.clear();
            Data_Entry.RM_7.sendKeys("*(QWE");
            Data_Entry.RM_7.clear();
            Data_Entry.RM_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Raw materials January 2023 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L28M1 = Data_Entry.CU_1.getAttribute("disabled") != null && Data_Entry.CU_1.getAttribute("disabled").equals("true");
        if (L28M1) {
            System.out.println("The Average Capacity Utilization Rate JULY 2022 textbox is disabled - FAILED.");
        } else {
            Data_Entry.CU_1.clear();
            Data_Entry.CU_1.sendKeys("*(QWE");
            Data_Entry.CU_1.clear();
            Data_Entry.CU_1.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Average Capacity Utilization Rate JULY 2022 textbox is not disabled and Numeric only - PASS.");

        }

        boolean L28M2 = Data_Entry.CU_2.getAttribute("disabled") != null && Data_Entry.CU_2.getAttribute("disabled").equals("true");
        if (L28M2) {
            System.out.println("The Average Capacity Utilization Rate August 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Average Capacity Utilization Rate August 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.CU_2.clear();
            Data_Entry.CU_2.sendKeys("*(QWE");
            Data_Entry.CU_2.clear();
            Data_Entry.CU_2.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L28M3 = Data_Entry.CU_3.getAttribute("disabled") != null && Data_Entry.CU_3.getAttribute("disabled").equals("true");
        if (L28M3) {
            System.out.println("The Average Capacity Utilization Rate September 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Average Capacity Utilization Rate September 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.CU_3.clear();
            Data_Entry.CU_3.sendKeys("*(QWE");
            Data_Entry.CU_3.clear();
            Data_Entry.CU_3.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L28M4 = Data_Entry.CU_4.getAttribute("disabled") != null && Data_Entry.CU_4.getAttribute("disabled").equals("true");
        if (L28M4) {
            System.out.println("The Average Capacity Utilization Rate October 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Average Capacity Utilization Rate October 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.CU_4.clear();
            Data_Entry.CU_4.sendKeys("*(QWE");
            Data_Entry.CU_4.clear();
            Data_Entry.CU_4.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L28M5 = Data_Entry.CU_5.getAttribute("disabled") != null && Data_Entry.CU_5.getAttribute("disabled").equals("true");
        if (L28M5) {
            System.out.println("The Average Capacity Utilization Rate November 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Average Capacity Utilization Rate November 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.CU_5.clear();
            Data_Entry.CU_5.sendKeys("*(QWE");
            Data_Entry.CU_5.clear();
            Data_Entry.CU_5.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L28M6 = Data_Entry.CU_6.getAttribute("disabled") != null && Data_Entry.CU_6.getAttribute("disabled").equals("true");
        if (L28M6) {
            System.out.println("The Average Capacity Utilization Rate December 2022 textbox is disabled - FAILED.");
        } else {
            System.out.println("The Average Capacity Utilization Rate December 2022 textbox is not disabled and Numeric only - PASS.");
            Data_Entry.CU_6.clear();
            Data_Entry.CU_6.sendKeys("*(QWE");
            Data_Entry.CU_6.clear();
            Data_Entry.CU_6.sendKeys(dataset.MISSI_ENTRY);
        }

        boolean L28M7 = Data_Entry.CU_7.getAttribute("disabled") != null && Data_Entry.CU_7.getAttribute("disabled").equals("true");
        if (L28M7) {
            System.out.println("The Average Capacity Utilization Rate January 2023 textbox is disabled - FAILED.");
        } else {
            Data_Entry.CU_7.clear();
            Data_Entry.CU_7.sendKeys("*(QWE");
            Data_Entry.CU_7.clear();
            Data_Entry.CU_7.sendKeys(dataset.MISSI_ENTRY);
            System.out.println("The Average Capacity Utilization Rate January 2023 textbox is not disabled and Numeric only - PASS.");

        }


        Data_Entry.Remarks_1.sendKeys("sample");
        Data_Entry.Remarks_2.sendKeys("sample");
        Data_Entry.Remarks_3.sendKeys("sample");
        Data_Entry.Remarks_4.sendKeys("sample");
        Data_Entry.Remarks_5.sendKeys("sample");
        Data_Entry.Remarks_6.sendKeys("sample");
        Data_Entry.Remarks_7.sendKeys("sample");


        Select R1 = new Select(Data_Entry.Reason_1);
        R1.selectByIndex(0);
        R1.selectByIndex(1);


        Select R2 = new Select(Data_Entry.Reason_2);
        R2.selectByIndex(0);
        R2.selectByIndex(1);


        Select R3 = new Select(Data_Entry.Reason_3);
        R3.selectByIndex(0);
        R3.selectByIndex(1);

        Data_Entry.txt_Prod1.sendKeys("Sample");
        Data_Entry.txt_Prod2.sendKeys("Sample");
        Data_Entry.txt_Prod3.sendKeys("Sample");
        Data_Entry.txt_Prod4.sendKeys("Sample");
        Data_Entry.txt_Prod5.sendKeys("Sample");

        Data_Entry.Accom_officer.sendKeys("Sample");
        Data_Entry.designation.sendKeys("Sample");
        Data_Entry.contact.sendKeys("Sample");
        Data_Entry.e_address.sendKeys("Sample@gmail.com");
        Data_Entry.website.sendKeys("Sample");

    }
}