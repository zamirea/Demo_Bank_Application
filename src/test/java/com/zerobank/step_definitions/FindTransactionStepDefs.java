package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.zerobank.utilities.BrowserUtils.waitForVisibility;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FindTransactionStepDefs {
    AccountActivityPage accountActivityPage = new AccountActivityPage();
    @When("user accesses the {string} tab")
    public void user_accesses_the_tab(String tab) {
        new AccountActivityPage().navigateTo_(tab);
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {

        accountActivityPage.enterDate("from", fromDate);
        accountActivityPage.enterDate("to", toDate);
    }

    @When("clicks search")
    public void clicks_search() {
        accountActivityPage.submitFind();
    }

    @Then("results table should only show transaction dates between {string} to {string}")
    public void results_table_should_only_show_transaction_dates_between_to(String fromDate, String toDate) {

        waitForVisibility(accountActivityPage.searchResult);
        assertTrue(accountActivityPage.verifyDisplayedDates(fromDate, toDate));
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {

        assertTrue(accountActivityPage.verifyDateOrder());
    }

    @Then("results table should not contain transactions dated {string}")
    public void results_table_should_not_contain_transactions_dated(String outOfRange) {
        assertFalse(accountActivityPage.verifyDateOutOfRange(outOfRange));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String description) {
       accountActivityPage.enterDescription(description);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String description) {

        waitForVisibility(accountActivityPage.searchResult);
        assertTrue(accountActivityPage.verifyResultsContainDescription(description));
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String description) {

        waitForVisibility(accountActivityPage.searchResult);
        assertFalse(accountActivityPage.verifyResultsContainDescription(description));
    }

    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_Deposit() {

        waitForVisibility(accountActivityPage.searchResult);
        int result = accountActivityPage.numberOfResult("deposit");
        assertTrue(result >= 1);
    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_Withdrawal() {

        waitForVisibility(accountActivityPage.searchResult);
        int result = accountActivityPage.numberOfResult("withdrawal");
        assertTrue(result >= 1);
    }

    @When("user selects type {string}")
    public void user_selects_type(String type) {
        accountActivityPage.selectTransactionType(type);
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_Withdrawal() {

        waitForVisibility(accountActivityPage.searchResult);
        int result = accountActivityPage.numberOfResult("withdrawal");
        assertEquals(0, result);
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_Deposit() {

        waitForVisibility(accountActivityPage.searchResult);
        int result = accountActivityPage.numberOfResult("deposit");
        assertEquals(0, result);
    }
}
