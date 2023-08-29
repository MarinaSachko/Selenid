package ru.netology.sachko;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.openqa.selenium.Keys;
import java.util.Locale;

class STest {

    @Test
    void shouldTestCorrectField() {
        open("http://localhost:9999");
        $("[type='Text']").setValue("Москва");

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, +5);

        Date date = calendar.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String datePlas5 = format1.format(date).toString();

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);

        $("[data-test-id='date'] input").sendKeys(datePlas5);

        $("[name='name']").setValue("Сачко Марина");
        $("[name='phone']").setValue("+79788853148");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + datePlas5));
    }
}

