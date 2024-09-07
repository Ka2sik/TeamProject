package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankTest {

    Bank bank = new Bank();
    int initialBalanceAcc1 = 15_000;
    int initialBalanceAcc2 = 1_000;

    Account acc1 = new SavingAccount(initialBalanceAcc1, 0, 60_000, 5);
    Account acc2 = new CreditAccount(initialBalanceAcc2, 50_000, 15);

    @Test
    public void shouldTransferMoneyFromSavingAccToCreditAcc() {
        int amount = 3_000;
        Assertions.assertTrue(bank.transfer(acc1, acc2, amount));
        Assertions.assertEquals(initialBalanceAcc1 - amount, acc1.getBalance());
        Assertions.assertEquals(initialBalanceAcc2 + amount, acc2.getBalance());
    }

    @Test
    public void shouldTransferMoneyFromCreditAccToSavingAcc() {
        int amount = 3_000;
        Assertions.assertTrue(bank.transfer(acc2, acc1, amount));
        Assertions.assertEquals(initialBalanceAcc2 - amount, acc2.getBalance());
        Assertions.assertEquals(initialBalanceAcc1 + amount, acc1.getBalance());
    }

    @Test
    public void shouldNotTransferZeroAmount() {
        int amount = 0;
        Assertions.assertFalse(bank.transfer(acc1, acc2, amount));
        Assertions.assertEquals(initialBalanceAcc1, acc1.getBalance());
        Assertions.assertEquals(initialBalanceAcc2, acc2.getBalance());
    }

    @Test
    public void shouldNotTransferNegativeAmount() {
        int amount = -1_000;
        Assertions.assertFalse(bank.transfer(acc1, acc2, amount));
        Assertions.assertEquals(initialBalanceAcc1, acc1.getBalance());
        Assertions.assertEquals(initialBalanceAcc2, acc2.getBalance());
    }

    @Test
    public void shouldNotTransferMoneyMoreThenBalanceFromSavingAcc() {
        int amount = 20_000;
        Assertions.assertFalse(bank.transfer(acc1, acc2, amount));
        Assertions.assertEquals(initialBalanceAcc1, acc1.getBalance());
        Assertions.assertEquals(initialBalanceAcc2, acc2.getBalance());
    }

    @Test
    public void shouldNotTransferMoneyMoreThenCreditLimitFromCreditAcc() {
        int amount = 55_000;
        Assertions.assertFalse(bank.transfer(acc2, acc1, amount));
        Assertions.assertEquals(initialBalanceAcc1, acc1.getBalance());
        Assertions.assertEquals(initialBalanceAcc2, acc2.getBalance());
    }

    @Test
    public void shouldNotTransferMoneyIfBalanceOfSavingAccWillBeMoreThanMaxLimit() {
        int amount = 50_000;
        Assertions.assertFalse(bank.transfer(acc2, acc1, amount));
        Assertions.assertEquals(initialBalanceAcc1, acc1.getBalance());
        Assertions.assertEquals(initialBalanceAcc2, acc2.getBalance());
    }
}
  
