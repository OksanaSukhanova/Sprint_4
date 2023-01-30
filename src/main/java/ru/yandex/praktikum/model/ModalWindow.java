package ru.yandex.praktikum.model;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalWindow {
    private final WebDriver driver;

    // Заголовок модального окна
    private static final By MODAL_HEADER = By.cssSelector("div[class*='Order_ModalHeader']");
    // Кнопка подтверждения заказа
    private static final By CONFIRM_ORDER_BUTTON = By.xpath("//div[contains(@class, 'Order_Buttons')]/button[text()='Да']");
    // Кнопка проверки статуса
    private static final By CHECK_ORDER_BUTTON = By.xpath("//button[text()='Посмотреть статус']");

    public ModalWindow(WebDriver driver) {
        this.driver = driver;
    }

    public ModalWindow checkModalWindowIsLoaded() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(MODAL_HEADER));
        return this;
    }

    public ModalWindow confirmOrder() {
        driver.findElement(CONFIRM_ORDER_BUTTON).click();
        return this;
    }

    public void checkOrderIsProcessed() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(CHECK_ORDER_BUTTON));
        MatcherAssert.assertThat(driver.findElement(MODAL_HEADER).getText(), CoreMatchers.containsString("Заказ оформлен"));
    }
}
