package com.demoqa.tests.registration;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.registration.RegistrationPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistrationTest {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    public void positiveFillTest() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = "Other";
        String phone = faker.numerify("##########");
        String day = "1";
        String month = "January";
        String year = "1900";
        String subjects = "Computer Science";
        String hobbies = "Music";
        String picture = "1.jpg";
        String address = faker.address().fullAddress();
        String state = "NCR";
        String city = "Delhi";

        registrationPage.openPage();

        registrationPage
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhoneNumber(phone)
                .setDate(day, month, year)
                .typeSubjects(subjects)
                .typeHobbies(hobbies)
                .uploadPicture(picture)
                .typeAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmitButton();

        registrationPage
                .verifyResults(firstName + " " + lastName)
                .verifyResults(email)
                .verifyResults(gender)
                .verifyResults(phone)
                .verifyResults(day + " " + month + "," + year)
                .verifyResults(subjects)
                .verifyResults(hobbies)
                .verifyResults(picture)
                .verifyResults(address)
                .verifyResults(state)
                .verifyResults(city);
    }
}
