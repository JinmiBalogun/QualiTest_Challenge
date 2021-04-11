package pages;

import driverBase.Base;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AutomationPractice extends Base {

    Properties prop = new Properties();
    FileInputStream ip;
    WebDriverWait wait = new WebDriverWait(driver, 30);


    {
        try {
            ip = new FileInputStream("config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AutomationPractice(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "#email")
    private WebElement emailField;

    @FindBy(how = How.CSS, using = "#passwd")
    private WebElement passwordField;

    @FindBy(how = How.CSS, using = "#SubmitLogin")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'My personal information')]")
    private WebElement personalInfoLink;

    @FindBy(how = How.CSS, using = "#firstname")
    private WebElement firstNameField;

    @FindBy(how = How.CSS, using = "#old_passwd")
    private WebElement currPasswordField;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(11) .btn.btn-default.button.button-medium > span:nth-child(1)")
    private WebElement saveButton;

    @FindBy(how = How.CSS, using = ".alert")
    private WebElement successAlert;

    @FindBy(how = How.LINK_TEXT, using = "T-SHIRTS")
    private WebElement tShirtTab;

    @FindBy(how = How.XPATH, using = "//*[@id=\"add_to_cart\"]/button")
    private WebElement addToCartButton;

    @FindBy(how = How.CSS, using = ".product_img_link")
    private WebElement requiredShirt;

    @FindBy(how = How.XPATH, using = "//*[@title='Proceed to checkout']")
    private WebElement proceedToCheckOutButtonTShirtPage;

    @FindBy(how = How.CSS, using = ".cart_navigation .btn.button-medium")
    private WebElement proceedToCheckOutButtonShoppingCartPage;

    @FindBy(how = How.CSS, using = ".cart_navigation .btn.button-medium")
    private WebElement proceedToCheckOutButtonAddressesPage;

    @FindBy(how = How.CSS, using = "#cgv")
    private WebElement termsOfServiceCheckBox;

    @FindBy(how = How.CSS, using = ".bankwire")
    private WebElement paymentMethod;

    @FindBy(how = How.CSS, using = ".button.standard-checkout")
    private WebElement proceedToCheckOutButtonShippingPage;

    @FindBy(how = How.CSS, using = "#cart_navigation .btn-default.button-medium")
    private WebElement confirmOrderButton;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Back to")
    private WebElement backToOrdersButton;

    @FindBy(how = How.CSS, using = ".color-myaccount")
    private List<WebElement> orderReference;

    @FindBy(how = How.CSS, using = ".history_method")
    private List<WebElement> paymentMethodList;


    public void navigateToLoginPage() throws Exception {

        driver.manage().deleteAllCookies();
        driver.navigate().to(prop.getProperty("base_url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(4, TimeUnit.SECONDS);
    }

    public void enterValidLoginCredentials(String email, String password) throws Exception {

        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }

    public void clickMyPersonalInfoLink() throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(personalInfoLink));
        personalInfoLink.click();
    }

    public void updateFirstName() throws Exception {

        firstNameField.clear();
        firstNameField.sendKeys("Jinmi" + RandomStringUtils.randomAlphabetic(1));
        currPasswordField.sendKeys(prop.getProperty("password"));
    }

    public void clickSaveButton() throws Exception {

        wait.until(ExpectedConditions.visibilityOfAllElements(saveButton));
        saveButton.click();
    }

    public void successMessage() throws Exception {

        String alertText = successAlert.getText();
        Assert.assertEquals("Error! Typo or Check UI to see if any changes", alertText, "Your personal information has been successfully updated.");
    }

    public void clickAddTShirt() throws Exception {

        tShirtTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(requiredShirt));
        requiredShirt.click();
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckOutButtonTShirtPage));
        proceedToCheckOutButtonTShirtPage.click();
    }

    public void clickProceedCheckOutButtons() throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckOutButtonShoppingCartPage));
        proceedToCheckOutButtonShoppingCartPage.click();
        Thread.sleep(1000);
        proceedToCheckOutButtonAddressesPage.click();
        termsOfServiceCheckBox.click();
        proceedToCheckOutButtonShippingPage.click();
    }

    public void clickConfirmOrdersAfterSelectingPaymentMethod() throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(paymentMethod));
        paymentMethod.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        confirmOrderButton.click();
    }

    public void orderDisplayedInOrderHistory() throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(backToOrdersButton));
        backToOrdersButton.click();
        String text = paymentMethodList.get(0).getText();
        Assert.assertTrue(text.contains("Bank wire"));
        Assert.assertTrue("Element not displayed", orderReference.get(0).isDisplayed());
    }


}


