package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement code = $("[data-test-id='code'] input");
    private final SelenideElement button = $("[data-test-id='action-verify'] .button__text");

    public DashboardPage validVerify(DataHelper.VerificationCode code) {
        this.code.setValue(code.getCode());
        button.click();
        return new DashboardPage();
    }


}
