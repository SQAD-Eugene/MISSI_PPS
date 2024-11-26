package Package_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(xpath ="//button[contains(.,'Login')]")
    public WebElement btn_login_btn;

    @FindBy(xpath = "//*[@id=\"__BVID__7__BV_label_\"]")
    public WebElement lbl_email;

    @FindBy (xpath = "//*[@id=\"__BVID__8\"]")
    public WebElement tf_emailAdd;

    @FindBy (xpath = "//*[@id=\"__BVID__9__BV_label_\"]")
    public WebElement lbl_pw;

    @FindBy (xpath = "//*[@id=\"password\"]")
    public WebElement tf_pw;

    @FindBy (xpath = "//*[@id=\"content\"]/div/div/div/form/div[5]/div/div/button")
    public WebElement btn_login;

    @FindBy (xpath = "//*[@id=\"login-modal___BV_modal_content_\"]")
    public WebElement modal_error;

    @FindBy (xpath = "//*[@id=\"login-modal___BV_modal_footer_\"]/div/div/div/button")
    public WebElement btn_errorOK;

    public LoginPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver = this.driver;
    }

}
