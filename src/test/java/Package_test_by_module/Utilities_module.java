package Package_test_by_module;

import Package_Element.DataSet;
import Package_Element.LoginPage;
import Package_Element.Utilities;
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

public class Utilities_module {


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

    @Test(priority = 2, testName = "utilities", enabled = true)
    public void utilities() throws InterruptedException {

        Utilities utilities = new Utilities(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(Utilities.lbl_Utilities));
        Utilities.lbl_Utilities.click();

        sleep(1000);
        Utilities.lbl_Active_User.click();

        Utilities.btn_create_user.click();

        sleep(2000);
        Utilities.txt_FnamePO.sendKeys(dataset.F_NAME);
        Utilities.txt_MnamePO.sendKeys(dataset.M_NAME);
        Utilities.txt_LnamePO.sendKeys(dataset.L_NAME);
        Utilities.txt_EmailPO.sendKeys(dataset.EMAIL);

// CO-Staff
        Select PrvU = new Select(Utilities.dd_Prov_OffPO);

        PrvU.selectByIndex(0);
        PrvU.selectByIndex(1);

        Select user = new Select(Utilities.dd_User_rolePO);

        user.selectByIndex(0);
        user.selectByIndex(1);

// PO-Supervisor
        Select PrvU1 = new Select(Utilities.dd_Prov_OffPO);

        PrvU1.selectByIndex(0);
        PrvU1.selectByIndex(1);

        Select user1 = new Select(Utilities.dd_User_rolePO);

        user1.selectByIndex(0);
        user1.selectByIndex(1);

        Utilities.txt_Emp_IDPO.sendKeys(dataset.EMP_ID);

        Utilities.btn_cancel.click();
        sleep(2000);

        Select pg = new Select(Utilities.dd_Ppage);
        pg.selectByIndex(1);
        pg.selectByIndex(2);
        pg.selectByIndex(3);

        Utilities.txt_Search.sendKeys(dataset.SEARCH);

        Utilities.btn_viewPO.click();

        Utilities.btn_ok.click();

        wait.until(ExpectedConditions.visibilityOf(Utilities.btn_updatePO));
        Utilities.btn_updatePO.click();

        sleep(2000);
        String Update_User_Fname = Utilities.txt_Fname_PO.getAttribute("value");
        if (Objects.equals(Update_User_Fname,"SQAD")){
            Utilities.txt_Fname_PO.clear();
            Utilities.txt_Fname_PO.sendKeys(dataset.Fname_Update_PO2);
        } else if (Objects.equals(Update_User_Fname, "SQADs")){
            Utilities.txt_Fname_PO.clear();
            Utilities.txt_Fname_PO.sendKeys(dataset.Fname_Update_PO);
        }else{
            System.out.println("Condition error");
        }
        String Update_User_Mname = Utilities.txt_Mname_PO.getAttribute("value");
        if (Objects.equals(Update_User_Mname,"")){
            Utilities.txt_Mname_PO.clear();
            Utilities.txt_Mname_PO.sendKeys(dataset.Mname_Update_PO2);
        } else if (Objects.equals(Update_User_Mname, "Dela Cruz")){
            Utilities.txt_Mname_PO.clear();
            Utilities.txt_Mname_PO.sendKeys(dataset.Mname_Update_PO);
        }else{
            System.out.println("Condition error");
        }
        String Update_User_Lname = Utilities.txt_Lname_PO.getAttribute("value");
        if (Objects.equals(Update_User_Lname,"TESTER")){
            Utilities.txt_Lname_PO.clear();
            Utilities.txt_Lname_PO.sendKeys(dataset.Lname_Update_PO2);
        } else if (Objects.equals(Update_User_Lname, "TESTERS")){
            Utilities.txt_Lname_PO.clear();
            Utilities.txt_Lname_PO.sendKeys(dataset.Lname_Update_PO);
        }else{
            System.out.println("Condition error");
        }

        wait.until(ExpectedConditions.visibilityOf(Utilities.btn_Update_PO));
        Utilities.btn_Update_PO.click();

        sleep(2000);
        Utilities.btn_Update_modal_ok.click();

        sleep(1000);
        Utilities.btn_view_PO.click();

        sleep(1000);
        Utilities.btn_view_PO_ok.click();

        sleep(1000);
        Utilities.btn_deact.click();

        sleep(1000);
        Utilities.btn_deact_Yes.click();

        sleep(1000);
        Utilities.lbl_Inactive.click();

        sleep(1000);
        Utilities.btn_activate.click();

        sleep(1000);
        Utilities.btn_Activate_Yes.click();

        sleep(1000);
        Utilities.lbl_Active_User.click();

        Utilities.lbl_Logout.click();

    }

}
