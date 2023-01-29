package ru.yandex.praktikum.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutScooterFormPage {
    private final WebDriver driver;

    // Заголовок формы
    private static final By HEADER = By.cssSelector("div[class*='Order_Header']");
    // Поле ввода даты доставки
    private static final By DELIVERY_DATE_INPUT = By.cssSelector("[placeholder*='* Когда привезти самокат']");
    // Поле ввода срока аренды
    private static final By RENT_TERM_INPUT = By.xpath(".//div[text()='* Срок аренды']");
    // Варианты срока аренды
    private static final By RENT_TERM_OPTIONS = By.cssSelector("div.Dropdown-menu>div[role='option']");
    // Чекбокс выбора черного самоката
    private static final By BLACK_SCOOTER_CHECKBOX = By.cssSelector("input[id = 'black']");
    // Чекбокс выбора серого самоката
    private static final By GREY_SCOOTER_CHECKBOX = By.cssSelector("input[id = 'grey']");
    // Поле ввода комментария курьеру
    private static final By COMMENT_INPUT = By.cssSelector("[placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private static final By CHECKOUT_BUTTON = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");

    public AboutScooterFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public AboutScooterFormPage checkModalWindowHeader() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(DELIVERY_DATE_INPUT));
        Assert.assertEquals("Про аренду", driver.findElement(HEADER).getText());
        return this;
    }

    public AboutScooterFormPage typeDeliveryDate(String date) {
        driver.findElement(DELIVERY_DATE_INPUT).sendKeys(date);
        driver.findElement(HEADER).click();
        return this;
    }

    public AboutScooterFormPage selectRentTerm(String term) {
        driver.findElement(RENT_TERM_INPUT).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(RENT_TERM_OPTIONS));
        driver.findElement(By.xpath("//div[text()='" + term + "']")).click();
        return this;
    }

    public AboutScooterFormPage selectScooterColor(String color) {
        if (color.equals("чёрный жемчуг")) {
            driver.findElement(BLACK_SCOOTER_CHECKBOX).click();
        } else {
            driver.findElement(GREY_SCOOTER_CHECKBOX).click();
        }
        return this;
    }

    public AboutScooterFormPage typeCourierComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
        return this;
    }

    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}
