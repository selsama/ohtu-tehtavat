package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        goToLoginPageFromFrontPage();
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUserAndPasswordGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
         this.goToCreateNewUserPageFromFrontPage();
    }
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernamePasswordAndPasswordConfirmationAreEntered(String username, String password) {
        createNewWith(username, password, password);
    }
    
    @Then("a new user is created")
    public void newUserIsCreated() {
        assertTrue(driver.getPageSource().contains("Welcome to Ohtu Application!"));
    }
    
    @When("a too short username {string} and valid password {string} are given")
    public void shortUsernameAndValidPasswordAreGiven(String username, String password) {
        createNewWith(username, password, password);
    }
    
    @When("a valid username {string} and too short password {string} are given")
    public void validUsernameAndTooShortPasswordAreGiven(String username, String password) {
        createNewWith(username, password, password);
    }
    
    @When("a valid username {string} and password {string} and not matching confirmation {string} are given")
    public void validUsernameAndPasswordButConfirmationDoesNotMatch(String username, String password, String confirmation) {
        createNewWith(username, password, confirmation);
    }
    
    @Then("user is not created and error {string} is reported")
    public void userNotCreatedAndErrorMessageGiven(String error) {
        assertTrue(driver.getPageSource().contains(error));
    }
    
    @Given("user with username {string} with password {string} is successfully created")
    public void userSuccessfullyCreated(String username, String password) {
        goToCreateNewUserPageFromFrontPage();
        createNewWith(username, password, password);
        returnToFirstPageFromWelcomePage();
    }
    
    @When("existing user info username {string} and password {string} are entered")
    public void existingUserLogsIn(String username, String password) {
        logInWith(username, password);
    }
    
    @Given("user with username {string} and password {string} is tried to be created")
    public void tryingToCreateUser(String username, String password) {
        goToCreateNewUserPageFromFrontPage();
        createNewWith(username, password, password);
        returnToFrontPageFromCreateUserPage();
    }
    
    @When("bad user info username {string} and password {string} are entered")
    public void badUserInfoEntered(String username, String password) {
        logInWith(username, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void createNewWith(String username, String password, String confirmation) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    private void returnToFirstPageFromWelcomePage() {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
    
    private void goToLoginPageFromFrontPage() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();  
    }
    
    private void goToCreateNewUserPageFromFrontPage() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    }
    
    private void returnToFrontPageFromCreateUserPage() {
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }
}
