package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.model.AboutMeFormPage;
import ru.yandex.praktikum.model.AboutScooterFormPage;
import ru.yandex.praktikum.model.MainPage;
import ru.yandex.praktikum.model.ModalWindow;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String term;
    private final String color;
    private final String comment;

    public OrderTest(String name, String lastName, String address, String metroStation,
                     String phoneNumber, String date, String term, String color, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Мистер", "Никто", "Нигде", "Чистые пруды", "89990001122", "28.02.2023", "сутки", "серая безысходность", "комментарий"},
                {"Имя", "Фамилия", "Адрес", "Преображенская площадь", "89990001122", "30.01.2023", "двое суток", "чёрный жемчуг", "орпорп"},
        };
    }

    @Test
    public void orderScooterByHeaderButton_expectOrderConfirmed() {
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open().checkPageIsLoaded().acceptCookie().clickHeaderOrderButton();
        AboutMeFormPage aboutMeFormPage = new AboutMeFormPage(driver);
        aboutMeFormPage.checkModalWindowHeader().withName(name).withLastName(lastName)
                .withAddress(address).selectMetroStation(metroStation).withPhoneNumber(phoneNumber).nextStep();
        AboutScooterFormPage aboutScooterFormPage = new AboutScooterFormPage(driver);
        aboutScooterFormPage.checkModalWindowHeader().typeDeliveryDate(date).selectRentTerm(term)
                .selectScooterColor(color).typeCourierComment(comment).checkout();
        ModalWindow modalWindow = new ModalWindow(driver);
        modalWindow.checkModalWindowIsLoaded().confirmOrder().checkOrderIsProcessed();
    }

    @Test
    public void orderScooterByHomeButton_expectOrderConfirmed() {
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open().checkPageIsLoaded().acceptCookie().clickHomeOrderButton();
        AboutMeFormPage aboutMeFormPage = new AboutMeFormPage(driver);
        aboutMeFormPage.checkModalWindowHeader().withName(name).withLastName(lastName)
                .withAddress(address).selectMetroStation(metroStation).withPhoneNumber(phoneNumber).nextStep();
        AboutScooterFormPage aboutScooterFormPage = new AboutScooterFormPage(driver);
        aboutScooterFormPage.checkModalWindowHeader().typeDeliveryDate(date).selectRentTerm(term)
                .selectScooterColor(color).typeCourierComment(comment).checkout();
        ModalWindow modalWindow = new ModalWindow(driver);
        modalWindow.checkModalWindowIsLoaded().confirmOrder().checkOrderIsProcessed();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
