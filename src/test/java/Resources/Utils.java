package Resources;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class Utils
{
    public WebDriver driver;
    Properties prop = new Properties();
    FileInputStream fi;

    {
        try {
            fi = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//configs//Configuration.properties");
            prop.load(fi);
        } catch (IOException e)
        {

        }
    }
    public WebDriver initialiseBrowser() {

        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);


        } else if (browserName.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options=new FirefoxOptions();
            options.addArguments("--incognito");
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public void goToURL() throws IOException {
        Properties prop = new Properties();
        FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//configs//Configuration.properties");
        prop.load(fi);
        String url = prop.getProperty("url");
        driver.get(url);



    }

    public void screenShot(String fileName)
    {

//        Shutterbug.shootPage(driver, Capture.FULL,true).withName(fileName).save("src//test//java//Images//screenShot");
              Shutterbug.shootPage(driver, Capture.FULL_SCROLL, true).withName(fileName).save("src//test//java//Images//screenShot");;

//        Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save();
//
    }


    public void clickNextButton()
    {
       WebElement button= driver.findElement(By.xpath("//div[@class='flex']//button[1]"));
       button.click();
    }


    public void entirePageScreenShot() throws IOException {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "PNG", new File("./img/" + driver.getTitle() + ".png"));


    }

    public void specificElementScreenshot()
    {

        WebElement myWebElement = driver.findElement(By.cssSelector("#my_element"));
        new AShot().takeScreenshot(driver, myWebElement);
    }


    public void ImageDifference(String baseLine, String screenShot, String diffImage)  {
        //Compare Base vs. Test Screenshots
        BufferedImage expectedImage=null;
        BufferedImage actualImage=null;
        try {
            expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + "//src//test//java//Images//baseLine//" + baseLine + ".png"));
            actualImage = ImageIO.read(new File(System.getProperty("user.dir") + "//src//test//java//Images//ScreenShot//" + screenShot + ".png"));

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff imageDiff = imageDiffer.makeDiff(expectedImage, actualImage).withDiffSizeTrigger(5);
        if(imageDiff.hasDiff()) {
            System.out.println("Images are not equal");
            try {
                ImageIO.write(imageDiff.getMarkedImage(), "PNG", new File("src//test//java//Images//Difference//" + diffImage + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}
        else
        {
            System.out.println("images are equal");
        }

    }


    public void elementToVisible(WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public WebElement stepDefinitionText()
    {
       WebElement pageElement= driver.findElement(By.xpath(prop.getProperty("pageText")));
       return pageElement;
    }

    public void closeDriver()
    {
        driver.close();
    }

   @BeforeTest(alwaysRun = true)
    public void deleteDirectory()
    {
        try {
            FileUtils.cleanDirectory(new File("src//test//java//Images//ScreenShot"));
            FileUtils.cleanDirectory(new File("src//test//java//Images//Difference"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }








}
