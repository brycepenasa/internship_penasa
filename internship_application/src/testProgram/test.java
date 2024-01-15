package testProgram;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class test {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        System.out.println("Scenario 1");
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        passwordField.submit();  

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (wait.until(ExpectedConditions.urlContains("/inventory.html"))) {
            System.out.println("Login successful");

            WebElement hamburgerButton = driver.findElement(By.id("react-burger-menu-btn"));
            hamburgerButton.click();

            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='logout_sidebar_link']")));
            logoutButton.click();

            if (wait.until(ExpectedConditions.urlContains("https://www.saucedemo.com/"))) {
                System.out.println("Logout successful");
            } else {
                System.out.println("Logout unsuccessful");
            }
            
        } else {
            System.out.println("Login failed");
        }

        System.out.println("");
        System.out.println("Scenario 2");
        
        usernameField = driver.findElement(By.id("user-name"));
        passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("locked_out_user");
        passwordField.sendKeys("secret_sauce");
        passwordField.submit();  
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement errorMessage = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));

        if (errorMessage.isDisplayed()) {
            System.out.println("Login unsuccessful");
            System.out.println("Error Message: " + errorMessage.getText());
        }
        
       
    }
}