package prova_sicredi;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class StepDefinitions {
    Map<String, String> parametersMap;
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    private static final Logger logger = Logger.getLogger(StepDefinitions.class.getName());

    @BeforeStep
    public void beforeStep() throws InterruptedException {
        Thread.sleep(2000);
    }
    @Given("^the user navigates to (.*)$")
    public void theUserNavigatesToHttpsWwwGroceryCrudComDemoBootstrapTheme(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logger.info("Accessed url: " + url);
    }

    @When("the user selects version Bootstrap V4 Theme")
    public void theUserSelectsVersionBootstrapV4Theme() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("switch-version-select")));
        new Select(driver.findElement(By.id("switch-version-select"))).selectByVisibleText("Bootstrap V4 Theme");

        logger.info("Selected Bootstrap V4 Theme");
    }

    @And("the user clicks on the button {string}")
    public void theUserClicksOnButton(String buttonName) {
        switch (buttonName){
            case "Add Customer":
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#gcrud-search-form > div.header-tools > div.floatL.t5 > a")));
                driver.findElement(By.cssSelector("#gcrud-search-form > div.header-tools > div.floatL.t5 > a")).click();
                break;
            case "Save":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("form-button-save")));
                driver.findElement(By.id("form-button-save")).click();
                break;
            case "Save and go back to list":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("save-and-go-back-button")));
                driver.findElement(By.id("save-and-go-back-button")).click();
                break;
            case "Delete":
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#gcrud-search-form > div.scroll-if-required > table > thead > tr.filter-row.gc-search-row > td.no-border-left > div.floatL > a")));
                driver.findElement(By.cssSelector("#gcrud-search-form > div.scroll-if-required > table > thead > tr.filter-row.gc-search-row > td.no-border-left > div.floatL > a")).click();
                break;
            case "Delete inside the popup":
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.container-fluid.gc-container > div.row > div.delete-multiple-confirmation.modal.fade.in.show > div > div > div.modal-footer > button.btn.btn-danger.delete-multiple-confirmation-button")));
                driver.findElement(By.cssSelector("body > div.container-fluid.gc-container > div.row > div.delete-multiple-confirmation.modal.fade.in.show > div > div > div.modal-footer > button.btn.btn-danger.delete-multiple-confirmation-button")).click();
                break;
            default:
                throw new IllegalStateException("Unexpected button: " + buttonName);
        }

        logger.info("Clicked on " + buttonName);
    }

    @And("the user fills the form with the following values")
    public void theUserFillsTheFormWithTheFollowingValues(DataTable parametersDataTable) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-customerName")));

        List<Map<String, String>> parametersList = parametersDataTable.asMaps(String.class, String.class);
        parametersMap = new HashMap<>(parametersList.get(0));

        driver.findElement(By.id("field-customerName")).sendKeys(parametersMap.get("first name"));
        driver.findElement(By.id("field-contactLastName")).sendKeys(parametersMap.get("last name"));
        driver.findElement(By.id("field-contactFirstName")).sendKeys(parametersMap.get("contact first name"));
        driver.findElement(By.id("field-phone")).sendKeys(parametersMap.get("phone"));
        driver.findElement(By.id("field-addressLine1")).sendKeys(parametersMap.get("address line 1"));
        driver.findElement(By.id("field-addressLine2")).sendKeys(parametersMap.get("address line 2"));
        driver.findElement(By.id("field-city")).sendKeys(parametersMap.get("city"));
        driver.findElement(By.id("field-state")).sendKeys(parametersMap.get("state"));
        driver.findElement(By.id("field-postalCode")).sendKeys(parametersMap.get("postal code"));
        driver.findElement(By.id("field-country")).sendKeys(parametersMap.get("country"));
        driver.findElement(By.id("field_salesRepEmployeeNumber_chosen")).click();
        driver.findElement(By.cssSelector("#field_salesRepEmployeeNumber_chosen > div > div > input[type=text]")).
                sendKeys(parametersMap.get("from employeer") + "\n");
        driver.findElement(By.id("field-creditLimit")).sendKeys(parametersMap.get("credit limit"));

        logger.info("All fields were filled with success");
    }

    @Then("a message saying that the customer was created with success is raised")
    public void aMessageSayingThatTheUserWasCreatedWithSuccessIsRaised() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("report-success")));
        Assert.assertTrue("Expected success message not raised", driver.findElement(By.id("report-success")).getText().
                contains("Your data has been successfully stored into the database."));

        logger.info("Success message was raised");
    }

    @And("the browser is closed")
    public void theBrowserIsClosed() {
        driver.close();

        logger.info("Browser closed with success");
    }

    @And("the user searches for the recent created customer")
    public void theUserSearchesForTheRecentCreatedCustomer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName")));
        driver.findElement(By.name("customerName")).sendKeys(parametersMap.get("first name"));
    }

    @And("the user select the check box of that customer")
    public void theUserSelectTheCheckBoxOfThatCustomer() throws InterruptedException {
        driver.findElement(By.className("select-all-none")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("select-all-none")).click();
    }

    @And("a popup confirming the delete operation shows up")
    public void aPopupConfirmingTheDeleteOperationShowsUp() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.container-fluid.gc-container > div.row > div.delete-multiple-confirmation.modal.fade.in.show > div > div > div.modal-footer > button.btn.btn-danger.delete-multiple-confirmation-button")));
        Assert.assertTrue(driver.findElement(By.cssSelector("body > div.container-fluid.gc-container > div.row > div.delete-multiple-confirmation.modal.fade.in.show > div > div > div.modal-body > p.alert-delete-multiple")).getText().contains("Are you sure that you want to delete"));

        logger.info("Popup confirming delete operation appeared with success");
    }

    @Then("a message saying that the customer was deleted with success is raised")
    public void aMessageSayingThatTheCustomerWasDeletedWithSuccessIsRaised() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.alert.alert-success.growl-animated.animated.bounceInDown")));
        Assert.assertTrue(driver.findElement(By.cssSelector("body > div.alert.alert-success.growl-animated.animated.bounceInDown")).getText().contains("Your data has been successfully deleted from the database"));

        logger.info("Customer deleted with success");
    }
}
