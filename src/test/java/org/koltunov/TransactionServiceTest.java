package org.koltunov;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TransactionServiceTest {

    @Test
    @DisplayName("Дебет при достаточном балансе")
    public void testDebitWithSufficientBalance() {
        Player player = new Player("testUser", "password123");
        player.setBalance(100.0);
        String transactionId = "transaction123";

        TransactionService service = new TransactionService();
        service.debit(player, 50.0, transactionId);

        Assertions.assertThat(player.getBalance()).isEqualTo(50.0);
        Assertions.assertThat(player.getTransactionHistory()).contains("Дебет/снятие: 50.0 руб.");
        Assertions.assertThat(player.getTransactionIds()).contains(transactionId);
    }

    @Test
    @DisplayName("Дебет при не достаточном балансе")
    public void testDebitWithInsufficientBalance() {
        Player player = new Player("testUser", "password123");
        player.setBalance(30.0);
        String transactionId = "transaction123";

        TransactionService service = new TransactionService();
        service.debit(player, 50.0, transactionId);

        Assertions.assertThat(player.getBalance()).isEqualTo(30.0);
        Assertions.assertThat(player.getTransactionHistory()).doesNotContain("Дебет/снятие: 50.0 руб.");
        Assertions.assertThat(player.getTransactionIds()).doesNotContain(transactionId);
    }

    @Test
    @DisplayName("Дебет отрицательной суммы")
    public void testDebitWithNonPositiveAmount() {
        Player player = new Player("testUser", "password123");
        player.setBalance(100.0);
        String transactionId = "transaction123";

        TransactionService service = new TransactionService();
        service.debit(player, -50.0, transactionId);

        Assertions.assertThat(player.getBalance()).isEqualTo(100.0);
        Assertions.assertThat(player.getTransactionHistory()).doesNotContain("Дебет/снятие: -50.0 руб.");
        Assertions.assertThat(player.getTransactionIds()).doesNotContain(transactionId);
    }

    @Test
    @DisplayName("Проверка кредита")
    public void testCredit() {
        Player player = new Player("testUser", "password123");
        player.setBalance(100.0);
        String transactionId = "transaction123";

        TransactionService service = new TransactionService();
        service.credit(player, 50.0, transactionId);

        Assertions.assertThat(player.getBalance()).isEqualTo(150.0);
        Assertions.assertThat(player.getTransactionHistory()).contains("Кредит/пополнение: 50.0 руб.");
        Assertions.assertThat(player.getTransactionIds()).contains(transactionId);
    }

    @Test
    @DisplayName("Кредит отрицательной суммы")
    public void testCreditWithNonPositiveAmount() {
        Player player = new Player("testUser", "password123");
        player.setBalance(100.0);
        String transactionId = "transaction123";

        TransactionService service = new TransactionService();
        service.credit(player, -50.0, transactionId);

        Assertions.assertThat(player.getBalance()).isEqualTo(100.0);
        Assertions.assertThat(player.getTransactionHistory()).doesNotContain("Кредит/пополнение: -50.0 рub.");
        Assertions.assertThat(player.getTransactionIds()).doesNotContain(transactionId);
    }
}