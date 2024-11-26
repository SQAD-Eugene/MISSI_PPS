package Package_test_by_module;

import Package_Element.*;
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
import java.util.Objects;

import static java.lang.Thread.sleep;

public class CAWI_registration_module {


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

    @Test(priority = 2, testName = "cawi_registration" , enabled = true)
    public void registration() throws InterruptedException {

        Cawi_Registration cawi_registration = new Cawi_Registration(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

//       validate_inconsistent_Year_Cawi_Reg();

        wait.until(ExpectedConditions.visibilityOf(Cawi_Registration.lbl_Receipt_and_control));
        Cawi_Registration.lbl_Receipt_and_control.click();
        sleep(2000);


        sleep(1000);
        Cawi_Registration.btn_Cawi_Reg.click();


        Select WL = new Select(Cawi_Registration.dd_Year);

        WL.selectByIndex(0);
        WL.selectByIndex(1);
        WL.selectByIndex(2);



        Select srv = new Select(Cawi_Registration.dd_Survey);

        srv.selectByIndex(0);
        srv.selectByIndex(1);


        Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT_PO);

        Cawi_Registration.btn_search.click();

        Cawi_Registration.btn_search.click();
        sleep(4000);
        Cawi_Registration.btn_edit.click();

        sleep(2000);
        String C_Person = Cawi_Registration.txtC_Person.getAttribute("value");
        if (Objects.equals(C_Person, "John Smith")) {
            Cawi_Registration.txtC_Person.clear();
            Cawi_Registration.txtC_Person.sendKeys(dataset.C_PERSON2);
        } else if (Objects.equals(C_Person, "Eugene Nuada")) {
            Cawi_Registration.txtC_Person.clear();
            Cawi_Registration.txtC_Person.sendKeys(dataset.C_PERSON);
        }else {
            System.out.println("Condition Error");
        }
        String C_Number = Cawi_Registration.txtC_number.getAttribute("value");
        if (Objects.equals(C_Number, "09123456799")) {
            Cawi_Registration.txtC_number.clear();
            Cawi_Registration.txtC_number.sendKeys(dataset.C_NUMBER2);
        } else  if (Objects.equals(C_Number, "09326565455")){
            Cawi_Registration.txtC_number.clear();
            Cawi_Registration.txtC_number.sendKeys(dataset.C_NUMBER);
        }else {
            System.out.println("Condition Error");
        }
        String C_Address = Cawi_Registration.txtE_Address.getAttribute("value");
        if (Objects.equals(C_Address, "sqadtester020@gmail.com")) {
            Cawi_Registration.txtE_Address.clear();
            Cawi_Registration.txtE_Address.sendKeys(dataset.E_ADDRESS2);
        } else if (Objects.equals(C_Address, "eugenenuada061@gmail.com")) {
            Cawi_Registration.txtE_Address.clear();
            Cawi_Registration.txtE_Address.sendKeys(dataset.E_ADDRESS);
        }else {
            System.out.println("Condition Error");
        }
        Cawi_Registration.btn_save.click();
        wait.until(ExpectedConditions.elementToBeClickable(Cawi_Registration.btn_close));
        sleep(5000);
        Cawi_Registration.btn_close.click();


    }

    //Validate inconsistency
    public static void validate_inconsistent_Year_Cawi_Reg() throws InterruptedException {
        Select WL = new Select(Cawi_Registration.dd_Year);
        WL.selectByIndex(0);
        Select srv = new Select(Cawi_Registration.dd_Survey);
        srv.selectByIndex(1);
        Cawi_Registration.txt_ECN.clear();
        Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT_PO);
        Cawi_Registration.btn_Search.click();
        sleep(3000);
        Cawi_Registration.btn_error_ok_PO.click();
        try{
            Cawi_Registration.btn_error_ok_PO.isDisplayed();
            System.out.println("Inconsistent Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }


        Select WL1 = new Select(Cawi_Registration.dd_Year);
        WL1.selectByIndex(1);
        Select srv1 = new Select(Cawi_Registration.dd_Survey);
        srv1.selectByIndex(0);
        Cawi_Registration.txt_ECN.clear();
        Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT_PO);
        Cawi_Registration.btn_Search.click();
        sleep(3000);
        Cawi_Registration.btn_error_ok_PO.click();
        try{
            Cawi_Registration.btn_error_ok_PO.isDisplayed();
            System.out.println("Inconsistent Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        Select WL2 = new Select(Cawi_Registration.dd_Year);
        WL2.selectByIndex(1);
        Select srv2 = new Select(Cawi_Registration.dd_Survey);
        srv2.selectByIndex(1);
//        Package_Element.Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT_PO);
        Cawi_Registration.txt_ECN.clear();
        Cawi_Registration.btn_Search.click();
        sleep(3000);
        try{
            Cawi_Registration.btn_error_ok_PO.click();
            Cawi_Registration.btn_error_ok_PO.isDisplayed();
            System.out.println("Inconsistent Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }


    }

}
