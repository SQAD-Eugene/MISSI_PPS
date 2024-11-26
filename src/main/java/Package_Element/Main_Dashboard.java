package Package_Element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Main_Dashboard {

    static WebDriver driver;
    static DataSet dataset;
    SoftAssert softAssert = new SoftAssert();

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
        if (Dashboard.lbl_co_name.isDisplayed()) {
            System.out.println("CO Username is displayed - PASS");
        } else {
            System.out.println("CO Username is not displayed - FAIL");
        }


        softAssert.assertTrue(Dashboard.lbl_co_position.isDisplayed(), "Position label is missing");
        String position = dataset.POSITION;
        assertEquals(Dashboard.lbl_co_position.getText(), position);
        if (Dashboard.lbl_co_position.isDisplayed()) {
            System.out.println("CO Position is displayed - PASS");
        } else {
            System.out.println("CO Position is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_co_location.isDisplayed(), "Location label is missing");
        String location = dataset.LOCATION;
        assertEquals(Dashboard.lbl_co_location.getText(), location);
        if (Dashboard.lbl_co_location.isDisplayed()) {
            System.out.println("CO Location is displayed - PASS");
        } else {
            System.out.println("CO Location is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_dashboard.isDisplayed(), "Package_Element.Dashboard label is missing");
        String dashboards = dataset.DASHBOARD;
        assertEquals(Dashboard.lbl_dashboard.getText(), dashboards);
        if (Dashboard.lbl_dashboard.isDisplayed()) {
            System.out.println("Package_Element.Dashboard label is displayed - PASS");
        } else {
            System.out.println("Package_Element.Dashboard label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_Receipt_and_control_CO.isDisplayed(), "Receipt and control label is missing");
        String receipt_and_control = dataset.RECEIPT_and_CONTROL;
        assertEquals(Dashboard.lbl_Receipt_and_control_CO.getText(), receipt_and_control);
        if (Dashboard.lbl_Receipt_and_control_CO.isDisplayed()) {
            System.out.println("Receipt and Control label is displayed - PASS");
        } else {
            System.out.println("Receipt and Control label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_Utilities.isDisplayed(), "Package_Element.Utilities label is missing");
        String utilities = dataset.UTILITIES;
        assertEquals(Dashboard.lbl_Utilities.getText(), utilities);
        if (Dashboard.lbl_Utilities.isDisplayed()) {
            System.out.println("Package_Element.Utilities label is displayed - PASS");
        } else {
            System.out.println("Package_Element.Utilities label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_dataentry.isDisplayed(), "Data entry label is missing");
        String data_entry = dataset.DATA_ENTRY;
        assertEquals(Dashboard.lbl_dataentry.getText(), data_entry);
        if (Dashboard.lbl_dataentry.isDisplayed()) {
            System.out.println("Data entry label is displayed - PASS");
        } else {
            System.out.println("Data entry label is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.lbl_logout.isDisplayed(), "Logout label is missing");
        String logout = dataset.LOGOUT;
        assertEquals(Dashboard.lbl_logout.getText(), logout);
        if (Dashboard.lbl_logout.isDisplayed()) {
            System.out.println("Logout label is displayed - PASS");
        } else {
            System.out.println("Logout label is not displayed - FAIL");
        }


        sleep(2000);
        softAssert.assertTrue(Dashboard.btn_Nat.isDisplayed());
        String National = dataset.NATIONAL;
        assertEquals(Dashboard.btn_Nat.getText(), National);
        if (Dashboard.btn_Nat.isDisplayed()) {
            System.out.println("National Button is displayed - PASS");
            sleep(1000);
            assertEquals(Dashboard.GRAP_MISSI_SUMMARY.getText(), dataset.Nat_GRAP_SUMMARY);
            assertEquals(Dashboard.GRAP_MISSI_RATE_SAMPLE.getText(), dataset.Nat_GRAP_Response_Rate_Samp);
            assertEquals(Dashboard.GRAP_MISSI_RATE_IND.getText(), dataset.Nat_GRAP_Response_Rate_Ind);
        } else {
            System.out.println("National Button is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.btn_Reg.isDisplayed());
        String Regional = dataset.REGION;
        assertEquals(Dashboard.btn_Reg.getText(), Regional);
        if (Dashboard.btn_Reg.isDisplayed()) {
            Dashboard.btn_Reg.click();
            System.out.println("Regional Button is displayed - PASS");
            assertEquals(Dashboard.GRAP_MISSI_Reg_Status.getText(), dataset.Reg_GRAP_Status_MISSI);
        } else {
            System.out.println("Regional Button is not displayed - FAIL");
        }

        softAssert.assertTrue(Dashboard.btn_Prov.isDisplayed());
        String Prov = dataset.PROVINCE;
        assertEquals(Dashboard.btn_Prov.getText(), Prov);
        if (Dashboard.btn_Prov.isDisplayed()) {
            Dashboard.btn_Prov.click();
            System.out.println("Province Button is displayed - PASS");
            assertEquals(Dashboard.GRAP_MISSI_Prov_Status.getText(), dataset.Prov_GRAP_Status_MISSI);
        } else {
            System.out.println("Province Button is not displayed - FAIL");
        }
        softAssert.assertAll();
    }
}