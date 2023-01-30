package ru.yandex.praktikum.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutMeFormPage {
    private final WebDriver driver;

    // Заголовок формы
    private static final By HEADER = By.cssSelector("div[class*='Order_Header']");
    // Поле ввода имени
    private static final By NAME_INPUT = By.cssSelector("[placeholder='* Имя']");
    // Поле ввода фамилии
    private static final By LAST_NAME_INPUT = By.cssSelector("[placeholder='* Фамилия']");
    // Поле ввода адреса
    private static final By ADDRESS_INPUT = By.cssSelector("[placeholder*='* Адрес']");
    // Поле ввода станции метро
    private static final By METRO_STATION_INPUT = By.cssSelector("[placeholder='* Станция метро']");
    // Список станций метро
    private static final By METRO_STATIONS_LIST = By.cssSelector("div[class*='select-search']>ul>li div[class*='Order_Text']");
    // Поле ввода номера телефона
    private static final By PHONE_NUMBER_INPUT = By.cssSelector("[placeholder*='* Телефон']");
    // Кнопка Далее
    private static final By NEXT_STEP_BUTTON = By.xpath(".//button[text()='Далее']");

    public AboutMeFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public AboutMeFormPage checkModalWindowHeader() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(HEADER));
        Assert.assertEquals("Для кого самокат", driver.findElement(HEADER).getText());
        return this;
    }

    public AboutMeFormPage withName(String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
        return this;
    }

    public AboutMeFormPage withLastName(String lastName) {
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        return this;
    }

    public AboutMeFormPage withAddress(String address) {
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
        return this;
    }

    public AboutMeFormPage selectMetroStation(String metroStation) {
        driver.findElement(METRO_STATION_INPUT).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(METRO_STATIONS_LIST));
        driver.findElement(By.xpath("//div[text() = '" + metroStation + "']")).click();
        return this;
    }

    public AboutMeFormPage withPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_INPUT).sendKeys(phoneNumber);
        return this;
    }

    public void nextStep() {
        driver.findElement(NEXT_STEP_BUTTON).click();
    }
}
