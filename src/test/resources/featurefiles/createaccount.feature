Feature: Create Account in www.mendeley.com
  In order to use the features available in the Mendely.com
  As a user I want to create a new account for mendeley.com

  Background:
  Given I am on "MendeleyJoin" page
  Then I verify "title" 
  
  Scenario: Check for the Error messages for the Empty entries at the Mendeley join page
    
    When I click on "continuebtn" 
    And "Emailerrmsg1" is displayed in the "Emailerrorlocation" in the registration page
    And "Fnamerrormessage" is displayed in the "Fnameerrorlocation" in the registration page
		And "lnamerrormessage" is displayed in the "lnameerrorlocation" in the registration page
		And "Pwderrmsg1" is displayed in the "pwderrorlocation" in the registration page
		
		Scenario Outline: Check for the Error messages for the Invalid entries at the Mendeley join page 
											and verify the title of the page to be the same as the initial loaded page
    
    Then I Enter "<invalidEmail>" in the "emailField" in the registration page
    Then I Enter "<invalidfname>" in the "FnameField" in the registration page
    Then I Enter "<invalidlname>" in the "lnameField" in the registration page
    Then I Enter "<invalidpassword>" in the "pwdField" in the registration page
    When I click on "continuebtn" 
    And "Emailerrmsg2" is displayed in the "Emailerrorlocation" in the registration page
    And "FLnameerrmsg2" is displayed in the "Fnameerrorlocation" in the registration page
		And "FLnameerrmsg2" is displayed in the "lnameerrorlocation" in the registration page
		And "Pwderrmsg2" is displayed in the "pwderrorlocation" in the registration page
		And I verify "title" 
	
	  Examples:
	  |invalidEmail|invalidfname|invalidlname|invalidpassword|
	  |gggggg|345675765|7676876|abc|
	  |ss.com|*****|^^^^^|3|
		|ss@com|&&&&&&|___--|123456|
		
		Scenario Outline: Check for the Error messages in the last step of  Mendeley join page 
											with the Field of study and Academics are not selected
		Then I Enter "<email>" in the "emailField" in the registration page
    Then I Enter "<fname>" in the "FnameField" in the registration page
    Then I Enter "<lname>" in the "lnameField" in the registration page
    Then I Enter "<password>" in the "pwdField" in the registration page
    When I click on "continuebtn" 
    When I click on "createaccountbtn"
    And "foserror" is displayed in the "foserrorloc" in the registration page
    And "acstatuserror" is displayed in the "acstatuserrorloc" in the registration page
    
    Examples:
    |email|fname|lname|password|
    |sample@gmail.com|Sample|Example|tryings|
    
	Scenario Outline: Create Mendeley account successfully
		
		Then I Enter "<email>" in the "emailField" in the registration page
    Then I Enter "<fname>" in the "FnameField" in the registration page
    Then I Enter "<lname>" in the "lnameField" in the registration page
    Then I Enter "<password>" in the "pwdField" in the registration page
    When I click on "continuebtn" 
    Then I select "<fieldofstudy>" from "FieldOfStudy"
    Then I select "<AcaStatus>" from "AcademicStatus"
    When I click on "createaccountbtn"
    And I verify "usertitle"
    
    Examples:
    |email|fname|lname|password|fieldofstudy|AcaStatus|
    |sample10@gmail.com|Sample10|Example10|tryings95|Chemistry|Lecturer|
    
    