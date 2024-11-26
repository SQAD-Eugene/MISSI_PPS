package Package_test_by_module;

import Package_Element.DataSet;
import Package_Element.LoginPage;
import Package_Element.Rec_and_cont;
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
import static org.testng.Assert.assertEquals;

public class Workload_Assignment_module {
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

    @Test(priority = 2, testName = "rec_and_cont", enabled = true)
    public void rec_and_cont() throws InterruptedException {

        Rec_and_cont test_rec_and_cont = new Rec_and_cont(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(Rec_and_cont.lbl_Receipt_and_control));
        Rec_and_cont.lbl_Receipt_and_control.click();
        sleep(2000);

        softAssert.assertTrue(Rec_and_cont.lbl_work_assign_PO_Sup.isDisplayed(), "Workload Assignment label is missing");
        String work_ass = dataset.WORK_ASS;
        assertEquals(Rec_and_cont.lbl_work_assign_PO_Sup.getText(), work_ass);

        softAssert.assertTrue(Rec_and_cont.lbl_view_workload.isDisplayed(), "View Workload label is missing");
        String view_work = dataset.VIEW_WORK;
        assertEquals(Rec_and_cont.lbl_view_workload.getText(), view_work);

        softAssert.assertTrue(Rec_and_cont.lbl_cawi_reg.isDisplayed(), "Cawi Registration label is missing");
        String cawi_reg = dataset.CAWI_REG;
        assertEquals(Rec_and_cont.lbl_cawi_reg.getText(), cawi_reg);

        softAssert.assertTrue(Rec_and_cont.lbl_work_ref.isDisplayed(), "Work Referral label is missing");
        String work_ref = dataset.WORK_REF;
        assertEquals(Rec_and_cont.lbl_work_ref.getText(), work_ref);
        sleep(1000);
        Rec_and_cont.lbl_work_assign.click();


        sleep(2000);

        //validation inconsistency and blank entry

        //WorkLoad Assignment
        sleep(1000);
        softAssert.assertTrue(Rec_and_cont.lbl_Year.isDisplayed(), "Year label is missing");
        String Y = dataset.YR;
        assertEquals(Rec_and_cont.lbl_Year.getText(), Y);
        Select WL = new Select(Rec_and_cont.dd_Year);
        WL.selectByIndex(1);

        softAssert.assertTrue(Rec_and_cont.lbl_Province.isDisplayed(), "Province label is missing");
        String prov = dataset.OFF;
        assertEquals(Rec_and_cont.lbl_Province.getText(), prov);
        Select Prv = new Select(Rec_and_cont.dd_Province);
        Prv.selectByIndex(1);

        softAssert.assertTrue(Rec_and_cont.lbl_Province.isDisplayed(), "Municipality label is missing");
        String mun = dataset.MUN;
        assertEquals(Rec_and_cont.lbl_Municipality.getText(), mun);
        Select Mun = new Select(Rec_and_cont.dd_Municipality);
        Mun.selectByIndex(0);
        Mun.selectByIndex(13);

        softAssert.assertTrue(Rec_and_cont.lbl_Survey.isDisplayed(), "Survey label is missing");
        String sur = dataset.SURVEY;
        assertEquals(Rec_and_cont.lbl_Survey.getText(), sur);
        Select srv = new Select(Rec_and_cont.dd_Survey);
        srv.selectByIndex(0);
        srv.selectByIndex(1);
        Rec_and_cont.btn_Search.click();

        wait.until(ExpectedConditions.visibilityOf(Rec_and_cont.lbl_Page));
        softAssert.assertTrue(Rec_and_cont.lbl_Page.isDisplayed(), "Per Page label is missing");
        String pg = dataset.PG;
        assertEquals(Rec_and_cont.lbl_Page.getText(), pg);

        Select Pge = new Select(Rec_and_cont.dd_Page);
        Pge.selectByIndex(0);
        Pge.selectByIndex(1);
        Pge.selectByIndex(2);
        Pge.selectByIndex(3);
        Pge.selectByIndex(4);

        softAssert.assertTrue(Rec_and_cont.lbl_Search.isDisplayed(), "Search label is missing");
        String srh = dataset.SRH;
        assertEquals(Rec_and_cont.lbl_Search.getText(), srh);

        softAssert.assertTrue(Rec_and_cont.txt_Search.isDisplayed(), "Search label is missing");

        for (DataSet test2 : dataset.test1) {
            Rec_and_cont.txt_Search.sendKeys(test2.SRCH);
            sleep(3000);
            Rec_and_cont.btn_Filter.click();
        }

        sleep(4000);
        softAssert.assertTrue(Rec_and_cont.lbl_assgnd_not_assgnd.isDisplayed(), "Per Page label is missing");
        String ass = dataset.ASS;
        assertEquals(Rec_and_cont.lbl_assgnd_not_assgnd.getText(), ass);

        sleep(6000);
        Select assg = new Select(Rec_and_cont.dd_assgnd_not_assgnd);
        assg.selectByValue("assigned");
        assg.selectByValue("not assigned");

        Rec_and_cont.btn_Assign.click();

//        Package_Element.Rec_and_cont.btn_YES.click();

        sleep(5000);

        Select List_PO = new Select(Rec_and_cont.dd_List_PO_Staff);
        List_PO.selectByIndex(3);



        wait.until(ExpectedConditions.visibilityOf(Rec_and_cont.btn_Assign_modal));
        Rec_and_cont.btn_Assign_modal.click();
        System.out.println("The Workload Assigned successful");

        sleep(13000);
//        wait.until(ExpectedConditions.elementToBeClickable(Package_Element.Rec_and_cont.btn_close_modal));
        Rec_and_cont.btn_close_modal.click();

        Rec_and_cont.btn_remove.click();

        wait.until(ExpectedConditions.elementToBeClickable(Rec_and_cont.btn_remove_Yes));
        Rec_and_cont.btn_remove_Yes.click();
        System.out.println("The Workload Removed");

    }


}
