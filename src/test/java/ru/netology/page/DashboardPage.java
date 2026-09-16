package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final SelenideElement heading = $("[data-test-id='dashboard']");
    private final ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible).shouldHave(Condition.text("Личный кабинет"));
    }

    public int getCardBalance(String cardNumber) {
        return extractBalance(findCardByLastDigits(cardNumber).text());
    }

    public TransferPage selectCardToReplenish(String cardNumber) {
        findCardByLastDigits(cardNumber).$("button").click();
        return new TransferPage();
    }

    private SelenideElement findCardByLastDigits(String cardNumber) {
        String lastDigits = cardNumber.substring(cardNumber.length() - 4);
        return cards.findBy(Condition.text(lastDigits));
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value.replaceAll("\\s", ""));
    }
}
