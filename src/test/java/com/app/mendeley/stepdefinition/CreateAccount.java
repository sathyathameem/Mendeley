package com.app.mendeley.stepdefinition;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.testng.Assert;

import com.app.mendeley.util.TestBaseUtils;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Sathya Thameem
 *
 */
public class CreateAccount {

	TestBaseUtils seleniumDriver = TestBaseUtils.getInstance(); // For Drivers

	@Rule
	ErrorCollector errColl = new ErrorCollector();

	@Given("^I am on \"([^\"]*)\" page$")
	public void i_am_on_page(String url) {
		System.out.println("I am on webpage - Given " + url);
		seleniumDriver.openBrowser("browser");
		seleniumDriver.navigate(url);

	}

	/**
	 * @Then("^\"([^\"] )\" should be present$") public void
	 *                  should_be_present(String arg1) {
	 *                  System.out.println(arg1+"should be present");
	 * 
	 *                  try{
	 *                  Assert.assertTrue(seleniumDriver.isElementPresent(arg1
	 *                  ));
	 * 
	 *                  } catch(AssertionError ae) { System.out.println(ae); //
	 *                  broken item #1
	 * 
	 *                  }
	 * 
	 *                  }
	 **/

	@When("^I click on \"([^\"]*)\"$")
	public void i_click_on(String arg1) {

		seleniumDriver.click(arg1);
	}

	@Then("^I verify \"([^\"]*)\"$")
	public void i_verify_of_the_create_account_page(String arg1) {
		try {
			Assert.assertTrue(seleniumDriver.verifyPageByTitle(arg1));
		} catch (AssertionError ae) {
			System.out.println("Test Failed " + ae); // broken item #1
			errColl.addError(ae);
		}
	}

	@Then("^I Enter \"([^\"]*)\" in the \"([^\"]*)\" in the registration page$")
	public void i_Enter_in_the_in_the_registration_page(String text,
			String object) {
		seleniumDriver.type(text, object);
	}

	/** catching the assert error, else next step will not be executed **/
	@When("^\"([^\"]*)\" is displayed in the \"([^\"]*)\" in the registration page$")
	public void is_displayed_in_the_in_the_registration_page(String arg1,
			String arg2) {
		try {
			Assert.assertTrue(seleniumDriver
					.isErrorMessageDisplayed(arg1, arg2));
		} catch (Throwable T) {
			System.out.println("Test Failed " + T.getMessage()); // broken item
																	// #1
			errColl.addError(T);
		}
	}

	@Then("^I select \"([^\"]*)\" from \"([^\"]*)\"$")
	public void i_select_from(String text, String object) {
		seleniumDriver.select(text, object);
	}

	/** Clean up - Close the browsers -After Hook. **/
	@After
	public void closeBrowsers(){
		seleniumDriver.cleanUp();
		
	}
	
}
