package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Should transfer money between own cards")
    void shouldTransferBetweenOwnCards() {
        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();
        int amount = 1000;

        int firstBefore = dashboardPage.getCardBalance(firstCard.getNumber());
        int secondBefore = dashboardPage.getCardBalance(secondCard.getNumber());

        var transferPage = dashboardPage.selectCardToReplenish(secondCard.getNumber());
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstCard.getNumber());

        int firstAfter = dashboardPage.getCardBalance(firstCard.getNumber());
        int secondAfter = dashboardPage.getCardBalance(secondCard.getNumber());

        assertEquals(firstBefore - amount, firstAfter);
        assertEquals(secondBefore + amount, secondAfter);
    }


    @Test
    @DisplayName("Should get error if transfer more than balance")
    void shouldGetErrorIfTransferMoreThanBalance() {
        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();
        int amount = 100_000; // больше, чем 10 000

        var transferPage = dashboardPage.selectCardToReplenish(secondCard.getNumber());
        transferPage.makeTransferWithError(String.valueOf(amount), firstCard.getNumber());

        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка!"));
    }


}
