package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DeleteUserTest {

    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void deleteUserTest() throws Exception{

        WebDriver driver = RunEnvironment.getWebDriver();

        driver.get("http://localhost:8080/CpFinalArtifactAdmin_war_exploded/" + "editUser?username=username-test");

        WebElement webElement = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/div[1]/div[1]/div/div[2]/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(500);
        webElement.click();

        WebElement webElement2 = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/div[1]/div[1]/div/div[3]/div/div/div[2]/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement2);
        Thread.sleep(500);
        webElement2.click();

        WebElement webElement3 = driver.findElement(By.xpath("/html/body/div[1]/aside[1]/div/div[6]/div/div/nav/ul/li[2]/a/p"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement3);
        Thread.sleep(500);
        webElement3.click();

        assertEquals(driver.findElements( By.xpath("/html/body/div[2]/section/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[*]/td[1][contains(.,'" + "username-test" + "')]") ).size(), 0);

    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}