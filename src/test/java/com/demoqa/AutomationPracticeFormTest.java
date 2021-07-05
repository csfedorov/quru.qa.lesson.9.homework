package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    public void positiveFillTest() {
        open("/automation-practice-form");

        $x("//input[@id='firstName']").setValue("TestFirstName");
        $x("//input[@id='lastName']").setValue("TestLastName");
        $x("//input[@id='userEmail']").setValue("test@test.test");
        $x("//input[@id='gender-radio-1']").doubleClick();
        $x("//input[@id='userNumber']").setValue("0000000000");

        $x("//input[@id='dateOfBirthInput']").click();
        $x("//select[@class='react-datepicker__month-select']").selectOptionByValue("0");
        $x("//select[@class='react-datepicker__year-select']").selectOptionByValue("1900");
        $x("//div[@class='react-datepicker__week']/*[text()=1]").click();

        $x("//input[@id='subjectsInput']").setValue("Computer Science").pressEnter();
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();

        $x("//input[@id='uploadPicture']").uploadFile(new File("src/test/resources/1.jpg"));

        $x("//textarea[@id='currentAddress']").setValue("Test Address");

        $x("//div[@id='state']").click();
        $(byText("NCR")).click();
        $x("//div[@id='city']").click();
        $(byText("Delhi")).click();

        $x("//button[@type='submit']").click();

        $x("//div[@class='modal-content']").should(Condition.visible);
        $x("//td[text()='Student Name']/following::td[1]").shouldHave(Condition.text("TestFirstName TestLastName"));
        $x("//td[text()='Student Email']/following::td[1]").shouldHave(Condition.text("test@test.test"));
        $x("//td[text()='Gender']/following::td[1]").shouldHave(Condition.text("Male"));
        $x("//td[text()='Mobile']/following::td[1]").shouldHave(Condition.text("0000000000"));
        $x("//td[text()='Date of Birth']/following::td[1]").shouldHave(Condition.text("01 January,1900"));
        $x("//td[text()='Subjects']/following::td[1]").shouldHave(Condition.text("Computer Science"));
        $x("//td[text()='Hobbies']/following::td[1]").shouldHave(Condition.text("Sports, Reading, Music"));
        $x("//td[text()='Picture']/following::td[1]").shouldHave(Condition.text("1.jpg"));
        $x("//td[text()='Address']/following::td[1]").shouldHave(Condition.text("Test Address"));
        $x("//td[text()='State and City']/following::td[1]").shouldHave(Condition.text("NCR Delhi"));
    }
}
