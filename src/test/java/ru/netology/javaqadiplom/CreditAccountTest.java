package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {              /// Позитивный тест(пополение баланса суммой >0)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
    @Test
    public void shouldAddToPositiveBalanceMoreNull() {              /// Позитивный тест(пополение суммы при любом балансе кроме 0)
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void addToNegativeBalance() {                    /// Негативный тест(пополение баланса суммой <0)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());

    }

    @Test
    public void NegativeInitialBalance() {                                 /////initialBalance - неотрицательное число, начальный баланс для счёта
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-3_000, 5_000, 15);
        });
    }

    @Test
    public void NegativeСreditLimit() {                                   /////creditLimit - неотрицательное число, максимальная сумма которую можно задолжать банку
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, -5_000, 15);
        });
    }

    @Test
    public void NegativeRate() {                                         /////rate - неотрицательное число, ставка кредитования для расчёта долга за отрицательный баланс
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, 5_000, -15);
        });

    }

    //////////////////
    @Test
    public void PositivePayTest() {           /// Покупа остаток >0
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(2_900);

        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void ZeroPayTest() {           /// Покупа при которой баланс =0
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void DownCreditLimitPayTest() {           /// Остаток <0 (в кредитный лимит)
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(4_000);

        Assertions.assertEquals(-1_000, account.getBalance());
    }

    @Test
    public void UpCreditLimitPayTest() {           /// Покупа за кредитный лимит
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(10_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    ////////////////////
    @Test
    public void PositiveCalculateRateYearChange() {           /// Расчёт процентов на положительный баланс
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void CalculateZeroYearChange() {           /// Расчёт процентов на  баланс =0
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void CalculateRateYearChange() {           /// Расчёт процентов на отрицательный баланс но в кр лимите
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(5_000);

        Assertions.assertEquals(-300, account.yearChange());
    }

    @Test
    public void UpCalculateRateYearChange() {           /// Расчёт процентов на отрицательный баланс за кр лимитом
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(10_000);

        Assertions.assertEquals(0, account.yearChange());
    }


}
