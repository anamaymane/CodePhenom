package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteLanguageTest {

    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void deleteLanguageTest() throws Exception{

        WebDriver driver = RunEnvironment.getWebDriver();

        driver.get("http://localhost:8080/CpFinalArtifactAdmin_war_exploded/" + "addLanguage");

        String LanguageToDelete = "LanguageTest";

        WebElement webElement = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/div/div[2]/table/tbody/tr[contains(., '"+LanguageToDelete+"')]/td[2]/a"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);

        Thread.sleep(500);

        Actions builder = new Actions(driver);

        webElement.click();

        assertEquals(driver.findElements(By.xpath("/html/body/div[2]/section[2]/div/div/div/div[2]/table/tbody/tr[contains(., '"+LanguageToDelete+"')]")).size(),0);


    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}