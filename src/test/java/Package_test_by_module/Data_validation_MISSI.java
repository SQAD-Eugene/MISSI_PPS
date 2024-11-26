package Package_test_by_module;

import Package_Element.DataSet;
import Package_Element.Data_validation;
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

public class Data_validation_MISSI {

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

    @Test(priority = 2, testName = "data_validation", enabled = true )
    public void data_validation() throws InterruptedException {

        Data_validation data_validation = new Data_validation(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;


        wait.until(ExpectedConditions.visibilityOf(Data_validation.lbl_Data_validation));
        Data_validation.lbl_Data_validation.click();

        sleep(2000);
        Data_validation.lbl_Missi.click();

        Select Yr1 = new Select(Data_validation.dd_Year);
        Yr1.selectByIndex(1);


        Select Mth1 = new Select(Data_validation.dd_Month);
        Mth1.selectByIndex(3);


        sleep(1000);
        Data_validation.btn_submit.click();

        Data_validation.btn_icon.click();

        sleep(2000);
        Data_validation.btn_Questionnaire.click();

        sleep(2000);
        Data_validation.btn_close.click();

        sleep(2000);
        Data_validation.lbl_Data_entry.click();

        sleep(2000);
        Data_validation.lbl_Data_validation.click();

        sleep(2000);
        Data_validation.lbl_pps.click();

        Select Yr2 = new Select(Data_validation.dd_Year);
        Yr2.selectByIndex(1);


        Select Mth2 = new Select(Data_validation.dd_Month);
        Mth2.selectByIndex(1);

        sleep(1000);
        Data_validation.btn_submit.click();

        sleep(1000);
        Data_validation.btn_icon_pps.click();

        sleep(2000);
        Data_validation.btn_Questionnaire.click();

        sleep(2000);
        Data_validation.btn_close.click();

    }
}
