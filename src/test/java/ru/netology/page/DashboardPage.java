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
        String lastDigits = cardNumber.substring(cardNumber.length() - 4);
        for (SelenideElement card : cards) {
            String text = card.text();
            if (text.contains(lastDigits)) {
                return extractBalance(text);
            }
        }
        throw new IllegalStateException("Карта не найдена: " + cardNumber);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value.replaceAll("\\s", ""));
    }

    public TransferPage selectCardToReplenish(String cardNumber) {
        String lastDigits = cardNumber.substring(cardNumber.length() - 4);
        for (SelenideElement card : cards) {
            if (card.text().contains(lastDigits)) {
                card.$("button").click();
                return new TransferPage();
            }
        }
        throw new IllegalStateException("Карта не найдена: " + cardNumber);
    }


}
