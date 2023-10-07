package org.koltunov;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionServiceTest {
    private TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    public void testRegisterPlayer() {
        transactionService.registerPlayer("testUser", "password");
        // Проверяем, что игрок был успешно зарегистрирован
        assertNotNull(transactionService.players.get("testUser"));

        // Попробуем зарегистрировать игрока с тем же именем
        transactionService.registerPlayer("testUser", "password2");
        // Проверяем, что игрок не был зарегистрирован повторно
        assertEquals(1, transactionService.players.size());
    }

    @Test
    public void testLogin() {
        transactionService.registerPlayer("testUser", "password");

        // Попытка входа с правильными учетными данными
        Player player = transactionService.login("testUser", "password");
        // Проверяем, что вход выполнен успешно
        assertNotNull(player);

        // Попытка входа с неправильным паролем
        player = transactionService.login("testUser", "wrongPassword");
        // Проверяем, что вход не выполнен
        assertNull(player);

        // Попытка входа с несуществующим пользователем
        player = transactionService.login("nonExistentUser", "password");
        // Проверяем, что вход не выполнен
        assertNull(player);
    }

    @Test
    public void testDebit() {
        transactionService.registerPlayer("testUser", "password");
        Player player = transactionService.login("testUser", "password");
        player.credit(10, "credit");

        // Попытка списания с достаточным балансом
        boolean success = transactionService.debit(player, 10.0, "debit1");
        assertTrue(success);

        // Попытка списания с недостаточным балансом
        success = transactionService.debit(player, 20.0, "debit2");
        assertFalse(success);
    }

    @Test
    public void testCredit() {
        transactionService.registerPlayer("testUser", "password");
        Player player = transactionService.login("testUser", "password");

        // Попытка пополнения с положительной суммой
        boolean success = transactionService.credit(player, 50.0, "credit1");
        assertTrue(success);

        // Попытка пополнения с отрицательной суммой (не должно сработать)
        success = transactionService.credit(player, -10.0, "credit2");
        assertFalse(success);
    }

    @Test
    public void testViewTransactionHistory() {
        transactionService.registerPlayer("testUser", "password");
        Player player = transactionService.login("testUser", "password");

        // Просмотр истории операций (должен быть хотя бы один элемент)
        transactionService.debit(player, 10.0, "debit1");
        transactionService.viewTransactionHistory(player);
    }

    @Test
    public void testViewAuditHistory() {
        transactionService.registerPlayer("testUser", "password");
        Player player = transactionService.login("testUser", "password");

        // Просмотр истории событий (должен быть хотя бы один элемент)
        transactionService.viewAuditHistory(player);
    }
}