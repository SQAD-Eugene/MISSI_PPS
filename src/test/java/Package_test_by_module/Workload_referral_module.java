package Package_test_by_module;

import Package_Element.DataSet;
import Package_Element.LoginPage;
import Package_Element.Rec_and_cont;
import Package_Element.Workload_Referral;
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

public class Workload_referral_module {

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


        eWait .until(ExpectedConditions.visibilityOf(login.btn_login_btn));
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
                softAssert.assertTrue(login.lbl_email.isDisplayed(),  "Email label is missing");
                softAssert.assertTrue(login.tf_emailAdd.isDisplayed(),"Missing email text field");
                System.out.println("required email? " + login.tf_emailAdd.getAttribute("required"));

                softAssert.assertTrue(login.lbl_pw.isDisplayed(),"Password label is missing");
                softAssert.assertTrue(login.tf_pw.isDisplayed(), "Missing password field");
                System.out.println("required password? " + login.tf_pw.getAttribute("required"));

                softAssert.assertTrue(login.btn_login.isDisplayed(), "Missing login button");
                login.btn_login.click();
            }
            iteration++;
        }
        softAssert.assertAll();
    }

    @Test(priority = 2, testName = "workload_referral" , enabled = true)
    public void cawi_registration() throws InterruptedException{

        Workload_Referral workload_referral = new Workload_Referral(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(Workload_Referral.lbl_Receipt_and_control));
        Workload_Referral.lbl_Receipt_and_control.click();

        sleep(13000);
        Workload_Referral.lbl_Workload_Referral.click();

        validate_inconsistent_Year_workload_Ref();

        Select yr = new Select(Workload_Referral.dd_Year);
        yr.selectByIndex(1);

        Select srv = new Select(Workload_Referral.dd_Survey);
        srv.selectByIndex(1);

        Select ref = new Select(Workload_Referral.dd_Referral);
        ref.selectByIndex(1);

        Workload_Referral.btn_Search.click();

        sleep(1000);
        Select P_page = new Select(Workload_Referral.dd_P_PAge);

        P_page.selectByIndex(1);

//      Workload_Referral.txt_Search.sendKeys(dataset.SRC_TXT);
        wait.until(ExpectedConditions.visibilityOf(Workload_Referral.btn_view));
        Workload_Referral.btn_view.click();

        wait.until(ExpectedConditions.visibilityOf(Workload_Referral.btn_close));
        Workload_Referral.btn_close.click();

    }

    //Validate inconsistency
    static void validate_inconsistent_Year_workload_Ref() throws InterruptedException {
        Select WL = new Select(Workload_Referral.dd_Year);
        WL.selectByIndex(0);
        Select srv = new Select(Workload_Referral.dd_Survey);
        srv.selectByIndex(1);
        Workload_Referral.btn_Search.click();
        sleep(3000);
        Workload_Referral.btn_error_ok_PO.click();
//        try {
//            Package_Element.Workload_Referral.btn_error_ok_PO.isDisplayed();
//            System.out.println("Inconsistent Year selected and Displayed validation message PASS");
//        } catch (Exception e) {
//            System.out.println("No validation message Displayed FAIL");
//        }

        Select WL1 = new Select(Workload_Referral.dd_Year);
        WL1.selectByIndex(0);
        Select srv1 = new Select(Workload_Referral.dd_Survey);
        srv1.selectByIndex(1);
        Workload_Referral.btn_Search.click();
        sleep(3000);
        Workload_Referral.btn_error_ok_PO.click();
//            try {
//                Package_Element.Workload_Referral.btn_error_ok_PO.isDisplayed();
//                System.out.println("Inconsistent Year selected and Displayed validation message PASS");
//            } catch (Exception e) {
//                System.out.println("No validation message Displayed FAIL");
    }

}
