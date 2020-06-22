package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AddUserTest {

    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void addUserTest() throws Exception{

        WebDriver driver = RunEnvironment.getWebDriver();

        driver.get("http://localhost:8080/CpFinalArtifactAdmin_war_exploded/" + "addUser");


        driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/form/div[1]/input")).sendKeys("username-test");
        driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/form/div[2]/input")).sendKeys("fullname-test");
        driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/form/div[3]/input")).sendKeys("email-test@mail.com");
        driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/form/div[4]/input")).sendKeys("password-test");
        driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/form/div[5]/input")).sendKeys("password-test");



        WebElement webElement = driver.findElement(By.xpath("//button[contains(@class, 'btn')][contains(., 'Submit')]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);

        Thread.sleep(500);

        Actions builder = new Actions(driver);

        webElement.click();


        WebElement webElement2 = driver.findElement(By.xpath("/html/body/div[1]/aside[1]/div/div[6]/div/div/nav/ul/li[2]/a/p"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement2);

        webElement2.click();

        assertNotEquals(driver.findElements( By.xpath("/html/body/div[2]/section/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[*]/td[1][contains(.,'" + "username-test" + "')]") ).size(), 0);

    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}