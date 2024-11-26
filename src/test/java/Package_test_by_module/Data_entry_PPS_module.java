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

public class Data_entry_PPS_module {


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
    @Test(priority = 2, testName = "PPS")
    public void data_entry_PPS() throws InterruptedException {
        Data_Entry data_entry = new Data_Entry(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(Data_Entry.lbl_Data_entry));
        Data_Entry.lbl_Data_entry.click();

        wait.until(ExpectedConditions.visibilityOf(Data_Entry.lbl_PPS));
        Data_Entry.lbl_PPS.click();

        sleep(1000);
        Select Yr1 = new Select(Data_Entry.dd_Year);
        Yr1.selectByIndex(1);

        Select Mth1 = new Select(Data_Entry.dd_Month);
        Mth1.selectByIndex(1);

        sleep(1000);
        Data_Entry.txt_ecn.sendKeys(dataset.ECN);

        Data_Entry.btn_submit_pps.click();

        sleep(1000);
//        wait.until(ExpectedConditions.visibilityOfAllElements(Data_Entry.lbl_Gen_info_PPS, Data_Entry.txt_ECN, Data_Entry.lbl_D_name, Data_Entry.txt_D_name, Data_Entry.lbl_D_Add,
//                Data_Entry.txt_D_Add, Data_Entry.lbl_D_TIN, Data_Entry.txt_D_TIN, Data_Entry.lbl_D_IND, Data_Entry.txt_D_IND, Data_Entry.lbl_D_Main_Eco));

        softAssert.assertTrue(Data_Entry.lbl_Gen_info_PPS.isDisplayed(), "Gen info label is missing");
        String ECN_lbl_PPS = Data_Entry.lbl_Gen_info_PPS.getText();
        System.out.println("System displays label " + ECN_lbl_PPS);

        softAssert.assertTrue(Data_Entry.txt_ECN.isDisplayed(), "Gen info text box is missing");
        String ECN_txt_PPS = Data_Entry.txt_ECN.getAttribute("value");
        System.out.println("Attribute " + ECN_txt_PPS);
        boolean isDisabled_ECN_PPS = Data_Entry.txt_ECN.getAttribute("disabled") != null && Data_Entry.txt_ECN.getAttribute("disabled").equals("true");
        if (isDisabled_ECN_PPS) {
            System.out.println("The ECN textbox is disabled.");
        } else {
            System.out.println("The ECN textbox is not disabled.");
        }

        softAssert.assertTrue(Data_Entry.lbl_D_name.isDisplayed(), "Business name label is missing");
        String B_name_lbl_PPS = Data_Entry.lbl_D_name.getText();
        System.out.println("System displays label " + B_name_lbl_PPS);

        softAssert.assertTrue(Data_Entry.txt_D_name.isDisplayed(), "Business name text box is missing");
        String B_name_txt_PPS = Data_Entry.txt_D_name.getAttribute("value");
        System.out.println("Attribute " + B_name_txt_PPS);

        boolean isDisabled_Dname_PPS = Data_Entry.txt_D_name.getAttribute("disabled") != null && Data_Entry.txt_D_name.getAttribute("disabled").equals("true");
        if (isDisabled_Dname_PPS) {
            System.out.println("The Business Name textbox is disabled.");
        } else {
            System.out.println("The Business Name textbox is not disabled.");
            Data_Entry.txt_D_name.sendKeys("Sample Text");
            Data_Entry.txt_D_name.clear();
            Data_Entry.txt_D_name.sendKeys("Sample SQAD CORP");
        }
        softAssert.assertTrue(Data_Entry.lbl_D_Add.isDisplayed(), "Business Address label is missing");
        String D_Add_lbl_PPS = Data_Entry.lbl_D_Add.getText();
        System.out.println("System displays label " + D_Add_lbl_PPS);

        softAssert.assertTrue(Data_Entry.txt_D_Add.isDisplayed(), "Business Address text box is missing");
        String D_add_txt_PPS = Data_Entry.txt_D_Add.getAttribute("value");
        System.out.println("Attribute " + D_add_txt_PPS);

        boolean isDisabled_Dadd_PPS = Data_Entry.txt_D_Add.getAttribute("disabled") != null && Data_Entry.txt_D_Add.getAttribute("disabled").equals("true");
        if (isDisabled_Dadd_PPS) {
            System.out.println("The Business Address textbox is disabled.");
        } else {
            System.out.println("The Business Address textbox is not disabled.");
            Data_Entry.txt_D_Add.sendKeys("Sample Text");
            Data_Entry.txt_D_Add.clear();
            Data_Entry.txt_D_Add.sendKeys("PUROK 4 BARANGAY 22");
        }

        softAssert.assertTrue(Data_Entry.lbl_D_TIN.isDisplayed(), "TIN label is missing");
        String D_tin_lbl_PPS = Data_Entry.lbl_D_TIN.getText();
        System.out.println("System displays label " + D_tin_lbl_PPS);

        softAssert.assertTrue(Data_Entry.txt_D_TIN.isDisplayed(), "TIN text box is missing");
        String D_tin_txt_PPS = Data_Entry.txt_D_TIN.getAttribute("value");
        System.out.println("Attribute " + D_tin_txt_PPS);

        boolean isDisabled_DTIN_PPS = Data_Entry.txt_D_TIN.getAttribute("disabled") != null && Data_Entry.txt_D_TIN.getAttribute("disabled").equals("true");
        if (isDisabled_DTIN_PPS) {
            System.out.println("The TIN textbox is disabled.");
        } else {
            System.out.println("The TIN textbox is not disabled.");
            Data_Entry.txt_D_TIN.sendKeys("Sample Text");
            Data_Entry.txt_D_TIN.clear();
            Data_Entry.txt_D_TIN.sendKeys("8701066");
        }
        softAssert.assertTrue(Data_Entry.lbl_D_IND.isDisplayed(), "IND label is missing");
        String D_IND_lbl_PPS = Data_Entry.lbl_D_IND.getText();
        System.out.println("System displays label " + D_IND_lbl_PPS);

        softAssert.assertTrue(Data_Entry.txt_D_IND.isDisplayed(), "IND text box is missing");
        String D_IND_txt_PPS = Data_Entry.txt_D_IND.getAttribute("value");
        System.out.println("Attribute " + D_IND_txt_PPS);

        boolean isDisabled_DIND_PPS = Data_Entry.txt_D_IND.getAttribute("disabled") != null && Data_Entry.txt_D_IND.getAttribute("disabled").equals("true");
        if (isDisabled_DIND_PPS) {
            System.out.println("The TIN textbox is disabled.");
        } else {
            System.out.println("The TIN textbox is not disabled.");
            Data_Entry.txt_D_IND.sendKeys("Sample Text");
            Data_Entry.txt_D_IND.clear();
            Data_Entry.txt_D_IND.sendKeys("C30111");
        }
        softAssert.assertTrue(Data_Entry.lbl_D_Main_Eco_PPS.isDisplayed(), "Main Economic label is missing");
        String D_Main_Eco_lbl_PPS = Data_Entry.lbl_D_Main_Eco_PPS.getText();
        System.out.println("System displays label " + D_Main_Eco_lbl_PPS);

        softAssert.assertTrue(Data_Entry.lbl_D_Main_Eco2_PPS.isDisplayed(), "Main Economic label is missing");
        String D_Main_Eco2_lbl_PPS = Data_Entry.lbl_D_Main_Eco2_PPS.getText();
        System.out.println("System displays label " + D_Main_Eco2_lbl_PPS);

        executor.executeScript("window.scrollBy(0, 500);");
        sleep(1000);
        softAssert.assertTrue(Data_Entry.txt_D_Main_Eco.isDisplayed(), "Main Economic text box is missing");
        String D_Main_txt_PPS = Data_Entry.txt_D_Main_Eco.getAttribute("value");
        System.out.println("Attribute " + D_Main_txt_PPS);

        boolean isDisabled_Main_PPS = Data_Entry.txt_D_Main_Eco.getAttribute("disabled") != null && Data_Entry.txt_D_Main_Eco.getAttribute("disabled").equals("true");
        if (isDisabled_Main_PPS) {
            System.out.println("The Main Economic (Principal) Activity textbox is disabled.");
        } else {
            System.out.println("The Main Economic (Principal) Activity textbox is not disabled.");
            Data_Entry.txt_D_Main_Eco.sendKeys("Sample Text");
            Data_Entry.txt_D_Main_Eco.clear();
            Data_Entry.txt_D_Main_Eco.sendKeys("SHIPREPAIR AND SHIPBUILDING");
        }

        Data_Entry.Add_P.click();
        Data_Entry.txt_Prod_name.sendKeys("Sample Text");
//        Data_Entry.txt_desc.sendKeys("Sample Text");
        Data_Entry.txt_brand.sendKeys("Sample Text");
        Data_Entry.txt_specs.sendKeys("Sample Text");
        Data_Entry.txt_measure.sendKeys("Liter");
        Data_Entry.txt_share.sendKeys("25");

        wait.until(ExpectedConditions.visibilityOf(Data_Entry.btn_submit_pps));
        Data_Entry.btn_submit_pps.click();


        sleep(2000);
        Data_Entry.btn_edit.click();
        wait.until(ExpectedConditions.visibilityOf(Data_Entry.txt_Prod_name));

        sleep(2000);
        Data_Entry.txt_Prod_name.clear();
//        Data_Entry.txt_desc.clear();
        Data_Entry.txt_brand.clear();
        Data_Entry.txt_specs.clear();
        Data_Entry.txt_measure.clear();
        Data_Entry.txt_share.clear();

        sleep(2000);
        Data_Entry.txt_Prod_name.sendKeys("Sample1 Text");
//        Data_Entry.txt_desc.sendKeys("Sample1 Text");
        Data_Entry.txt_brand.sendKeys("Sample1 Text");
        Data_Entry.txt_specs.sendKeys("Sample1 Text");
        Data_Entry.txt_measure.sendKeys("Yard");
        Data_Entry.txt_share.sendKeys("50");

        Data_Entry.btn_update.click();

        Data_Entry.btn_delete.click();

        wait.until(ExpectedConditions.visibilityOf(Data_Entry.btn_yes));
        Data_Entry.btn_yes.click();


//        Package_Element.Data_Entry.btn_cancel.click();

//        Package_Element.Data_Entry.btn_Submit.click();

        wait.until(ExpectedConditions.visibilityOf(Data_Entry.btn_select));
        Data_Entry.btn_select.click();

        Data_Entry.btn_Proceed.click();


        sleep(2000);

//        executor.executeScript("window.scrollBy(0, 500);");
        Data_Entry.txt_PPS_Prod_M1.sendKeys("1000");
        Data_Entry.txt_PPS_Prod_M2.sendKeys("1000");
        Data_Entry.txt_PPS_Prod_M3.sendKeys("1000");
        Data_Entry.txt_PPS_Prod_M4.sendKeys("1000");
        Data_Entry.txt_PPS_Prod_M5.sendKeys("1000");
        Data_Entry.txt_PPS_Prod_M6.sendKeys("2000");
        Data_Entry.txt_PPS_Prod_M7.sendKeys("1000");

        Data_Entry.txt_PPS_Remarks_M1.sendKeys("Sample");
        Data_Entry.txt_PPS_Remarks_M2.sendKeys("Sample");
        Data_Entry.txt_PPS_Remarks_M3.sendKeys("Sample");
        Data_Entry.txt_PPS_Remarks_M4.sendKeys("Sample");
        Data_Entry.txt_PPS_Remarks_M5.sendKeys("Sample");
        Data_Entry.txt_PPS_Remarks_M6.sendKeys("Sample");
        Data_Entry.txt_PPS_Remarks_M7.sendKeys("Sample");


        Select R_PPS1 = new Select(Data_Entry.ReasonPPS_1);
        R_PPS1.selectByIndex(0);
        R_PPS1.selectByIndex(1);

        Data_Entry.Accom_officer.sendKeys("Sample");
        Data_Entry.designation.sendKeys("Sample");
        Data_Entry.contact.sendKeys("09328998799");
        Data_Entry.e_address.sendKeys("Sample@gmail.com");
        Data_Entry.website.sendKeys("Website.com");

        Data_Entry.lbl_Data_entry.click();

    }



}
