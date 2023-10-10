package org.koltunov;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, представляющий сервис для управления транзакциями и игроками.
 * Этот класс обеспечивает регистрацию игроков, аутентификацию, просмотр баланса,
 * а также выполнение дебетовых и кредитных операций.
 */

public class TransactionService {
    Map<String, Player> players;

    /**
     * Конструктор класса TransactionService.
     * Инициализирует пустой список игроков.
     */
    public TransactionService() {
        this.players = new HashMap<>();
    }

    /**
     * Регистрирует нового игрока с указанным именем и паролем.
     * Если игрок с таким именем уже существует, выводит сообщение об ошибке.
     *
     * @param username имя игрока
     * @param password пароль игрока
     */
    public void registerPlayer(String username, String password) {
        if (!players.containsKey(username)) {
            Player player = new Player(username, password);
            players.put(username, player);
            player.audit("Регистрация");
            System.out.println("Игрок " + username + " успешно зарегистрирован");
        } else {
            System.out.println("Игрок " + username + " уже зарегистрирован");
        }
    }

    /**
     * Авторизует игрока по имени пользователя и паролю.
     *
     * @param username имя игрока
     * @param password пароль игрока
     * @return объект игрока, если авторизация успешна; в противном случае - null
     */
    public Player login(String username, String password) {
        Player player = players.get(username);
        if (player != null && player.authorization(password)) {
            player.audit("Авторизация");
            System.out.println("Авторизация игрока " + username + " выполнена успешно");
            return player;
        }
        System.out.println("Не верное имя игрока или пароль");
        return null;
    }

    /**
     * Выводит текущий баланс игрока в консоль.
     *
     * @param player объект игрока
     */
    public void viewBalance(Player player) {
        System.out.println("Текущий баланс игрока " + player.getUsername() + " " + player.getBalance() + " руб.");
        player.audit("Просмотр баланса");
    }

    /**
     * Выполняет дебет/списание средств с баланса игрока.
     *
     * @param player объект игрока
     * @param amount сумма для списания
     * @param transactionId уникальный идентификатор транзакции
     * @return true, если списание успешно; в противном случае - false
     */
    public boolean debit(Player player, double amount, String transactionId) {
        boolean success = player.debit(amount, transactionId);
        if (success) {
            System.out.println("Дебет/Списание на " + amount + " руб. произведено успешно");
        } else {
            System.out.println("Дебет/Списание не произведено. " +
                    "Недостаточный баланс, повторяющийся идентификатор транзакции или не верная сумма.");
            player.audit("Отказ в списании " + amount + " руб.");
        }
        return success;
    }

    /**
     * Выполняет кредит/пополнение баланса игрока.
     *
     * @param player объект игрока
     * @param amount сумма для пополнения
     * @param transactionId уникальный идентификатор транзакции
     * @return true, если пополнение успешно; в противном случае - false
     */
    public boolean credit(Player player, double amount, String transactionId) {
        boolean success = player.credit(amount, transactionId);
        if (success) {
            System.out.println("Кредит/Пополнение на " + amount + " руб. произведено успешно");
//            viewBalance(player);
        } else {
            System.out.println("Кредит/Пополнение не произведено. " +
                    "Повторяющийся идентификатор транзакции или не верная сумма.");
            player.audit("Отказ в пополнении " + amount + " руб.");
        }
        return success;
    }

    /**
     * Выводит историю транзакций игрока в консоль.
     *
     * @param player объект игрока
     */
    public void viewTransactionHistory(Player player) {
        List<String> history = player.getTransactionHistory();
        System.out.println("История операций для игрока " + player.getUsername() + ":");
        for (String entry : history) {
            System.out.println(entry);
        }
        player.audit("Просмотр истории операций");
    }

    /**
     * Выводит историю аудита событий игрока в консоль.
     *
     * @param player объект игрока
     */
    public void viewAuditHistory(Player player) {
        List<String> history = player.getAuditHistory();
        System.out.println("История событий для игрока " + player.getUsername() + ":");
        for (String entry : history) {
            System.out.println(entry);
        }
        player.audit("Просмотр истории событий");
    }
}
