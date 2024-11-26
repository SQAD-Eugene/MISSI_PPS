package Package_test_by_module;

import Package_Element.DataSet;
import Package_Element.LoginPage;
import Package_Element.Rec_and_cont;
import Package_Element.View_workload;
import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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

public class View_workload_module {

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

    @Test(priority = 2, testName = "view_workload", enabled = true)
    public void view_workload() throws InterruptedException {
        View_workload view_workload = new View_workload(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(View_workload.lbl_Receipt_and_control));
        View_workload.lbl_Receipt_and_control.click();
        sleep(2000);

        
//        Package_Element.View_workload.lbl_Receipt_and_control.click();
        sleep(13000);
        View_workload.lbl_view_workload.click();
//        validate_inconsistent_Year_view_workload();
//        sleep(2000);
//        driver.switchTo().alert().accept();
//
//
//        sleep(2000);
//        driver.switchTo().window(driver.getWindowHandle());

//        softAssert.assertTrue(Package_Element.View_workload.lbl_Year.isDisplayed(), "Year label is missing");
//        String Y = dataset.YR;
//        assertEquals(Package_Element.View_workload.lbl_Year.getText(), Y);

        Select WL = new Select(View_workload.dd_Year);

        WL.selectByIndex(0);
        WL.selectByIndex(1);




        softAssert.assertTrue(View_workload.lbl_Month.isDisplayed(), "Month label is missing");
        String M = dataset.M;
        assertEquals(View_workload.lbl_Month.getText(), M);

        Select VW = new Select(View_workload.dd_Month);

        VW.selectByIndex(0);
        VW.selectByIndex(1);


        softAssert.assertTrue(View_workload.lbl_Survey.isDisplayed(), "Survey label is missing");
        String sur = dataset.SURVEY;
        assertEquals(View_workload.lbl_Survey.getText(), sur);

        Select srv = new Select(View_workload.dd_Survey);

        srv.selectByIndex(0);
        srv.selectByIndex(1);


        View_workload.btn_Search_PO.click();
        sleep(3000);
        try{
            sleep(2000);
            softAssert.assertTrue(View_workload.lbl_Page.isDisplayed(), "Per page label is missing");
            String pg = dataset.PG;
            assertEquals(View_workload.lbl_Page.getText(), pg);
        }catch (Exception e ){
            System.out.println("Page label is not displayed - Fail");
        }


        Select Pg = new Select(View_workload.dd_Page);
        Pg.selectByIndex(0);
        Pg.selectByIndex(1);
        Pg.selectByIndex(2);
        Pg.selectByIndex(3);
        Pg.selectByIndex(4);


        sleep(13000);
        softAssert.assertTrue(View_workload.lbl_Search.isDisplayed(), "Search label is missing");
        String srh = dataset.SRH;
        assertEquals(View_workload.lbl_Search.getText(), srh);
        View_workload.txt_Search.sendKeys(dataset.ECN_PO);

        System.out.println("Search label is not displayed - Fail");



        wait.until(ExpectedConditions.visibilityOf(View_workload.btn_log_CO));

        View_workload.btn_log_CO.click();
        softAssert.assertTrue(View_workload.lbl_Company.isDisplayed(), "Company name label is missing");
        softAssert.assertTrue(View_workload.lbl_ECN.isDisplayed(), "ECN label is missing");
        softAssert.assertTrue(View_workload.lbl_industry.isDisplayed(), "Industry label is missing");
        softAssert.assertTrue(View_workload.lbl_Prov.isDisplayed(), "Province name label is missing");
        softAssert.assertTrue(View_workload.lbl_Mun.isDisplayed(), "Municipality name label is missing");
        softAssert.assertTrue(View_workload.lbl_Bry.isDisplayed(), "Barangay name label is missing");

        WebElement dis = View_workload.dd_Date_Distribution;
        Actions actions = new Actions(driver);
        actions.doubleClick(dis).perform();
        System.out.println("Distribution date displayed - PASS ");

//        for (int f = 1; f <= 2; f++) {
//            sleep(1000);
//            String formattedStr = String.format("%02d", f);
//            driver.findElement(By.xpath("//*[@id=\"__BVID__84__cell-2022-09-" + formattedStr + "_\"]/span")).click();
//            Package_Element.View_workload.dd_Date_Distribution.click();
//        }


        View_workload.dd_Date_Collection.click();
        WebElement col = View_workload.dd_Date_Collection;
        Actions actions1 = new Actions(driver);
        actions1.doubleClick(col).perform();
        System.out.println("Collection date displayed - PASS ");

//        for (int f = 1; f <= 2; f++) {    //*[@id="__BVID__90__cell-2022-09-01_"]/span
//            sleep(1000);
//            String formattedStr = String.format("%02d", f);
//            driver.findElement(By.xpath("//*[@id=\"__BVID__90__cell-2022-09-" + formattedStr + "_\"]/span")).click();
//            Package_Element.View_workload.dd_Date_Collection.click();
//        }

        View_workload.dd_Date_Receipt_PO.click();
        WebElement Rep = View_workload.dd_Date_Receipt_PO;
        Actions actions2 = new Actions(driver);
        actions2.doubleClick(Rep).perform();
        System.out.println("Receipt date displayed - PASS ");

//        for (int f = 1; f <= 2; f++) {
//            sleep(1000);
//            String formattedStr = String.format("%02d", f);
//            driver.findElement(By.xpath("//*[@id=\"__BVID__94__cell-2022-09-" + formattedStr + "_\"]/span")).click();
//            Package_Element.View_workload.dd_Date_Receipt_PO.click();
//        }

        Select stat = new Select(View_workload.dd_Status);
        stat.selectByIndex(0);
        stat.selectByIndex(13);

        sleep(1000);
        Select Ref_Prov = new Select(View_workload.dd_Prov);
        Ref_Prov.selectByIndex(0);
        Ref_Prov.selectByIndex(1);

        sleep(1000);
        Select Ref_Mun = new Select(View_workload.dd_Mun);
        Ref_Mun.selectByIndex(0);
        Ref_Mun.selectByIndex(4);

        sleep(1000);
        Select Ref_Bgy = new Select(View_workload.dd_Bgy);
        Ref_Bgy.selectByIndex(0);
        Ref_Bgy.selectByIndex(1);

        View_workload.txt_Cont_P.sendKeys(dataset.NAME);
        View_workload.txt_Cont_N.sendKeys(dataset.CONT);
        View_workload.txt_Business_Add.sendKeys(dataset.NAME);

        View_workload.txt_coll_Name.clear();
        View_workload.txt_coll_Name.sendKeys(dataset.NAME);

        View_workload.txt_Remarks.clear();
        View_workload.txt_Remarks.sendKeys(dataset.NAME);

        Actions act = new Actions(driver);
        act.sendKeys(Keys.PAGE_UP).build().perform();
        sleep(1000);
        View_workload.btn_close.click();

        sleep(1000);
        View_workload.btn_view_PO.click();

        sleep(1000);
        View_workload.btn_close_view.click();

    }

