package Data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Data {
    Faker faker = new Faker(new Locale("ru"));
    public String correctEmail = "test@protei.ru";
    public String correctPassword = "test";
    public String wrongEmail = faker.internet().emailAddress();
    public String name = faker.name().name();
    public String textModal = "Данные добавлены.";
    public String textEmailFormat = "Неверный формат E-Mail";
    public String textInvalidEmailPassword = "Неверный E-Mail или пароль";
    public String textEmptyNameField = "Поле имя не может быть пустым";
    public String errorColor = "rgba(216, 80, 48, 1)";
    public String path = "file:///Users/Tasilia/Desktop/QATest/qa-test.html";
}
