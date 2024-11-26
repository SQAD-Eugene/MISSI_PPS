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

import static java.lang.Thread.sleep;

public class Record_management_module {

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

    @Test(priority = 2, testName = "record_management", enabled = true)
    public void view_data() throws InterruptedException {

        View_Data view_data = new View_Data(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(View_Data.lbl_Record_management));
        View_Data.lbl_Record_management.click();

        sleep(1000);
        View_Data.lbl_View_data.click();

        Select Sur = new Select(View_Data.dd_survey);
        Sur.selectByIndex(0);
        Sur.selectByIndex(1);

        Select M = new Select(View_Data.dd_Month);
        M.selectByIndex(0);
        M.selectByIndex(1);

        Select Y = new Select(View_Data.dd_Year);
        Y.selectByIndex(0);
        Y.selectByIndex(1);
        Y.selectByIndex(2);

        Select cla = new Select(View_Data.dd_industry_class);
        cla.selectByIndex(0);
        cla.selectByIndex(1);
        cla.selectByIndex(2);

        Select ind = new Select(View_Data.dd_industry_desc);
        ind.selectByIndex(0);
        ind.selectByIndex(1);
        ind.selectByIndex(2);

        View_Data.btn_Show_data.click();
    }

    @Test(priority = 3, testName = "list_of_sample_estab", enabled = true)
    public void list_of_sample_estab() throws InterruptedException {

        List_of_sample_estab list_of_sample_estab = new List_of_sample_estab(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        List_of_sample_estab.lbl_list_of_sample.click();

        sleep(2000);
        Select ind = new Select(List_of_sample_estab.dd_List_estab);
        ind.selectByIndex(0);
        ind.selectByIndex(1);
        ind.selectByIndex(2);

        List_of_sample_estab.btn_show.click();

        Select pg = new Select(List_of_sample_estab.dd_Per_page);
        pg.selectByIndex(0);
        pg.selectByIndex(1);
        pg.selectByIndex(2);


        List_of_sample_estab.txt_search.sendKeys(dataset.ECN);


    }


    @Test(priority = 4, testName = "tracking_changes_data_entry", enabled = true)
    public void tracking_changes_data_entry() throws InterruptedException {

        Tracking_changes_data_entry tracking_changes_data_entry = new Tracking_changes_data_entry(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        Tracking_changes_data_entry.lbl_Tracking_of_changes.click();

        sleep(2000);
        Select yr = new Select(Tracking_changes_data_entry.dd_Yr);
        yr.selectByIndex(0);
        yr.selectByIndex(1);
        yr.selectByIndex(2);

        Select M = new Select(Tracking_changes_data_entry.dd_Mth);
        M.selectByIndex(0);
        M.selectByIndex(1);
        M.selectByIndex(2);

        Select sur = new Select(Tracking_changes_data_entry.dd_Sur);
        sur.selectByIndex(0);
        sur.selectByIndex(1);

        Tracking_changes_data_entry.btn_Show.click();

        sleep(1000);
        Select Fil = new Select(Tracking_changes_data_entry.dd_id_code);
        Fil.selectByIndex(0);
        Fil.selectByIndex(1);
        Fil.selectByIndex(2);

        Tracking_changes_data_entry.btn_Filter.click();

        Select Pg = new Select(Tracking_changes_data_entry.dd_Ppage);
        Pg.selectByIndex(0);
        Pg.selectByIndex(1);
        Pg.selectByIndex(2);
        Pg.selectByIndex(3);

        Tracking_changes_data_entry.txt_Search.sendKeys(dataset.ECN);
    }

}
