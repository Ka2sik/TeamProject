package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SavingAccountTest {

    @ParameterizedTest
    @CsvFileSource(resources = "Saving_Account_Correct.csv")
    public void shouldCreateSavingAccountWithCorrectValues(int initialBalance, int minBalance, int maxBalance, int rate) {
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(minBalance, account.getMinBalance());
        Assertions.assertEquals(maxBalance, account.getMaxBalance());
        Assertions.assertEquals(rate, account.getRate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "Saving_Account_Incorrect.csv")
    public void shouldNotCreateSavingAccountWithIncorrectValues(int initialBalance, int minBalance, int maxBalance, int rate) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }

    @Test
    public void shouldPayBalanceBetweenMinAndMax() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.pay(500);

        Assertions.assertEquals(2_000 - 500, account.getBalance());
    }

    @Test
    public void shouldPayBalanceEqualMin() {
        SavingAccount account = new SavingAccount(2_000, 500, 10_000, 5);

        Assertions.assertTrue(account.pay(1500));
        Assertions.assertEquals(2_000 - 1_500, account.getBalance());
    }

    @Test
    public void shouldPayAmountEqualBalance() {
        SavingAccount account = new SavingAccount(2_000, 0, 10_000, 5);

        Assertions.assertTrue(account.pay(2000));
        Assertions.assertEquals(2_000 - 2_000, account.getBalance());
    }

    @Test
    public void shouldNotPayBalanceLessThanMinBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(1500));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotPayBalanceLessThanPayment() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(3000));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotPayNegativeAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(-100));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotPayZeroAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertTrue(account.add(3_000));
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddAmountToMakeEqualToMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertTrue(account.add(8_000));
        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddAmountMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(10_000));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddNegativeAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(-100));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddZeroAmount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldCalculateAnnualIncome() {
        SavingAccount account = new SavingAccount(15_520, 1_000, 100_000, 12);

        Assertions.assertEquals(1_862, account.yearChange());
    }

}
