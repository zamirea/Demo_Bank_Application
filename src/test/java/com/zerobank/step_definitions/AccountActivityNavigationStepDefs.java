package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.zerobank.utilities.BrowserUtils.waitUntilTitleIs;
import static org.junit.Assert.assertEquals;

public class AccountActivityNavigationStepDefs {



    @When("the user clicks on {string} link on the home page")
    public void the_user_clicks_on_link_on_the_home_page(String link) {
        new AccountSummaryPage().navigateToLink(link);
    }


}

