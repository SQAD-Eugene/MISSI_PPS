package Package_test_by_module;

import Package_Element.Dashboard;
import Package_Element.DataSet;
import Package_Element.LoginPage;
import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Dashboard_module {

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
    public void testLogin() throws InterruptedException {
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

    @Test(priority = 2, testName = "dashboard",enabled = true)
    public void d_board() throws InterruptedException {

        Dashboard dash_board = new Dashboard(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        sleep(1000);
        wait.until(ExpectedConditions.visibilityOfAllElements(Dashboard.lbl_po_name_PO, Dashboard.lbl_po_position_PO, Dashboard.lbl_po_location_PO,
                Dashboard.lbl_dashboard_PO, Dashboard.lbl_Receipt_and_control_PO, Dashboard.lbl_Utilities_PO, Dashboard.lbl_dataentry_PO, Dashboard.lbl_logout_PO));

        softAssert.assertTrue(Dashboard.lbl_po_sup_name.isDisplayed(), "PO Name label is missing");
        String nSupervisor = dataset.N_SUPERVISOR_PO;
        Assert.assertEquals(Dashboard.lbl_po_sup_name.getText(), nSupervisor);

        softAssert.assertTrue(Dashboard.lbl_po_sup_position.isDisplayed(), "Position label is missing");
        String position = dataset.POSITION_PO;
        Assert.assertEquals(Dashboard.lbl_po_sup_position.getText(), position);

        softAssert.assertTrue(Dashboard.lbl_po_sup_location.isDisplayed(), "Location label is missing");
        String location = dataset.LOCATION_PO;
        Assert.assertEquals(Dashboard.lbl_po_sup_location.getText(), location);

        softAssert.assertTrue(Dashboard.lbl_dashboard_PO.isDisplayed(),"Package_Element.Dashboard label is missing");
        String dashboards = dataset.DASHBOARD;
        Assert.assertEquals(Dashboard.lbl_dashboard_PO.getText(),dashboards);

        softAssert.assertTrue(Dashboard.lbl_Receipt_and_control_PO.isDisplayed(),"Receipt and control label is missing");
        String receipt_and_control = dataset.RECEIPT_and_CONTROL;
        Assert.assertEquals(Dashboard.lbl_Receipt_and_control_PO.getText(),receipt_and_control);

        softAssert.assertTrue(Dashboard.lbl_Utilities_PO.isDisplayed(),"Package_Element.Utilities label is missing");
        String utilities = dataset.UTILITIES;
        Assert.assertEquals(Dashboard.lbl_Utilities_PO.getText(),utilities);

        softAssert.assertTrue(Dashboard.lbl_dataentry_PO.isDisplayed(),"Data entry label is missing");
        String data_entry = dataset.DATA_ENTRY;
        Assert.assertEquals(Dashboard.lbl_dataentry_PO.getText(),data_entry);

        softAssert.assertTrue(Dashboard.lbl_logout_PO.isDisplayed(),"Logout label is missing");
        String logout = dataset.LOGOUT;
        Assert.assertEquals(Dashboard.lbl_logout_PO.getText(),logout);
        softAssert.assertAll();

        softAssert.assertTrue(Dashboard.switch_questionnaire.isDisplayed());
        sleep(2000);
        Dashboard.switch_questionnaire.click();

        softAssert.assertTrue(Dashboard.GRAP_PPS_Prov_Status.isDisplayed());
        String PPS = dataset.Prov_GRAP_Status_PPS;

        Dashboard.lbl_Receipt_and_control.click();

    }

}
