package org.koltunov;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player testPlayer;

    @Before
    public void setUp() {
        // Инициализация объекта testPlayer перед каждым тестом
        testPlayer = new Player("testUser", "password");
    }

    @Test
    public void testAuthenticate() {
        // Тест на успешную аутентификацию
        assertTrue(testPlayer.authenticate("password"));

        // Тест на неуспешную аутентификацию с неправильным паролем
        assertFalse(testPlayer.authenticate("wrongPassword"));
    }

    @Test
    public void testDebit() {
        testPlayer.credit(10, "id");
        // Тест на успешное списание с достаточным балансом
        assertTrue(testPlayer.debit(10.0, "debit1"));

        // Тест на неуспешное списание с недостаточным балансом
        assertFalse(testPlayer.debit(20.0, "debit2"));
    }

    @Test
    public void testCredit() {
        // Тест на успешное пополнение с положительной суммой
        assertTrue(testPlayer.credit(50.0, "credit1"));

        // Тест на неуспешное пополнение с отрицательной суммой (не должно сработать)
        assertFalse(testPlayer.credit(-10.0, "credit2"));
    }

    @Test
    public void testGetBalance() {
        // Проверка начального баланса (должен быть равен 0)
        assertEquals(0.0, testPlayer.getBalance(), 0.001);
    }

    @Test
    public void testGetUsername() {
        // Проверка имени пользователя
        assertEquals("testUser", testPlayer.getUsername());
    }
}