    //Validate inconsistency
    static void validate_inconsistent_Year_view_workload() throws InterruptedException {
        Select WL = new Select(View_workload.dd_Year);
        WL.selectByIndex(4);
        Select VW = new Select(View_workload.dd_Month);
        VW.selectByIndex(7);
        Select srv = new Select(View_workload.dd_Survey);
        srv.selectByIndex(1);
        Select Prv = new Select(View_workload.dd_Province);
        Prv.selectByIndex(8);
        Rec_and_cont.btn_Search.click();
        sleep(3000);
//        Package_Element.Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("Inconsistent Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        // without selecting Year
        Select WL1 = new Select(View_workload.dd_Year);
        WL1.selectByIndex(0);
        Select VW1 = new Select(View_workload.dd_Month);
        VW1.selectByIndex(7);
        Select srv1 = new Select(View_workload.dd_Survey);
        srv1.selectByIndex(1);
        Select Prv1 = new Select(View_workload.dd_Province);
        Prv1.selectByIndex(8);
        View_workload.btn_Search.click();
        sleep(3000);
        Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("No Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        //without selecting Month
        Select WL2 = new Select(View_workload.dd_Year);
        WL2.selectByIndex(1);
        Select VW2 = new Select(View_workload.dd_Month);
        VW2.selectByIndex(0);
        Select srv2 = new Select(View_workload.dd_Survey);
        srv2.selectByIndex(1);
        Select Prv2 = new Select(View_workload.dd_Province);
        Prv2.selectByIndex(8);
        View_workload.btn_Search.click();
        sleep(3000);
        Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("No Month selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        //without selecting survey form
        Select WL3 = new Select(View_workload.dd_Year);
        WL3.selectByIndex(1);
        Select VW3 = new Select(View_workload.dd_Month);
        VW3.selectByIndex(7);
        Select srv3 = new Select(View_workload.dd_Survey);
        srv3.selectByIndex(0);
        Select Prv3 = new Select(View_workload.dd_Province);
        Prv3.selectByIndex(8);
        View_workload.btn_Search.click();
        sleep(3000);
        Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("No Survey Form selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        // without selecting Province
        Select WL4 = new Select(View_workload.dd_Year);
        WL4.selectByIndex(1);
        Select VW4 = new Select(View_workload.dd_Month);
        VW4.selectByIndex(7);
        Select srv4 = new Select(View_workload.dd_Survey);
        srv4.selectByIndex(1);
        Select Prv4 = new Select(View_workload.dd_Province);
        Prv4.selectByIndex(0);
        View_workload.btn_Search.click();
        sleep(3000);
//        Package_Element.Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("No Province selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }
    }

}
