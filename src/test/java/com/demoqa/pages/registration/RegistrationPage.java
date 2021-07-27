package com.demoqa.pages.registration;

import com.demoqa.pages.registration.elements.Calendar;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final static String FORM_TITLE = "Student Registration Form";

    private static final Calendar CALENDAR = new Calendar();

    public void openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
    }

    public RegistrationPage typeFirstName(String firstName) {
        $x("//input[@id='firstName']").setValue(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        $x("//input[@id='lastName']").setValue(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        $x("//input[@id='userEmail']").setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        $x("//label[text()='" + gender +"']").click();
        return this;
    }

    public RegistrationPage typePhoneNumber(String number) {
        $x("//input[@id='userNumber']").setValue(number);
        return this;
    }

    public RegistrationPage setDate(String day, String month, String year) {
        CALENDAR.setDate(day, month, year);
        return this;
    }

    public RegistrationPage typeSubjects(String subjects){
        $x("//input[@id='subjectsInput']").setValue("Computer Science").pressEnter();
        return this;
    }

    public RegistrationPage typeHobbies(String hobbies){
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String pictureFileName){
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + pictureFileName));
        return this;
    }

    public RegistrationPage typeAddress(String address){
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationPage selectState(String state){
        $("#state").click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city){
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

    public void clickSubmitButton(){
        $("#submit").click();
    }

    public RegistrationPage verifyResults(String containsValue) {
        $("[role=dialog]").$(".table-responsive").shouldHave(text(containsValue));
        return this;
    }
}
