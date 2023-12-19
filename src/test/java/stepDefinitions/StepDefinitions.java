package stepDefinitions;


import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class StepDefinitions extends Utils {

    Properties prop = new Properties();
    FileInputStream fi;

    {
        try {
            fi = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//configs//Configuration.properties");
            prop.load(fi);
        } catch (IOException e) {

        }
    }

    @Given("Open a browser and navigate to the given URL")
    public void navigateToURL() throws IOException, InterruptedException {
        driver = initialiseBrowser();
        goToURL();
        WebElement button = driver.findElement(By.xpath("(//button[contains(.,'Start demo')])[1]"));
        elementToVisible(button);
        driver.findElement(By.xpath("//div[@class='cookie-btn bg-red']")).click();

        screenShot("Homepage");
        ImageDifference("Homepage", "Homepage", "HomepageDiff");


    }

    @When("Click on start demo  of use {string}.")
    public void ClickStartDemo(String StartDemo) {
        driver.findElement(By.xpath(prop.getProperty(StartDemo))).click();

    }

    @Then("it navigates to the cart details page capture screenshot and click on next Button.")
    public void navigatesToCartPage() throws InterruptedException {
        Thread.sleep(3000);
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 1 OF 6: BEGIN CHECKOUT");
        screenShot("cartPage");
        ImageDifference("cartPage", "cartPage", "cartPageDiff");

        clickNextButton();
        Thread.sleep(1000);
    }

    @And("navigates to the shipping details page enter the details using {string} and select {string} and click on next button")
    public void NavigatesToShippingDetailsPage(String testInfoButton, String inputData) throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Thread.sleep(2000);

        screenShot("shippingPageWithoutData");
        ImageDifference("shippingPageWithoutData", "shippingPageWithoutData", "shippingPageWithoutDataDiff");
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 2 OF 6: CLICK TO PAY ID LOOKUP");
        driver.findElement(By.xpath(prop.getProperty(testInfoButton))).click();
        WebElement data = driver.findElement(By.xpath(prop.getProperty(inputData)));
        elementToVisible(data);
        data.click();
        Thread.sleep(2000);

        screenShot("shippingPageWithData");
        ImageDifference("shippingPageWithData", "shippingPageWithData", "shippingPageWithDataDiff");
        clickNextButton();
    }


    @And("navigates to the OTP component page enter the details using {string} and select {string} and click on next button")
    public void NavigatesToOTPComponentPage(String fillCode, String inputData) throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Thread.sleep(2000);

        screenShot("OTPWithoutData");
        ImageDifference("OTPWithoutData", "OTPWithoutData", "OTPWithoutDataDiff");
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 3 OF 6: USE ONE-TIME PASSCODE");

        driver.findElement(By.xpath(prop.getProperty(fillCode))).click();
        WebElement data = driver.findElement(By.xpath(prop.getProperty(inputData)));
        elementToVisible(data);
        data.click();
        Thread.sleep(2000);

        screenShot("OTPWithData");
        ImageDifference("OTPWithData", "OTPWithData", "OTPWithDataDiff");
        clickNextButton();


    }

    @And("navigates to review page and click on next.")

    public void navigatesToReviewPageAndClickOnNext() throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Thread.sleep(2000);

        screenShot("reviewPage");
        ImageDifference("reviewPage", "reviewPage", "reviewPageDiff");
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 4 OF 6: PLACE ORDER");
        clickNextButton();


    }

    @And("navigates to DCF page and click on next.")
    public void navigatesToDCFPageAndClickOnNext() throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 5 OF 6: PAYMENT PROCESSING");
        Thread.sleep(2000);

        screenShot("DCFPage");
        ImageDifference("DCFPage", "DCFPage", "DCFPageDiff");
        clickNextButton();

    }


    @And("navigates to Order Confirmation page Click on {string}")
    public void navigatesToOrderConfirmationPageClickOn(String endDemo) throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 6 OF 6: ORDER CONFIRMATION");
        Thread.sleep(2000);
        screenShot("orderConfirmationPage");
        ImageDifference("orderConfirmationPage", "orderConfirmationPage", "orderConfirmationPageDiff");
        driver.findElement(By.xpath(prop.getProperty(endDemo))).click();
        closeDriver();
    }

    @Then("it navigates to the cart details page and click on next Button.")
    public void UC2cartDetailsPage() throws InterruptedException {
        Thread.sleep(3000);
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 1 OF 7: BEGIN CHECKOUT");
        screenShot("UC2cartPage");
        ImageDifference("UC2cartPage", "UC2cartPage", "UC2cartPageDiff");

        clickNextButton();
        Thread.sleep(1000);

    }


    @And("Navigates to shipping details page and click on next Button.")
    public void UC2ShippingDetailsPage() {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 2 OF 7: ID LOOKUP PERFORMED, NO PROFILE FOUND");
        screenShot("UC2ShippingDetailsPage");
        ImageDifference("UC2ShippingDetailsPage", "UC2ShippingDetailsPage", "UC2ShippingDetailsPageDiff");
        clickNextButton();

    }

    @And("Navigates to Add a card page and click on {string} and select {string} and click on next button.")
    public void UC2CardDetailsPage(String testInfoButton, String cardDetails) throws InterruptedException {
        Thread.sleep(1000);
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 3 OF 7: CHECK OUT WITH NEW CARD");
        screenShot("UC2CardDetailsPageWithoutData");
        ImageDifference("UC2CardDetailsPageWithoutData", "UC2CardDetailsPageWithoutData", "UC2CardDetailsPageWithoutDataDiff");
        driver.findElement(By.xpath(prop.getProperty(testInfoButton))).click();
        driver.findElement(By.xpath(prop.getProperty(cardDetails))).click();
        Thread.sleep(1000);
        screenShot("UC2CardDetailsPageWithData");
        ImageDifference("UC2CardDetailsPageWithData", "UC2CardDetailsPageWithData", "UC2CardDetailsPageWithDataDiff");
        Thread.sleep(1000);
        clickNextButton();
        Thread.sleep(1000);


    }

    @Given("Navigates to DCF page and  click on next button.")
    public void NavigateToDCFPage() throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 4 OF 7: ENROLL IN CLICK TO PAY");
        screenShot("UC2DCFPage");
        ImageDifference("UC2DCFPage", "UC2DCFPage", "UC2DCFPageDiff");
        clickNextButton();
        Thread.sleep(1000);

    }

    @Given("Navigates to payment process page and click on next Button")
    public void NavigateToPaymentProcessingPage() throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 5 OF 7: PAYMENT PROCESSING");
        screenShot("UC2PaymentProcessingPage");
        ImageDifference("UC2PaymentProcessingPage", "UC2PaymentProcessingPage", "UC2PaymentProcessingPageDiff");
        clickNextButton();
        Thread.sleep(1000);


    }

    @Given("navigate to review page and click on next.")
    public void NavigateToReviewPage() throws InterruptedException {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 6 OF 7: PLACE ORDER");
        screenShot("UC2ReviewPage");
        ImageDifference("UC2ReviewPage", "UC2ReviewPage", "UC2ReviewPageDiff");
        clickNextButton();
        Thread.sleep(1000);

    }

    @Given("navigate to Order Confirmation page Click on {string}")
    public void NavigateToOrderConfirmationPage(String string) {
        elementToVisible(stepDefinitionText());
        Assert.assertEquals(stepDefinitionText().getText(), "STEP 7 OF 7: ORDER CONFIRMATION");
        screenShot("UC2orderConfirmationPage");
        ImageDifference("UC2orderConfirmationPage", "UC2orderConfirmationPage", "UC2orderConfirmationPage");
        closeDriver();

    }


    @When("Delete all the screenshot Folders")
    public void deleteAllTheScreenshotFolders() {
        try {
            FileUtils.cleanDirectory(new File("src//test//java//Images//ScreenShot"));
            FileUtils.cleanDirectory(new File("src//test//java//Images//Difference"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
