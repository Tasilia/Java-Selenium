package Tests;

import Data.Data;
import Page.AuthPage;
import Page.InputsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InputsTest {
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

    @ParameterizedTest
    @ValueSource(strings = {"test", "test@", "test.ru", ""})
    public void invalidEmail(String invalidEmail) {
        AuthPage authPage = new AuthPage(driver);
        InputsPage inputsPage = authPage.login(data.correctEmail, data.correctPassword);
        inputsPage.enterEmailField(invalidEmail);
        inputsPage.clickSend();
        By cssError = inputsPage.getCssError();
        Assertions.assertTrue(driver.findElement(cssError).isDisplayed());
        Assertions.assertTrue(driver.findElement(cssError).getCssValue("color").equals(data.errorColor));
        Assertions.assertTrue(driver.findElement(cssError).getText().equals(data.textEmailFormat));
    }

    @Test
    public void sendWithoutName() {
        AuthPage authPage = new AuthPage(driver);
        InputsPage inputsPage = authPage.login(data.correctEmail, data.correctPassword);
        inputsPage.enterEmailField(data.correctEmail);
        inputsPage.clickSend();
        By cssError = inputsPage.getCssError();
        Assertions.assertTrue(driver.findElement(cssError).isDisplayed());
        Assertions.assertTrue(driver.findElement(cssError).getCssValue("color").equals(data.errorColor));
        Assertions.assertTrue(driver.findElement(cssError).getText().equals(data.textEmptyNameField));
    }

    @Test
    public void shouldCloseErrorMessage() throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        InputsPage inputsPage = authPage.login(data.correctEmail, data.correctPassword);
        inputsPage.clickSend();
        inputsPage.closeErrorMessage();
        Thread.sleep(200);
        Assertions.assertTrue(driver.findElements(inputsPage.getCssError()).isEmpty());
    }

    public InputsPage enterEmailAndName() {
        AuthPage authPage = new AuthPage(driver);
        InputsPage inputsPage = authPage.login(data.correctEmail, data.correctPassword);
        inputsPage.enterEmailField(data.correctEmail);
        inputsPage.enterNameField(data.name);
        return inputsPage;
    }

    @Test
    public void modalShouldAppear() {
        InputsPage inputsPage = enterEmailAndName();
        inputsPage.clickSend();
        By cssModal = inputsPage.getCssModal();
        Assertions.assertTrue(driver.findElement(cssModal).isDisplayed());
        Assertions.assertTrue(driver.findElement(cssModal).getText().equals(data.textModal));
    }

    @Test
    public void closeModal() throws InterruptedException {
        InputsPage inputsPage = enterEmailAndName();
        inputsPage.clickSend();
        inputsPage.closeModal();
        Thread.sleep(200);
        Assertions.assertTrue(driver.findElements(inputsPage.getCssModal()).isEmpty());
    }

    @Test
    public void Var1() {
        InputsPage inputsPage = enterEmailAndName();
        inputsPage.clickSend();
        Assertions.assertTrue(driver.findElement(By.cssSelector("tbody")).isDisplayed());
        Assertions.assertTrue(data.correctEmail.equals(inputsPage.getTBodeText("E-Mail")));
        Assertions.assertTrue(data.name.equals(inputsPage.getTBodeText("Name")));
        Assertions.assertTrue("Мужской".equals(inputsPage.getTBodeText("Gender")));
        Assertions.assertTrue("Нет".equals(inputsPage.getTBodeText("Check")));
        Assertions.assertTrue("".equals(inputsPage.getTBodeText("Select")));
    }

    @Test
    public void Var2() {
        InputsPage inputsPage = enterEmailAndName();
        inputsPage.selectFemaleGender();
        inputsPage.check("1.1");
        inputsPage.select("2.1");
        inputsPage.clickSend();
        Assertions.assertTrue(driver.findElement(By.cssSelector("tbody")).isDisplayed());
        Assertions.assertTrue(data.correctEmail.equals(inputsPage.getTBodeText("E-Mail")));
        Assertions.assertTrue(data.name.equals(inputsPage.getTBodeText("Name")));
        Assertions.assertTrue("Женский".equals(inputsPage.getTBodeText("Gender")));
        Assertions.assertTrue("1.1".equals(inputsPage.getTBodeText("Check")));
        Assertions.assertTrue("2.1".equals(inputsPage.getTBodeText("Select")));
    }

    @Test
    public void Var3() {
        InputsPage inputsPage = enterEmailAndName();
        inputsPage.selectFemaleGender();
        inputsPage.check("1.2");
        inputsPage.select("2.2");
        inputsPage.clickSend();
        Assertions.assertTrue(driver.findElement(By.cssSelector("tbody")).isDisplayed());
        Assertions.assertTrue(data.correctEmail.equals(inputsPage.getTBodeText("E-Mail")));
        Assertions.assertTrue(data.name.equals(inputsPage.getTBodeText("Name")));
        Assertions.assertTrue("Женский".equals(inputsPage.getTBodeText("Gender")));
        Assertions.assertTrue("1.2".equals(inputsPage.getTBodeText("Check")));
        Assertions.assertTrue("2.2".equals(inputsPage.getTBodeText("Select")));
    }

    @Test
    public void Var4() {
        InputsPage inputsPage = enterEmailAndName();
        inputsPage.check("1.1");
        inputsPage.check("1.2");
        inputsPage.select("2.3");
        inputsPage.clickSend();
        Assertions.assertTrue(driver.findElement(By.cssSelector("tbody")).isDisplayed());
        Assertions.assertTrue(data.correctEmail.equals(inputsPage.getTBodeText("E-Mail")));
        Assertions.assertTrue(data.name.equals(inputsPage.getTBodeText("Name")));
        Assertions.assertTrue("Мужской".equals(inputsPage.getTBodeText("Gender")));
        Assertions.assertTrue("1.1, 1.2".equals(inputsPage.getTBodeText("Check")));
        Assertions.assertTrue("2.3".equals(inputsPage.getTBodeText("Select")));
    }
}
