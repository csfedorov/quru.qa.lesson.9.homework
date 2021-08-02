package com.demoqa.tests.registration;

import com.demoqa.pages.registration.RegistrationPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RegistrationTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

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

        step("Open students registration form", () -> registrationPage.openPage());

        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                registrationPage.typeFirstName(firstName)
                        .typeLastName(lastName)
                        .typeEmail(email)
                        .selectGender(gender)
                        .typePhoneNumber(phone);
            });
            step("Set date", () -> registrationPage.setDate(day, month, year));
            step("Set subjects", () -> registrationPage.typeSubjects(subjects));
            step("Set hobbies", () -> registrationPage.typeHobbies(hobbies));
            step("Upload image", () -> registrationPage.uploadPicture(picture));
            step("Set address", () -> {
                registrationPage.typeAddress(address)
                        .selectState(state)
                        .selectCity(city);
            });
            step("Submit form", () -> registrationPage.clickSubmitButton());

            step("Verify successful form submit", () ->
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
                            .verifyResults(city));
        });
    }
}

