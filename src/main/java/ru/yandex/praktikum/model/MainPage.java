package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    // Адрес страницы
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // Логотип в хэдере
    private static final By HEADER_LOGO = By.cssSelector("div[class*='Header_Logo']");
    // Кнопка куки
    private static final By ACCEPT_COOKIE = By.cssSelector("button[class*='App_CookieButton']");
    // Кнопка заказа в хэдере
    private static final By HEADER_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Header')]//button[text()='Заказать']");
    // Кнопка заказа внизу страницы
    private static final By HOME_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Home_Finish')]//button[text()='Заказать']");
    // Элементы раскрывающегося списка
    private static final String ACCORDION_ITEMS = "//div[@class='accordion']//div";
    // Внутренний текст элемента раскрывающегося списка
    private static final By ACCORDION_ITEM_INNER_TEXT = By.xpath("../..//div[@class='accordion__panel']//p");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage checkPageIsLoaded() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(HEADER_LOGO));
        return this;
    }

    public MainPage acceptCookie() {
        driver.findElement(ACCEPT_COOKIE).click();
        return this;
    }

    public MainPage clickHeaderOrderButton() {
        driver.findElement(HEADER_ORDER_BUTTON).click();
        return this;
    }

    public MainPage clickHomeOrderButton() {
        driver.findElement(HOME_ORDER_BUTTON).click();
        return this;
    }

    public String getInnerTextByAccordionItemText(String itemText) {
        WebElement item = driver.findElement(By.xpath(ACCORDION_ITEMS + "[text()='" + itemText + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);
        item.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions
                .visibilityOf(item.findElement(ACCORDION_ITEM_INNER_TEXT)));
        return item.findElement(ACCORDION_ITEM_INNER_TEXT).getText();
    }
}
