Feature: Grocery CRUD web page automation

  Scenario: Create new user with success (Challenge 1)
    Given the user navigates to https://www.grocerycrud.com/demo/bootstrap_theme
    When the user selects version Bootstrap V4 Theme
    And the user clicks on the button "Add Customer"
    And the user fills the form with the following values
      | first name    | last name | contact first name | phone        | address line 1        | address line 2 | city         | state | postal code | country | from employeer | credit limit |
      | Teste Sicredi | Teste     | Matheus            | 51 9999-9999 | Av Assis Brasil, 3970 | Torre D        | Porto Alegre | RS    | 91000-000   | Brasil  | Fixter         | 200          |
    And the user clicks on the button "Save"
    Then a message saying that the customer was created with success is raised
    And the browser is closed

  Scenario: Create new user with success (Challenge 2)
    Given the user navigates to https://www.grocerycrud.com/demo/bootstrap_theme
    When the user selects version Bootstrap V4 Theme
    And the user clicks on the button "Add Customer"
    And the user fills the form with the following values
      | first name    | last name | contact first name | phone        | address line 1        | address line 2 | city         | state | postal code | country | from employeer | credit limit |
      | Teste Sicredi | Teste     | Matheus            | 51 9999-9999 | Av Assis Brasil, 3970 | Torre D        | Porto Alegre | RS    | 91000-000   | Brasil  | Fixter         | 200          |
    And the user clicks on the button "Save"
    Then a message saying that the customer was created with success is raised

    When the user clicks on the button "Save and go back to list"
    And the user searches for the recent created customer
    And the user select the check box of that customer
    And the user clicks on the button "Delete"
    And the user clicks on the button Delete inside the popup that shows up
    Then a message saying that the customer was deleted with success is raised
    And the browser is closed