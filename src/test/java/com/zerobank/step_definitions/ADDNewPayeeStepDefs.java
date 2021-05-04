package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static com.zerobank.utilities.BrowserUtils.waitUntilVisible;
import static org.junit.Assert.assertEquals;

public class ADDNewPayeeStepDefs {
    PayBillsPage payBillsPage = new PayBillsPage();
    @When("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> payeeInfo) {
        payBillsPage.enterPayeeInfo(payeeInfo);
    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String expectedMessage) {

        waitUntilVisible(payBillsPage.alertMessage);
        String actualMessage = payBillsPage.alertMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
