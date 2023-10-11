package org.koltunov;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PlayerTest {

    @Test
    @DisplayName("Авторизация с валидным паролем")
    public void testAuthorizationWithValidPassword() {
        Player player = new Player("testUser", "password123");
        boolean authorized = player.authorization("password123");
        Assertions.assertThat(authorized).isTrue();
    }

    @Test
    @DisplayName("Авторизация с не валидным паролем")
    public void testAuthorizationWithInvalidPassword() {
        Player player = new Player("testUser", "password123");
        boolean authorized = player.authorization("wrongPassword");
        Assertions.assertThat(authorized).isFalse();
    }

    @Test
    @DisplayName("Добавление транзакции в историю")
    public void testAddTransactionHistory() {
        Player player = new Player("testUser", "password123");
        player.addTransactionHistory("Transaction 1");
        Assertions.assertThat(player.getTransactionHistory()).contains("Transaction 1");
    }

    @Test
    @DisplayName("Установка баланса")
    public void testSetBalance() {
        Player player = new Player("testUser", "password123");
        player.setBalance(100.0);
        Assertions.assertThat(player.getBalance()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("Добавление действия в аудит")
    public void testAddAudit() {
        Player player = new Player("testUser", "password123");
        player.addAudit("Action 1");
        Assertions.assertThat(player.getAuditHistory()).contains("Аудит: Action 1");
    }
}