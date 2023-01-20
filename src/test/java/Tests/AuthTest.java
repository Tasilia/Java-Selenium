package Tests;

import Page.AuthPage;
import Data.Data;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthTest {
    WebDriver driver;
    Data data = new Data();

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.get(data.path);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void successLogin() {
        AuthPage authPage = new AuthPage(driver);
        authPage.login(data.correctEmail, data.correctPassword);
        Assertions.assertFalse(driver.findElement(By.cssSelector("#authPage")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.cssSelector("#inputsPage")).isDisplayed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "test@", "test.ru", ""})
    public void loginWithInvalidEmail(String invalidEmail) {
        AuthPage authPage = new AuthPage(driver);
        authPage.enterPassword(data.correctPassword);
        authPage.enterEmail(invalidEmail);
        authPage.clickEnter();
        By cssError = authPage.getCssError();
        Assertions.assertTrue(driver.findElement(cssError).isDisplayed());
        Assertions.assertTrue(driver.findElement(cssError).getCssValue("color").equals(data.errorColor));
        Assertions.assertTrue(driver.findElement(cssError).getText().equals(data.textEmailFormat));
    }

    @Test
    public void shouldCloseErrorMessage() throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        authPage.enterPassword(data.correctPassword);
        authPage.clickEnter();
        authPage.closeErrorMessage();
        Thread.sleep(200);
        Assertions.assertTrue(driver.findElements(authPage.getCssError()).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test2", ""})
    public void loginWithWrongPassword(String wrongPassword) {
        AuthPage authPage = new AuthPage(driver);
        authPage.enterEmail(data.correctEmail);
        authPage.enterPassword(wrongPassword);
        authPage.clickEnter();
        By cssError = authPage.getCssError();
        Assertions.assertTrue(driver.findElement(cssError).isDisplayed());
        Assertions.assertTrue(driver.findElement(cssError).getCssValue("color").equals(data.errorColor));
        Assertions.assertTrue(driver.findElement(cssError).getText().equals(data.textInvalidEmailPassword));
    }

    @Test
    public void loginWithWrongEmail() {
        AuthPage authPage = new AuthPage(driver);
        authPage.enterEmail(data.wrongEmail);
        authPage.enterPassword(data.correctPassword);
        authPage.clickEnter();
        By cssError = authPage.getCssError();
        Assertions.assertTrue(driver.findElement(cssError).isDisplayed());
        Assertions.assertTrue(driver.findElement(cssError).getCssValue("color").equals(data.errorColor));
        Assertions.assertTrue(driver.findElement(cssError).getText().equals(data.textInvalidEmailPassword));
    }
}
