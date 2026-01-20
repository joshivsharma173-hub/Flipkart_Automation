package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import static demo.wrappers.Wrappers.*;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }

    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("TestCase01 starts.......");
        Wrappers wrapper = new Wrappers(driver);
        wrapper.navigateToUrl("https://www.flipkart.com/");
        pause(3000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
        wrapper.enterText(searchBox, "Washing Machine");
        System.out.println("Waiting for 3 mins");
        pause(3000);
        WebElement popularity = driver.findElement(By.xpath("//div[text()='Popularity']"));
        wrapper.clickOnElem(popularity);
        System.out.println("Waiting for 3 mins");
        pause(3000);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='MKiFS6']"));
        wrapper.starsCount(list, 4.2, false);

        pause(3000);

    }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("=======================TestCase02 starts===================");
        Wrappers wrapper = new Wrappers(driver);
        wrapper.navigateToUrl("https://www.flipkart.com/");
        pause(3000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
        wrapper.enterText(searchBox, "iPhone");
        pause(3000);
        WebElement apple = driver.findElement(By.xpath("//div[text()='Apple']"));
        wrapper.clickOnElem(apple);
        pause(3000);
        WebElement discount = driver.findElement(By.xpath("//div[text()='10% or more']"));
        wrapper.clickOnElem(discount);
        pause(3000);
        List<WebElement> parentList = driver.findElements(By.xpath("//div[@class='ZFwe0M row']"));
        wrapper.discountPercCount(parentList, 17);
        System.out.println("=======================TestCase02 ends===================");
    }

    @Test
    public void testCase03() throws InterruptedException {

        Wrappers wrapper = new Wrappers(driver);
        wrapper.navigateToUrl("https://www.flipkart.com/");
        pause(3000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));

        wrapper.enterText(searchBox, "Coffee Mug");
        pause(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement starAbove = driver
                .findElement(By.xpath("//div[contains(text(),'4') and contains(@class,'buvtMR')]"));
        wrapper.clickOnElem(starAbove);
        pause(10000);

        List<WebElement> list = wait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='RGLWAk']")));
        wrapper.getNProduct(list, 5);
    }

}