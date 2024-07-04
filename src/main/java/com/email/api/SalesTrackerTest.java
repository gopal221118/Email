package com.email.api;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SalesTrackerTest {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", args[0]);
        

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open the HTML file
            driver.get(args[1]);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Find all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath("//button[contains(@class,'submit-btn')]/parent::center"));

            // Iterate over each row
           
            for (int i = 0; i < rows.size(); i++) {
                try {
                    // Locate the current row again to avoid stale element reference
                    WebElement row = rows.get(i);
                    
                    // Find submit button within the current row
                    WebElement submitButton = row.findElement(By.xpath(".//button[contains(@class,'submit-btn')]/parent::center"));

                    
                    // Extract the text next to the Submit button
                    String subtext = (submitButton.getText().replace("Submit", "")).trim();
                    System.out.println(subtext);
                    if (subtext.length() == 0) {
                        System.out.println(i + "------------" + subtext + "--------------------" + subtext.length());
                         // Click the Submit button
                        submitButton.click();

                        // Wait for the alert to be present
                        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

                        // Accept the alert
                        alert.accept();
                    }
                } catch (StaleElementReferenceException e) {
                    // Re-locate the rows and retry the current row
                    rows = driver.findElements(By.xpath("//button[contains(@class,'submit-btn')]/parent::center"));
                    i--; // Decrement the index to retry the current row
                } catch (NoAlertPresentException e) {
                    // No alert found, continue with the next row
                    System.out.println("No alert present for row: " + (i + 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}