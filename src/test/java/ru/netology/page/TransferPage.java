package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amount = $("[data-test-id='amount'] input");
    private final SelenideElement from = $("[data-test-id='from'] input");
    private final SelenideElement button = $("[data-test-id='action-transfer'] .button__text");
    private final SelenideElement heading = $("h1");

    public TransferPage() {
        heading.shouldBe(visible).shouldHave(com.codeborne.selenide.Condition.text("Пополнение карты"));
    }

    public DashboardPage makeTransfer(String sum, String fromCard) {
        amount.setValue(sum);
        from.setValue(fromCard);
        button.click();
        return new DashboardPage();
    }


    public TransferPage makeTransferWithError(String sum, String fromCard) {
        amount.setValue(sum);
        from.setValue(fromCard);
        button.click();
        return this;
    }
}
