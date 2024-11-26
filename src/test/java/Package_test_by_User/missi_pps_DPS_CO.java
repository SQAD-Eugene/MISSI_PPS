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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

public class missi_pps_DPS_CO {

    static WebDriver driver;
    static DataSet dataset;
    SoftAssert softAssert = new SoftAssert();

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
        driver.get("http://192.168.26.104:81/public/login");

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("dataset.json"));
        dataset = gson.fromJson(reader, DataSet.class);

    }

    @Test(priority = 1, testName = "Login")
    public void testLogin() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(60));


        int iteration = 1;
        for (DataSet credentials : dataset.test_credentialsCO) {
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
//                wait.until(ExpectedConditions.visibilityOfAllElements(login.lbl_email, login.lbl_pw));
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


    @Test(enabled = false)
    //@Test(priority = 2, testName = "dashboard")
    public void d_board() throws InterruptedException {


        Dashboard dashboard = new Dashboard(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        sleep(2000);
//        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.lbl_co_name, dashboard.lbl_co_position, dashboard.lbl_co_location,
//                dashboard.lbl_dashboard, dashboard.lbl_receipt_and_control, dashboard.lbl_Utilities, dashboard.lbl_dataentry, dashboard.lbl_logout));

        softAssert.assertTrue(Dashboard.lbl_co_name.isDisplayed(), "CO Name label is missing");
        String nSupervisor = dataset.N_SUPERVISOR;
        assertEquals(Dashboard.lbl_co_name.getText(), nSupervisor);
        if (Dashboard.lbl_co_name.isDisplayed()){
            System.out.println("CO Username is displayed - PASS");
        }else{
            System.out.println("CO Username is not displayed - FAIL");
        }


        softAssert.assertTrue(Dashboard.lbl_co_position.isDisplayed(), "Position label is missing");
        String position = dataset.POSITION;
        assertEquals(Dashboard.lbl_co_position.getText(), position);
        if (Dashboard.lbl_co_position.isDisplayed()){
            System.out.println("CO Position is displayed - PASS");
        }else{
            System.out.println("CO Position is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_co_location.isDisplayed(), "Location label is missing");
        String location = dataset.LOCATION;
        assertEquals(Dashboard.lbl_co_location.getText(), location);
        if (Dashboard.lbl_co_location.isDisplayed()){
            System.out.println("CO Location is displayed - PASS");
        }else{
            System.out.println("CO Location is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_dashboard.isDisplayed(), "Package_Element.Dashboard label is missing");
        String dashboards = dataset.DASHBOARD;
        assertEquals(Dashboard.lbl_dashboard.getText(), dashboards);
        if (Dashboard.lbl_dashboard.isDisplayed()){
            System.out.println("Package_Element.Dashboard label is displayed - PASS");
        }else{
            System.out.println("Package_Element.Dashboard label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_Receipt_and_control_CO.isDisplayed(), "Receipt and control label is missing");
        String receipt_and_control = dataset.RECEIPT_and_CONTROL;
        assertEquals(Dashboard.lbl_Receipt_and_control_CO.getText(), receipt_and_control);
        if (Dashboard.lbl_Receipt_and_control_CO.isDisplayed()){
            System.out.println("Receipt and Control label is displayed - PASS");
        }else{
            System.out.println("Receipt and Control label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_Utilities.isDisplayed(), "Package_Element.Utilities label is missing");
        String utilities = dataset.UTILITIES;
        assertEquals(Dashboard.lbl_Utilities.getText(), utilities);
        if (Dashboard.lbl_Utilities.isDisplayed()){
            System.out.println("Package_Element.Utilities label is displayed - PASS");
        }else{
            System.out.println("Package_Element.Utilities label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_dataentry.isDisplayed(), "Data entry label is missing");
        String data_entry = dataset.DATA_ENTRY;
        assertEquals(Dashboard.lbl_dataentry.getText(), data_entry);
        if (Dashboard.lbl_dataentry.isDisplayed()){
            System.out.println("Data entry label is displayed - PASS");
        }else{
            System.out.println("Data entry label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_logout.isDisplayed(), "Logout label is missing");
        String logout = dataset.LOGOUT;
        assertEquals(Dashboard.lbl_logout.getText(), logout);
        if (Dashboard.lbl_logout.isDisplayed()){
            System.out.println("Logout label is displayed - PASS");
        }else{
            System.out.println("Logout label is not displayed - FAIL");
        }


        sleep(2000);
        softAssert.assertTrue(Dashboard.btn_Nat.isDisplayed());
        String National = dataset.NATIONAL;
        assertEquals(Dashboard.btn_Nat.getText(), National);
        if (Dashboard.btn_Nat.isDisplayed()){
            System.out.println("National Button is displayed - PASS");
            sleep(1000);
            assertEquals(Dashboard.GRAP_MISSI_SUMMARY.getText(), dataset.Nat_GRAP_SUMMARY);
            assertEquals(Dashboard.GRAP_MISSI_RATE_SAMPLE.getText(), dataset.Nat_GRAP_Response_Rate_Samp);
            assertEquals(Dashboard.GRAP_MISSI_RATE_IND.getText(), dataset.Nat_GRAP_Response_Rate_Ind);
        }else{
            System.out.println("National Button is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.btn_Reg.isDisplayed());
        String Regional = dataset.REGION;
        assertEquals(Dashboard.btn_Reg.getText(), Regional);
        if (Dashboard.btn_Reg.isDisplayed()){
            Dashboard.btn_Reg.click();
            System.out.println("Regional Button is displayed - PASS");
            assertEquals(Dashboard.GRAP_MISSI_Reg_Status.getText(), dataset.Reg_GRAP_Status_MISSI);
        }else{
            System.out.println("Regional Button is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.btn_Prov.isDisplayed());
        String Prov = dataset.PROVINCE;
        assertEquals(Dashboard.btn_Prov.getText(), Prov);
        if (Dashboard.btn_Prov.isDisplayed()){
            Dashboard.btn_Prov.click();
            System.out.println("Province Button is displayed - PASS");
            assertEquals(Dashboard.GRAP_MISSI_Prov_Status.getText(), dataset.Prov_GRAP_Status_MISSI);
        }else{
            System.out.println("Province Button is not displayed - FAIL");
        }
        softAssert.assertAll();
    }

    @Test(priority = 3, testName = "rec_and_cont")
    public void rec_and_cont() throws InterruptedException {

        Rec_and_cont rec_and_cont = new Rec_and_cont(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOfAllElements(Rec_and_cont.lbl_Receipt_and_control));
        Rec_and_cont.lbl_Receipt_and_control.click();
        sleep(2000);

        wait.until(ExpectedConditions.visibilityOfAllElements(Rec_and_cont.lbl_work_assign, Rec_and_cont.lbl_view_workload, Rec_and_cont.lbl_cawi_reg, Rec_and_cont.lbl_work_ref));

        //Receipt and control
        softAssert.assertTrue(Rec_and_cont.lbl_work_assign.isDisplayed());
        String work_ass = dataset.WORK_ASS;
        assertEquals(Rec_and_cont.lbl_work_assign.getText(), work_ass);
        if (Rec_and_cont.lbl_work_assign.isDisplayed()){
            System.out.println("Workload Assignment label is displayed - PASS");
        }else{
            System.out.println("Workload Assignment label is not displayed - FAIL");
        }


        softAssert.assertTrue(Rec_and_cont.lbl_view_workload.isDisplayed());
        String view_work = dataset.VIEW_WORK;
        assertEquals(Rec_and_cont.lbl_view_workload.getText(), view_work);
        if (Rec_and_cont.lbl_view_workload.isDisplayed()){
            System.out.println("View Workload label is displayed - PASS");
        }else{
            System.out.println("View Workload label is not displayed - FAIL");
        }

        softAssert.assertTrue(Rec_and_cont.lbl_cawi_reg.isDisplayed(), "Cawi Registration label is missing");
        String cawi_reg = dataset.CAWI_REG;
        assertEquals(Rec_and_cont.lbl_cawi_reg.getText(), cawi_reg);
        if (Rec_and_cont.lbl_cawi_reg.isDisplayed()){
            System.out.println("Cawi Registration label is displayed - PASS");
        }else{
            System.out.println("Cawi Registration label is not displayed - FAIL");
        }

        softAssert.assertTrue(Rec_and_cont.lbl_work_ref.isDisplayed(), "Work Referral label is missing");
        String work_ref = dataset.WORK_REF;
        assertEquals(Rec_and_cont.lbl_work_ref.getText(), work_ref);
        if (Rec_and_cont.lbl_work_ref.isDisplayed()){
            System.out.println("Work Referral label is displayed - PASS");
        }else{
            System.out.println("Work Referral label is not displayed - FAIL");
        }

        Rec_and_cont.lbl_work_assign.click();

        sleep(2000);

        //validation inconsistency and blank entry
        validate_inconsistency_and_blank_entry();

        //WorkLoad Assignment
        softAssert.assertTrue(Rec_and_cont.lbl_Year.isDisplayed(), "Year label is missing");
        String Y = dataset.YR;
        assertEquals(Rec_and_cont.lbl_Year.getText(), Y);

        Select WL = new Select(Rec_and_cont.dd_Year);
        WL.selectByIndex(1);


        softAssert.assertTrue(Rec_and_cont.lbl_Province.isDisplayed(), "Province label is missing");
        String prov = dataset.OFF;
        assertEquals(Rec_and_cont.lbl_Province.getText(), prov);

        Select Prv = new Select(Rec_and_cont.dd_Province);

        softAssert.assertTrue(Rec_and_cont.lbl_Province.isDisplayed(), "Municipality label is missing");
        String mun = dataset.MUN;
        assertEquals(Rec_and_cont.lbl_Municipality.getText(), mun);

        Prv.selectByIndex(2);
        Select Mun = new Select(Rec_and_cont.dd_Municipality);
        Mun.selectByIndex(0);
        Mun.selectByIndex(1);


        softAssert.assertTrue(Rec_and_cont.lbl_Survey.isDisplayed(), "Survey label is missing");
        String sur = dataset.SURVEY;
        assertEquals(Rec_and_cont.lbl_Survey.getText(), sur);

        Select srv = new Select(Rec_and_cont.dd_Survey);

        srv.selectByIndex(0);
        srv.selectByIndex(1);
        srv.selectByIndex(2);
        srv.selectByIndex(1);

    Rec_and_cont.btn_Search.click();

    sleep(7000);
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
        sleep(2000);
        Rec_and_cont.btn_Filter.click();
        Rec_and_cont.txt_Search.clear();
    }

    softAssert.assertTrue(Rec_and_cont.lbl_assgnd_not_assgnd.isDisplayed(), "Per Page label is missing");
    String ass = dataset.ASS;
    assertEquals(Rec_and_cont.lbl_assgnd_not_assgnd.getText(), ass);

    Select assg = new Select(Rec_and_cont.dd_assgnd_not_assgnd);
    assg.selectByIndex(0);
    assg.selectByIndex(1);
    assg.selectByIndex(2);

    sleep(2000);

//    Package_Element.Rec_and_cont.btn_Assign.click();
//
//    sleep(2000);
//
//    Select List_PO = new Select(Package_Element.Rec_and_cont.dd_List_PO_Staff);
//    List_PO.selectByIndex(0);
//    List_PO.selectByIndex(1);
//    List_PO.selectByIndex(2);
//    List_PO.selectByIndex(3);
//    List_PO.selectByIndex(4);
//
//    sleep(5000);
//    Package_Element.Rec_and_cont.btn_Assign_modal.click();
//
//    sleep(2000);
//    Package_Element.Rec_and_cont.btn_close_modal_CO.click();
//    sleep(10000);
//    Package_Element.Rec_and_cont.btn_remove_CO.click();
//    sleep(2000);
//    Package_Element.Rec_and_cont.btn_remove_Yes.click();
//
//    sleep(6000);
////    validate_reassign_workload();
    }

    public static void validate_reassign_workload() throws InterruptedException {
        sleep(2000);
        Rec_and_cont.btn_Reassign_workload_CO.click();
        Rec_and_cont.btn_Reassign_Workload_modal_Yes_CO.click();
        sleep(1000);

        try {
            Select List_CO = new Select(Rec_and_cont.btn_Reassign_workload_List_of_PO_staff_CO);
            List_CO.selectByIndex(0);
            List_CO.selectByIndex(1);
            List_CO.selectByIndex(2);
            List_CO.selectByIndex(3);
        } catch (Exception e) {
            System.out.println("No Staff display in Assign workload dropdown box");
        }

        Rec_and_cont.btn_Reassign_workload_CO_modal.click();

        sleep(2000);
        Rec_and_cont.btn_reassign_close_modal.click();
    }

    //validation inconsistency
    public static void validate_inconsistency_and_blank_entry() throws InterruptedException {

//       without selecting Year
        Select WL1 = new Select(Rec_and_cont.dd_Year);
        System.out.println("VALIDATE Without selecting Year");
        WL1.selectByIndex(0);
        sleep(1000);
        Select Prv1 = new Select(Rec_and_cont.dd_Province);
        Prv1.selectByIndex(2);
        sleep(1000);
        Select Mun81 = new Select(Rec_and_cont.dd_Municipality);
        Mun81.selectByIndex(1);
        sleep(1000);
        Select srv1 = new Select(Rec_and_cont.dd_Survey);
        srv1.selectByIndex(1);
        sleep(1000);
        Rec_and_cont.btn_Search.click();
        System.out.println("Search is clicked - PASS");
        sleep(3000);
        Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("No Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

//without selecting Province and Municipality
        System.out.println("Validate without selecting Province and Municipality");
        Select WL2 = new Select(Rec_and_cont.dd_Year);
        WL2.selectByIndex(1);
        sleep(1000);
        Select Prv2 = new Select(Rec_and_cont.dd_Province);
        Prv2.selectByIndex(0);
        sleep(1000);
        Select Mun82 = new Select(Rec_and_cont.dd_Municipality);
        Mun82.selectByIndex(0);
        sleep(1000);
        Select srv2 = new Select(Rec_and_cont.dd_Survey);
        srv2.selectByIndex(1);
        sleep(1000);
        Rec_and_cont.btn_Search.click();
        sleep(3000);
        Rec_and_cont.btn_Error_ok.click();
        try{
           Rec_and_cont.btn_Error_ok.isDisplayed();
           System.out.println("No Prov and Mun selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

//without selecting survey form
        System.out.println("Validate without selecting survey form");
        Select WL3 = new Select(Rec_and_cont.dd_Year);
        WL3.selectByIndex(1);
        sleep(1000);
        Select Prv3 = new Select(Rec_and_cont.dd_Province);
        Prv3.selectByIndex(2);
        sleep(2000);
        Select Mun83 = new Select(Rec_and_cont.dd_Municipality);
        Mun83.selectByVisibleText("Magallanes");

        Select srv3 = new Select(Rec_and_cont.dd_Survey);
        srv3.selectByIndex(0);
        Rec_and_cont.btn_Search.click();
        sleep(3000);
        Rec_and_cont.btn_Error_ok.click();
        try{
           Rec_and_cont.btn_Error_ok.isDisplayed();
           System.out.println("No Survey form selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

}

    @Test(priority = 4, testName = "view_workload")
    public void view_workload() throws InterruptedException {
        View_workload view_workload = new View_workload(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

//        Package_Element.View_workload.lbl_Receipt_and_control.click();

        sleep(2000);
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
        System.out.println("Year displayed - PASS");


        softAssert.assertTrue(View_workload.lbl_Month.isDisplayed(), "Month label is missing");
        String M = dataset.M;
        assertEquals(View_workload.lbl_Month.getText(), M);
        System.out.println("Month label displayed - PASS");

        Select VW = new Select(View_workload.dd_Month);
        VW.selectByIndex(1);

        System.out.println("Month displayed - PASS");

        softAssert.assertTrue(View_workload.lbl_Survey.isDisplayed(), "Survey label is missing");
        String sur = dataset.SURVEY;
        assertEquals(View_workload.lbl_Survey.getText(), sur);

        Select srv = new Select(View_workload.dd_Survey);
        srv.selectByIndex(1);

        softAssert.assertTrue(View_workload.lbl_Province.isDisplayed(), "Province label is missing");
        String prov = dataset.PROV;
        assertEquals(View_workload.lbl_Province.getText(), prov);

        Select Prv = new Select(View_workload.dd_Province);
        Prv.selectByIndex(2);


        View_workload.btn_Search.click();
        sleep(3000);

        Select Pg = new Select(View_workload.dd_Page);
        Pg.selectByIndex(0);
        Pg.selectByIndex(1);
        Pg.selectByIndex(2);
        Pg.selectByIndex(3);
        Pg.selectByIndex(4);

        wait.until(ExpectedConditions.visibilityOf(View_workload.btn_log_CO));

        View_workload.btn_log_CO.click();
        System.out.println("Log Modal displayed - PASS");

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

//        for (int f = 1; f <= 3; f++) {
//            sleep(1000);
//            String formattedStr = String.format("%02d", f);
//            driver.findElement(By.xpath("//*[@id=\"__BVID__138__cell-2023-11-" + formattedStr + "_\"]/span")).click();
//            Package_Element.View_workload.dd_Date_Distribution.click();
//        }

        WebElement col = View_workload.dd_Date_Collection;
        Actions actions1 = new Actions(driver);
        actions1.doubleClick(col).perform();
        System.out.println("Collection date displayed - PASS ");

//        for (int f = 4; f <= 8; f++) {
//            sleep(1000);
//            String formattedStr = String.format("%02d", f);
//            driver.findElement(By.xpath("//*[@id=\"__BVID__142__cell-2023-09-" + formattedStr + "_\"]/span")).click();
//            Package_Element.View_workload.dd_Date_Collection.click();
//        }
//        System.out.println("Collection date displayed - PASS");

        WebElement rec = View_workload.dd_Date_Receipt_PO;
        Actions actions2 = new Actions(driver);
        actions2.doubleClick(rec).perform();
        System.out.println("Receipt date displayed - PASS ");

//        for (int f = 8; f <= 12; f++) {
//            sleep(1000);
//            String formattedStr = String.format("%02d", f);
//            driver.findElement(By.xpath("//*[@id=\"__BVID__146__cell-2023-09-" + formattedStr + "_\"]/span")).click();
//            Package_Element.View_workload.dd_Date_Receipt_PO.click();
//        }
//        System.out.println("Receipt date displayed - PASS");

        Select stat = new Select(View_workload.dd_Status);
        stat.selectByIndex(0);
        stat.selectByIndex(13);


        sleep(1000);
        Select Ref_Prov = new Select(View_workload.dd_Prov);
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
        View_workload.btn_X.click();

//        sleep(1000);
//        Package_Element.View_workload.btn_view_CO.click();
//
//        sleep(1000);
//        Package_Element.View_workload.btn_close_view.click();

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
   //   Package_Element.Rec_and_cont.btn_Error_ok.click();
        try{
            Rec_and_cont.btn_Error_ok.isDisplayed();
            System.out.println("No Province selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }
    }

    @Test(priority = 5, testName = "cawi_registration")
    public void registration() throws InterruptedException {

        Cawi_Registration cawi_registration = new Cawi_Registration(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

//        Package_Element.Cawi_Registration.lbl_Receipt_and_control.click();

        sleep(1000);
        Cawi_Registration.btn_Cawi_Reg.click();

        sleep(2000);
        Select WL = new Select(Cawi_Registration.dd_Year);
        WL.selectByIndex(2);

        Select srv = new Select(Cawi_Registration.dd_Survey);
        srv.selectByIndex(1);

        Cawi_Registration.txt_ECN.clear();
        Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT);
        Cawi_Registration.btn_search.click();

        sleep(5000);
        Cawi_Registration.btn_edit.click();

        sleep(2000);
        String C_Person = Cawi_Registration.txtC_Person.getAttribute("value");
        if (Objects.equals(C_Person, "John Smith")) {
            Cawi_Registration.txtC_Person.clear();
            Cawi_Registration.txtC_Person.sendKeys(dataset.C_PERSON2);
        } else if (Objects.equals(C_Person, "Eugene Nuada")) {
            Cawi_Registration.txtC_Person.clear();
            Cawi_Registration.txtC_Person.sendKeys(dataset.C_PERSON);
        } else {
            System.out.println("Contact Person text box is displayed - PASS");
        }
        String C_Number = Cawi_Registration.txtC_number.getAttribute("value");
        if (Objects.equals(C_Number, "09123456799")) {
            Cawi_Registration.txtC_number.clear();
            Cawi_Registration.txtC_number.sendKeys(dataset.C_NUMBER2);
        } else if (Objects.equals(C_Number, "09326565455")) {
            Cawi_Registration.txtC_number.clear();
            Cawi_Registration.txtC_number.sendKeys(dataset.C_NUMBER);
        } else {
            System.out.println("Contact Number text box is displayed - PASS");
        }
        String C_Address = Cawi_Registration.txtE_Address.getAttribute("value");
        if (Objects.equals(C_Address, "sqadtester020@gmail.com")) {
            Cawi_Registration.txtE_Address.clear();
            Cawi_Registration.txtE_Address.sendKeys(dataset.E_ADDRESS2);
        } else if (Objects.equals(C_Address, "eugenenuada061@gmail.com")) {
            Cawi_Registration.txtE_Address.clear();
            Cawi_Registration.txtE_Address.sendKeys(dataset.E_ADDRESS);
        } else {
            System.out.println("Email Address text box is displayed - PASS");
        }
        Cawi_Registration.btn_save.click();

        wait.until(ExpectedConditions.elementToBeClickable(Cawi_Registration.btn_close));
        Cawi_Registration.btn_close.click();

        //Validation inconsistency and blank entry
//        validate_inconsistent_Year_Cawi_Reg();

    }



    //Validate inconsistency
    static void validate_inconsistent_Year_Cawi_Reg() throws InterruptedException {
        Rec_and_cont.lbl_receipt_and_control.click();
        Cawi_Registration.lbl_Receipt_and_control.click();

        Select WL = new Select(Cawi_Registration.dd_Year);
        WL.selectByIndex(0);
        Select srv = new Select(Cawi_Registration.dd_Survey);
        srv.selectByIndex(1);
        Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT_PO);
        Cawi_Registration.btn_search.click();
        sleep(4000);
        try{
            Cawi_Registration.btn_error_ok_rsso.click();
            Cawi_Registration.btn_error_ok_rsso.isDisplayed();
            System.out.println("No Year selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        //Without selecting Survey Form
        Select WL1 = new Select(Cawi_Registration.dd_Year);
        WL1.selectByIndex(1);
        Select srv1 = new Select(Cawi_Registration.dd_Survey);
        srv1.selectByIndex(0);
        Cawi_Registration.txt_ECN.clear();
        Cawi_Registration.txt_ECN.sendKeys(dataset.SRC_TXT_PO);
        Cawi_Registration.btn_search.click();
        sleep(4000);
        try{
            Cawi_Registration.btn_error_ok_rsso.click();
            Cawi_Registration.btn_error_ok_rsso.isDisplayed();
            System.out.println("No Survey Form selected and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }

        //without ECN input
        Rec_and_cont.lbl_receipt_and_control.click();
        Cawi_Registration.lbl_Receipt_and_control.click();
        Select WL2 = new Select(Cawi_Registration.dd_Year);
        WL2.selectByIndex(1);
        Select srv2 = new Select(Cawi_Registration.dd_Survey);
        srv2.selectByIndex(1);
        Cawi_Registration.txt_ECN.clear();
        sleep(1000);
        Cawi_Registration.btn_search.click();
        sleep(4000);
        try{
            Cawi_Registration.btn_error_ok_rsso.click();
            Cawi_Registration.btn_error_ok_rsso.isDisplayed();
            System.out.println("No ECN input and Displayed validation message PASS");
        }catch (Exception e){
            System.out.println("No validation message Displayed FAIL");
        }
    }


    @Test(priority = 6, testName = "workload_referral")
    public void referral() throws InterruptedException {

    Workload_Referral workload_referral = new Workload_Referral(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    JavascriptExecutor executor = (JavascriptExecutor) driver;



    sleep(1000);
    Workload_Referral.lbl_Workload_Referral.click();

    Select yr = new Select(Workload_Referral.dd_Year);

    yr.selectByIndex(0);
    yr.selectByIndex(1);


    Select srv = new Select(Workload_Referral.dd_Survey);

    srv.selectByIndex(0);
    srv.selectByIndex(1);


    Select ref = new Select(Workload_Referral.dd_Referral);

    ref.selectByIndex(0);
    ref.selectByIndex(1);
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
//    Package_Element.Workload_Referral.btn_Filter.click();
}


    @Test(priority = 7, testName = "data_entry")
    public void data_entry() throws InterruptedException {

        Data_Entry Data_entry = new Data_Entry(driver);
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
        Mth.selectByIndex(2);

        sleep(1000);
        Data_Entry.txt_ecn.sendKeys(dataset.ECN);

        Data_Entry.btn_Back_Dashboard.click();
;
        sleep(2000);
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

        sleep(1000);
        Data_Entry.txt_ecn.sendKeys(dataset.ECN);

        Data_Entry.btn_Submit.click();

        Data_Entry.lbl_Data_entry.click();


    }

    @Test(priority = 8, testName = "data_validation")
    public void data_validation() throws InterruptedException {

        Data_validation data_validation = new Data_validation(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        sleep(2000);
        Data_validation.lbl_Data_validation.click();

        sleep(2000);
        Data_validation.lbl_Missi.click();

        sleep(1000);
        Select Yr = new Select( Data_validation.dd_Year);
        Yr.selectByIndex(1);


        Select Mth = new Select( Data_validation.dd_Month);
        Mth.selectByIndex(4);

        Data_validation.btn_submit.click();

        sleep(1000);
        Data_validation.btn_icon.click();


        sleep(2000);
        Data_validation.lbl_pps.click();

        sleep(1000);
        Select Yr1 = new Select( Data_validation.dd_Year);
        Yr1.selectByIndex(0);
        Yr1.selectByIndex(1);

        Select Mth1 = new Select( Data_validation.dd_Month);
        Mth1.selectByIndex(0);
        Mth1.selectByIndex(1);


//        sleep(1000);
//        Package_Element.Data_Entry.txt_ecn.sendKeys(dataset.ECN);
//
//        Package_Element.Data_Entry.btn_Submit.click();
//
//        sleep(2000);
//        driver.switchTo( ).alert( ).accept();

        Data_Entry.btn_Back_Dashboard.click();

    }
    @Test(priority = 9, testName = "tabulation")
    public void tabulation() throws InterruptedException {

        Tabulation tabulation = new Tabulation(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        sleep(2000);
        Tabulation.lbl_Tabulation.click();
//        wait.until(ExpectedConditions.visibilityOfAllElements(Package_Element.Tabulation.lbl_Survey, Package_Element.Tabulation.lbl_Year, Package_Element.Tabulation.lbl_Month, Package_Element.Tabulation.lbl_List_of_table));
        if (Tabulation.lbl_Survey.isDisplayed()) {
            System.out.println("Survey label displayed");
        }else{
            System.out.println("Survey label is not displayed");
        }
        Select Sur = new Select(Tabulation.dd_Survey);
        Sur.selectByIndex(0);
        Sur.selectByIndex(1);

        if (Tabulation.dd_Year.isDisplayed()) {
            System.out.println("Year label displayed");
        }else{
            System.out.println("Year label is not displayed");
        }
        Select Yr = new Select(Tabulation.dd_Year);
        Yr.selectByIndex(0);
        Yr.selectByIndex(1);
        Yr.selectByIndex(2);

        if (Tabulation.dd_Month.isDisplayed()) {
            System.out.println("Month label displayed");
        }else{
            System.out.println("Month label is not displayed");
        }
        Select Mo = new Select(Tabulation.dd_Month);
        Mo.selectByIndex(0);
        Mo.selectByIndex(1);

        if (Tabulation.btn_Search.isDisplayed()) {
            System.out.println("Search button displayed");
        }else{
            System.out.println("Search button is not displayed");
        }
        WebElement srch = Tabulation.btn_Search;
        srch.click();

        WebElement tbl = Tabulation.dd_List_tab;
        List<WebElement>table = tbl.findElements(By.tagName("option"));

        for(WebElement option : table){
            option.click();

            srch.click();
            sleep(2000);
        }

    }

    @Test(priority = 10, testName = "reports")
    public void reports() throws InterruptedException {

        Reports reports = new Reports(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        Reports.lbl_Reports.click();

        sleep(1000);
        Reports.lbl_Worksheet.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(Reports.lbl_Survey, Reports.lbl_Year, Reports.lbl_Month));


        Select Sur = new Select(Reports.dd_Survey);
        Sur.selectByIndex(0);
        Sur.selectByIndex(1);

        Select Yr = new Select(Reports.dd_Year);
        Yr.selectByIndex(0);
        Yr.selectByIndex(2);

        Select Mth = new Select(Reports.dd_Month);
        Mth.selectByIndex(0);
        Mth.selectByIndex(12);

        sleep(2000);
        Select Cat = new Select(Reports.dd_Category);
        Cat.selectByIndex(0);
        Cat.selectByIndex(3);

        Tabulation.btn_Search.click();

        sleep(5000);

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

        Tabulation.btn_Search.click();
        sleep(5000);

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
        
        Tabulation.btn_Search.click();
        sleep(5000);
    }


        @Test(priority = 11, testName = "utilities")
    public void utilities() throws InterruptedException {

        Utilities utilities = new Utilities(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        sleep(1000);
        Utilities.lbl_Utilities.click();

        sleep(1000);
        Utilities.lbl_Active_User.click();

        Utilities.btn_create_user.click();

        Utilities.txt_Fname.sendKeys(dataset.F_NAME);
        Utilities.txt_Mname.sendKeys(dataset.M_NAME);
        Utilities.txt_Lname.sendKeys(dataset.L_NAME);
        Utilities.txt_Email.sendKeys(dataset.EMAIL);
// CO-Staff
//        WebElement dropdown = driver.findElement(By.cssSelector("select[@placeholder='Enter office']"));
//        dropdown.click();
        Select PrvU = new Select(Utilities.dd_Prov_Off);

        PrvU.selectByIndex(0);
        PrvU.selectByIndex(1);


        Select user = new Select(Utilities.dd_User_role);
        user.selectByIndex(0);
        user.selectByIndex(1);

// PO-Supervisor
        Select PrvU1 = new Select(Utilities.dd_Prov_Off);
        PrvU1.selectByIndex(0);
        PrvU1.selectByIndex(1);
        PrvU1.selectByIndex(2);
        PrvU1.selectByIndex(3);
        PrvU1.selectByIndex(4);
        PrvU1.selectByIndex(5);
        PrvU1.selectByIndex(6);
        PrvU1.selectByIndex(7);
        PrvU1.selectByIndex(8);


        Select user1 = new Select(Utilities.dd_User_role);

        user1.selectByIndex(0);
        user1.selectByIndex(1);
        user1.selectByIndex(2);

        Utilities.txt_Emp_ID.sendKeys(dataset.EMP_ID);

        Utilities.Rad_RSSO.click();

        Select PrcRSSO = new Select(Utilities.dd_RSSO);

        PrcRSSO.selectByIndex(0);
        PrcRSSO.selectByIndex(1);
        PrcRSSO.selectByIndex(2);
        PrcRSSO.selectByIndex(3);
        PrcRSSO.selectByIndex(4);
        PrcRSSO.selectByIndex(5);
        PrcRSSO.selectByIndex(6);
        PrcRSSO.selectByIndex(7);
        PrcRSSO.selectByIndex(8);

        Utilities.btn_cancel.click();


        Select pg = new Select(Utilities.dd_Ppage);

        pg.selectByIndex(0);
        pg.selectByIndex(1);
        pg.selectByIndex(2);
        pg.selectByIndex(3);

        Utilities.txt_Search.sendKeys(dataset.SEARCH);

        sleep(2000);
        Utilities.btn_view.click();

        sleep(2000);
        Utilities.btn_ok.click();

        sleep(2000);
        Utilities.btn_update.click();

        sleep(2000);
        Utilities.btn_cancel1.click();

        if(Utilities.user.isDisplayed()){
            Utilities.btn_deact.click();
            Utilities.btn_deact_Yes.click();
        }else{
            System.out.println("No 15 Juana A Charito has already Deactivated");
        }


        Utilities.lbl_Utilities.click();

        sleep(1000);
        Utilities.lbl_Inactive.click();

        Select pg1 = new Select(Utilities.dd_Ppage);

        pg1.selectByIndex(0);
        pg1.selectByIndex(1);
        pg1.selectByIndex(2);
        pg1.selectByIndex(3);

        Utilities.txt_Search.sendKeys(dataset.SEARCH);


        if(Utilities.btn_activate.isDisplayed()){
            Utilities.btn_activate.click();

            sleep(2000);
            Utilities.btn_Activate_Yes.click();
        }else{
            System.out.println("No user Deactivated as of now");
        }

//        changeUserRole();
    }

    //validation

}

