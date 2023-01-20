package Page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InputsPage {
    WebDriver driver;
    private By emailField = By.cssSelector("input#dataEmail");
    private By nameField = By.cssSelector("input#dataName");
    private By emailLabel = By.cssSelector("label[for=\"dataEmail\"]");
    private By nameLabel = By.cssSelector("label[for=\"dataName\"]");
    private By submitButton = By.cssSelector("button#dataSend");
    private By selectGender = By.cssSelector("select#dataGender");
    private By genderLabel = By.cssSelector("label[for=\"dataGender\"]");
    private By emailError = By.cssSelector(".uk-alert");
    private By closeError = By.cssSelector(".uk-alert a");
    private String textEmailLabel = "E-Mail:";
    private String textNameLabel = "Имя:";
    private String textGenderLabel = "Пол:";
    private String textButton = "Добавить";
    private String checkbox11 = "//input[@id=\"dataCheck11\"]";
    private String checkbox12 = "//input[@id=\"dataCheck12\"]";
    private String textCheckbox11 = "Вариант 1.1";
    private String textCheckbox12 = "Вариант 1.2";
    private String xpath = "//parent::div";
    private String select21 = "//input[@id=\"dataSelect21\"]";
    private String select22 = "//input[@id=\"dataSelect22\"]";
    private String select23 = "//input[@id=\"dataSelect23\"]";
    private String textSelect21 = "Вариант 2.1";
    private String textSelect22 = "Вариант 2.2";
    private String textSelect23 = "Вариант 2.3";
    private By thread = By.cssSelector("thead th");
    private By thEmail = By.xpath("//th[text()=\"E-Mail\"]");
    private By thName = By.xpath("//th[text()=\"Имя\"]");
    private By thGender = By.xpath("//th[text()=\"Пол\"]");
    private By thSelect1 = By.xpath("//th[text()=\"Выбор 1\"]");
    private By thSelect2 = By.xpath("//th[text()=\"Выбор 2\"]");
    private By modal = By.cssSelector(".uk-modal-content");
    private By closeModal = By.cssSelector("button.uk-modal-close");
    private By tbody = By.cssSelector("tbody td");

    //select#dataGender option
    public InputsPage(WebDriver driver) {
        this.driver = driver;
        Assertions.assertTrue(driver.findElement(emailField).isDisplayed());
        Assertions.assertTrue(driver.findElement(emailLabel).isDisplayed());
        Assertions.assertTrue(driver.findElement(emailLabel).getText().equals(textEmailLabel));
        Assertions.assertTrue(driver.findElement(nameField).isDisplayed());
        Assertions.assertTrue(driver.findElement(nameLabel).isDisplayed());
        Assertions.assertTrue(driver.findElement(nameLabel).getText().equals(textNameLabel));
        Assertions.assertTrue(driver.findElement(submitButton).isDisplayed());
        Assertions.assertTrue(driver.findElement(submitButton).getText().equals(textButton));
        Assertions.assertTrue(driver.findElement(selectGender).isDisplayed());
        Assertions.assertTrue(driver.findElement(genderLabel).isDisplayed());
        Assertions.assertTrue(driver.findElement(genderLabel).getText().equals(textGenderLabel));
        Assertions.assertTrue(driver.findElement(By.xpath(checkbox11 + xpath)).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath(checkbox11 + xpath)).getText().contains(textCheckbox11));
        Assertions.assertTrue(driver.findElement(By.xpath(checkbox12 + xpath)).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath(checkbox12 + xpath)).getText().contains(textCheckbox12));
        Assertions.assertTrue(driver.findElement(By.xpath(select21 + xpath)).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath(select21 + xpath)).getText().contains(textSelect21));
        Assertions.assertTrue(driver.findElement(By.xpath(select22 + xpath)).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath(select22 + xpath)).getText().contains(textSelect22));
        Assertions.assertTrue(driver.findElement(By.xpath(select23 + xpath)).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath(select23 + xpath)).getText().contains(textSelect23));
        Assertions.assertEquals(5, driver.findElements(thread).size());
        Assertions.assertTrue(driver.findElement(thEmail).isDisplayed());
        Assertions.assertTrue(driver.findElement(thName).isDisplayed());
        Assertions.assertTrue(driver.findElement(thGender).isDisplayed());
        Assertions.assertTrue(driver.findElement(thSelect1).isDisplayed());
        Assertions.assertTrue(driver.findElement(thSelect2).isDisplayed());
    }

    public void enterEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public By getCssError() {
        return emailError;
    }

    public By getCssModal() {
        return modal;
    }

    public void clickSend() {
        driver.findElement(submitButton).click();
    }

    public void closeErrorMessage() {
        driver.findElement(closeError).click();
    }

    public void closeModal() {
        driver.findElement(closeModal).click();
    }

    public String getTBodeText(String id) {
        List<WebElement> tds = driver.findElements(tbody);
        Assertions.assertEquals(5, tds.size());
        String td = null;
        switch (id) {
            case ("E-Mail"):
                td = tds.get(0).getText();
                break;
            case ("Name"):
                td = tds.get(1).getText();
                break;
            case ("Gender"):
                td = tds.get(2).getText();
                break;
            case ("Check"):
                td = tds.get(3).getText();
                break;
            case ("Select"):
                td = tds.get(4).getText();
                break;
            default:
                break;
        }
        if (td == null) {
            throw new RuntimeException();
        } else {
            return td;
        }
    }

    public void selectFemaleGender() {
        driver.findElement(selectGender).click();
        driver.findElements(By.cssSelector("select#dataGender option")).get(1).click();
    }

    public void check(String check) {
        switch (check) {
            case ("1.1"):
                driver.findElement(By.xpath(checkbox11)).click();
                break;
            case ("1.2"):
                driver.findElement(By.xpath(checkbox12)).click();
                break;
            default:
                break;
        }
    }

    public void select(String check) {
        switch (check) {
            case ("2.1"):
                driver.findElement(By.xpath(select21)).click();
                break;
            case ("2.2"):
                driver.findElement(By.xpath(select22)).click();
                break;
            case ("2.3"):
                driver.findElement(By.xpath(select23)).click();
                break;
            default:
                break;
        }
    }
}
