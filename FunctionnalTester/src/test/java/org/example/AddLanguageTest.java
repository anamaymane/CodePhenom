package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.example.EnvironmentManager;
import org.example.RunEnvironment;
import org.openqa.selenium.interactions.Actions;

class AddLanguageTest {

    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void addLanguageTest() throws Exception {

        WebDriver driver = RunEnvironment.getWebDriver();

        driver.get("http://localhost:8080/CpFinalArtifactAdmin_war_exploded/" + "addLanguage");

        String LanguageToAdd = "LanguageTest";

        driver.findElement(By.id("LanguageName")).sendKeys(LanguageToAdd);

        WebElement webElement = driver.findElement(By.xpath("//button[contains(@class, 'btn') and @id='submit'][contains(., 'Submit')]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);

        Thread.sleep(500);

        Actions builder = new Actions(driver);

        webElement.click();


        assertNotEquals(driver.findElements( By.xpath("/html/body/div[2]/section[2]/div/div/div/div[2]/table/tbody/tr[contains(., '"+ LanguageToAdd +"')]") ).size(), 0);

    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}