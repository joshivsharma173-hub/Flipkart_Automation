package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    ChromeDriver driver;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
    }

    /*
     * Write your selenium wrappers here
     */
    // Enter a text
    public void enterText(WebElement ele, String text) {
        ele.click();
        ele.clear();

        ele.sendKeys(text);
        ele.sendKeys(Keys.ENTER);
    }

    public void clickOnElem(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", ele);

    }

    // Navigate to URL
    public void navigateToUrl(String text) {
        driver.get(text);
    }

    public static void pause(long n) throws InterruptedException {
        Thread.sleep(n);
    }

    public void starsCount(List<WebElement> list1, double stars, boolean moreThanStar) {

        int count = 0;
        for (WebElement ele : list1) {
            double starCount = Double.parseDouble(ele.getText().trim());
            if (moreThanStar) {
                if (starCount > stars) {
                    count++;
                }

            } else {
                if (starCount <= stars) {
                    count++;
                }
            }

        }
        System.out.println("Number of Products of the said rating i.e. " + stars + " ====>" + count);

    }

    public void discountPercCount(List<WebElement> list, int discNum) {
        int count = 0;
        for (WebElement parentEle : list) {
            WebElement childProductTitle = parentEle.findElement(By.xpath(".//div//div[1]"));
            if (isElementPresent(By.xpath("//div[contains(@class,'HQe')]"))) {
                WebElement childDiscount = parentEle.findElement(By.xpath(".//div[contains(@class,'HQe')]"));
                String discountPerc = childDiscount.getText();
                int disc = Integer.parseInt(discountPerc.split("%")[0]);
                String productTitle = childProductTitle.getText();
                if (disc > discNum) {
                    System.out.println(productTitle + " ===> " + disc + "% off");
                    count++;
                }
            }

        }
        System.out.println("No. of Products with discount % greater than " + discNum + " are ======> " + count);

    }
    /// //div[2]//div/div[3]//span

    private boolean isElementPresent(By xpath) {
        // TODO Auto-generated method stub
        return driver.findElements(xpath).size() > 0;
    }

    public void getNProduct(List<WebElement> list, int n) {

        List<Double> numList = new ArrayList<>();
        Map<Double, String> sortedMap = new HashMap<>();
        for (WebElement parentEle : list) {
            WebElement childReviewCount = parentEle.findElement(By.xpath(".//div//span[2]"));
            WebElement product = parentEle.findElement(By.xpath(".//a[2]"));
            WebElement childImage = parentEle.findElement(By.xpath(".//a[1]"));
            String imageUrl = childImage.getAttribute("href");
            String productText = product.getText();
            String strText = childReviewCount.getText();
            String strText1 = strText.replaceAll("[(),]", "");
            double actualNum = Double.parseDouble(strText1);
            sortedMap.put(actualNum, "Poduct Name ====> " + productText + " Image Url =======> " + imageUrl);
            numList.add(actualNum);

        }
        Collections.sort(numList, Collections.reverseOrder());
        System.out.println(numList);

        for (int i = 0; i < n; i++) {
            Double num = numList.get(i);
            System.out.println(i + 1 + ". [" + num + "]  " + sortedMap.get(num));

        }

    }

}
