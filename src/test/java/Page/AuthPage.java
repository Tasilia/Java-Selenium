package Page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage {
    WebDriver driver;
    private By emailField = By.cssSelector("input#loginEmail");
    private By passwordField = By.cssSelector("input#loginPassword");
    private By emailLabel = By.cssSelector("label[for=\"loginEmail\"]");
    private By passwordLabel = By.cssSelector("label[for=\"loginPassword\"]");
    private By submitButton = By.cssSelector("button#authButton");
    private By emailError = By.cssSelector(".uk-alert");
    private By closeError = By.cssSelector(".uk-alert a");
    private String textEmailLabel = "E-Mail:";
    private String textPasswordLabel = "Пароль:";
    private String textButton = "Вход";

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        Assertions.assertTrue(driver.findElement(emailField).isDisplayed());
        Assertions.assertTrue(driver.findElement(passwordField).isDisplayed());
        Assertions.assertTrue(driver.findElement(emailLabel).isDisplayed());
        Assertions.assertTrue(driver.findElement(passwordLabel).isDisplayed());
        Assertions.assertTrue(driver.findElement(emailLabel).getText().equals(textEmailLabel));
        Assertions.assertTrue(driver.findElement(passwordLabel).getText().equals(textPasswordLabel));
        Assertions.assertTrue(driver.findElement(submitButton).isDisplayed());
        Assertions.assertTrue(driver.findElement(submitButton).getText().equals(textButton));
    }

    public void clickEnter() {
        driver.findElement(submitButton).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public InputsPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickEnter();
        return new InputsPage(driver);
    }

    public void closeErrorMessage() {
        driver.findElement(closeError).click();
    }

    public By getCssError() {
        return emailError;
    }
}
