package com.app.mendeley.stepdefinition;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


/**
 * @author Sathya Thameem
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/featurefiles",
				plugin = {"html:target"}
		)
public class Runner {
	
	
}
