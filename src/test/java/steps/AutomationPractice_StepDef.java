package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AutomationPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static driverBase.Base.driver;


public class AutomationPractice_StepDef {

    Properties prop = new Properties();
    FileInputStream ip;

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

    AutomationPractice practice = new AutomationPractice(driver);

    @Given("^User is logged in to Automation practice app My Account page$")
    public void user_is_logged_in_to_Automation_practice_app_My_Account_page() throws Throwable {

        practice.navigateToLoginPage();
        practice.enterValidLoginCredentials(prop.getProperty("username"), prop.getProperty("password"));
        practice.clickLoginButton();
    }

    @When("^User clicks my personal information link$")
    public void user_clicks_my_personal_information_link() throws Throwable {

        practice.clickMyPersonalInfoLink();
    }

    @When("^User updates first name correctly$")
    public void user_updates_first_name_correctly() throws Throwable {

        practice.updateFirstName();
    }

    @When("^User clicks Save button$")
    public void user_clicks_Save_button() throws Throwable {

        practice.clickSaveButton();
    }

    @Then("^Success message is displayed$")
    public void success_message_is_displayed() throws Throwable {

        practice.successMessage();
    }

    @When("^User clicks add to cart button after selecting TShirt to buy$")
    public void userClicksAddToCartButtonAfterSelectingTShirtLinkToBuy() throws Throwable {

        practice.clickAddTShirt();
    }

    @And("^User clicks through all Proceed to Checkout buttons$")
    public void userClicksThroughAllProceedToCheckoutButtons() throws Throwable {

        practice.clickProceedCheckOutButtons();
    }

    @And("^User clicks Confirm Order button after selecting payment method$")
    public void userClicksConfirmOrderButtonAfterSelectingPaymentMethod() throws Throwable {

        practice.clickConfirmOrdersAfterSelectingPaymentMethod();
    }

    @Then("^Order is displayed in Order history after click back to Order button$")
    public void orderIsDisplayedInOrderHistoryAfterClickBackToOrderButton() throws Throwable {

        practice.orderDisplayedInOrderHistory();
    }
}
