package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        String number;
        String balance;
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001", "10 000");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002", "10 000");
    }


}
