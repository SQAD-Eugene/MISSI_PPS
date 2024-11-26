package Package_test_by_User;

import Package_Element.*;
import com.google.gson.Gson;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import static org.testng.Assert.assertEquals;



public class missi_pps_DPS_RSSO {

    public static WebDriver driver;
    public static DataSet dataset;
    public static SoftAssert softAssert = new SoftAssert();

  @BeforeClass
    public void beforeClass() throws IOException {

        //Chrome Browser
        System.setProperty("webdriver.chrome.driver", "C:\\CDver\\chromedriver-win64 (7)\\chromedriver-win64\\chromedriver" + ".exe");;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //web link

        driver.get("http://192.168.26.104:81/public/login");

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("dataset.json"));
        dataset = gson.fromJson(reader, DataSet.class);

    }



   @Test(priority = 1, testName ="login")
   public static void  RSSO_login() throws InterruptedException, IOException {

       LoginPage login = new LoginPage(driver);
       WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(60));

       int iteration = 1;
       for (DataSet credentials : dataset.test_credentials_RSSO) {
           login.tf_emailAdd.sendKeys(credentials.EMAIL);
           login.tf_pw.sendKeys(credentials.PW);
           login.btn_login.click();

           if (iteration >= 1 && iteration <= 2) {
               eWait.until(ExpectedConditions.visibilityOf(login.modal_error));
               softAssert.assertTrue(login.modal_error.isDisplayed(), "Missing Error Modal");
               softAssert.assertTrue(login.btn_errorOK.isDisplayed(), "Error button OK is missing");
               login.btn_errorOK.click();

//                driver.switchTo().alert().accept();
           } else {
//                driver.navigate().refresh();
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


    @Test(priority = 2, testName = "dashboard")
    public static void dash_b() throws IOException, InterruptedException {


    }

    @Test(priority = 3, testName = "view_workload")
    public void V_workload() throws InterruptedException {

    }


    @Test(priority = 4, testName = "workload_referral")
    public void cawi_registration() throws InterruptedException {


    }

    @Test(priority = 5, testName = "reports")
    public void reports() throws InterruptedException {

    }

    @Test(priority = 6, testName = "list_of_sample_estab")
    public void list_of_sample_estab() throws InterruptedException {


    }
}