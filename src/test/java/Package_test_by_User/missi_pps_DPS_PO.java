package Package_test_by_User;

import Package_Element.*;
import com.google.gson.Gson;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
import java.util.Objects;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class missi_pps_DPS_PO {
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
//        driver.get("https://sqad-test.psa.gov.ph/");
        driver.get("https://missippsdev-dps.psa.gov.ph/login");
//        driver.get("https://dev.psa.gov.ph/missi_pps/dps/login");

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("dataset.json"));
        dataset = gson.fromJson(reader, DataSet.class);

    }


    @Test(priority = 1, testName = "Login")
    public void testLogin() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(60));


//        eWait .until(ExpectedConditions.visibilityOf(login.btn_login_btn));
//        login.btn_login_btn.click();

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

    @Test(priority = 2, testName = "dashboard",enabled = false)
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

    @Test(priority = 3, testName = "rec_and_cont", enabled = true)
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

    @Test(priority = 4, testName = "view_workload", enabled = true)
    public void view_workload() throws InterruptedException {
        View_workload view_workload = new View_workload(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

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
        WL.selectByIndex(2);



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


    @Test(priority = 5, testName = "cawi_registration" , enabled = true)
    public void registration() throws InterruptedException {

        Cawi_Registration cawi_registration = new Cawi_Registration(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

//       validate_inconsistent_Year_Cawi_Reg();

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


    @Test(priority = 6, testName = "workload_referral" , enabled = true)
    public void cawi_registration() throws InterruptedException{

        Workload_Referral workload_referral = new Workload_Referral(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;


        sleep(13000);
        Workload_Referral.lbl_Workload_Referral.click();

        validate_inconsistent_Year_workload_Ref();

        Select yr = new Select(Workload_Referral.dd_Year);
        yr.selectByIndex(1);

        Select srv = new Select(Workload_Referral.dd_Survey);
        srv.selectByIndex(1);

        Select ref = new Select(Workload_Referral.dd_Referral);
        ref.selectByIndex(0);

        Workload_Referral.btn_Search.click();

        sleep(1000);
        Select P_page = new Select(Workload_Referral.dd_P_PAge);

        P_page.selectByIndex(0);
        P_page.selectByIndex(1);
        P_page.selectByIndex(2);
        P_page.selectByIndex(3);
        P_page.selectByIndex(4);
        P_page.selectByIndex(1);

        Workload_Referral.txt_Search.sendKeys(dataset.SRC_TXT);


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

    @Test(priority = 7, testName = "data_entry" , enabled = true)
    public void data_entry() throws InterruptedException {

        Data_Entry data_entry = new Data_Entry(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        Data_Entry.lbl_Data_entry.click();

        sleep(1000);
        Data_Entry.lbl_Missi.click();

        sleep(1000);
        Select Yr = new Select(Data_Entry.dd_Year);

        Yr.selectByIndex(0);
        Yr.selectByIndex(1);

        Select Mth = new Select(Data_Entry.dd_Month);

        Mth.selectByIndex(0);
        Mth.selectByIndex(1);
        Mth.selectByIndex(2);

        try {
            Data_Entry.txt_ecn.sendKeys(dataset.ECN);

            Data_Entry.btn_Submit.click();

        } catch (Exception e) {

            System.out.println("Disabled ECN Text box Data entry for Missi will not open. ");

        }
        sleep(2000);
        Data_Entry.btn_Back_Dashboard.click();

        Data_Entry.lbl_Data_entry.click();

        sleep(2000);
        Data_Entry.lbl_PPS.click();

        sleep(1000);
        Select Yr1 = new Select(Data_Entry.dd_Year);

        Yr1.selectByIndex(0);
        Yr1.selectByIndex(1);

        Select Mth1 = new Select(Data_Entry.dd_Month);

        Mth1.selectByIndex(0);
        Mth1.selectByIndex(1);
        Mth1.selectByIndex(2);

        sleep(1000);
        Data_Entry.txt_ecn.sendKeys(dataset.ECN);

        Data_Entry.btn_Submit.click();

        Data_Entry.btn_Back_Dashboard.click();
    }


    @Test(priority = 8, testName = "data_validation", enabled = true)
    public void data_validation() throws InterruptedException {

        Data_validation data_validation = new Data_validation(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

try {

    Data_validation.lbl_Data_validation.click();

    sleep(2000);
    Data_validation.lbl_Missi.click();

    Select Yr1 = new Select(Data_validation.dd_Year);

    Yr1.selectByIndex(0);
    Yr1.selectByIndex(1);
    Yr1.selectByIndex(2);

    Select Mth1 = new Select(Data_validation.dd_Month);

    Mth1.selectByIndex(0);
    Mth1.selectByIndex(1);


    sleep(1000);
    Data_validation.btn_submit.click();

    Data_validation.btn_icon.click();

    sleep(2000);
    Data_validation.btn_Questionnaire.click();

    Data_validation.btn_close.click();
}catch (Exception e){
    System.out.println(e);
}
 Data_validation.btn_Back_DashboardPO.click();
    }

    @Test(priority = 9, testName = "reports", enabled = false)
    public void reports() throws InterruptedException {

        Reports reports = new Reports(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        Reports.lbl_Reports.click();


        sleep(3000);
        Reports.lbl_Monitoring.click();

        Select Sur1 = new Select(Reports.dd_Survey);
        Sur1.selectByIndex(0);
        Sur1.selectByIndex(1);

        Select Yr1 = new Select(Reports.dd_Year);
        Yr1.selectByIndex(0);
        Yr1.selectByIndex(2);

        Select Mth1 = new Select(Reports.dd_Month);
        Mth1.selectByIndex(0);
        Mth1.selectByIndex(12);

        sleep(2000);
        Select Cat1 = new Select(Reports.dd_Category);
        Cat1.selectByIndex(0);
        Cat1.selectByIndex(3);

        Reports.btn_Search.click();
        sleep(2000);

        Reports.lbl_Control_list.click();

        Select Sur2 = new Select(Reports.dd_Survey);
        Sur2.selectByIndex(0);
        Sur2.selectByIndex(1);

        Select Yr2 = new Select(Reports.dd_Year);
        Yr2.selectByIndex(0);
        Yr2.selectByIndex(2);

        Select Mth2 = new Select(Reports.dd_Month);
        Mth2.selectByIndex(0);
        Mth2.selectByIndex(12);

        sleep(2000);
        Select Cat2 = new Select(Reports.dd_Control_List);
        Cat2.selectByIndex(0);
        Cat2.selectByIndex(1);


        Reports.btn_Search.click();

        sleep(2000);
    }

    @Test(priority = 10, testName = "view_data", enabled = false)
    public void view_data() throws InterruptedException {

        View_Data view_data = new View_Data(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

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

    @Test(priority = 11, testName = "list_of_sample_estab", enabled = false)
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


    @Test(priority = 12, testName = "tracking_changes_data_entry", enabled = false)
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

        @Test(priority = 13, testName = "utilities", enabled = false)
    public void utilities() throws InterruptedException {

        Utilities utilities = new Utilities(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

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

        sleep(1000);
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
