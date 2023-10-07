package org.koltunov;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionService {
    Map<String, Player> players;

    public TransactionService() {
        this.players = new HashMap<>();
    }

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

    public Player login(String username, String password) {
        Player player = players.get(username);
        if (player != null && player.authenticate(password)) {
            player.audit("Авторизация");
            System.out.println("Авторизация игрока " + username + " выполнена успешно");
            return player;
        }
        System.out.println("Не верное имя игрока или пароль");
        return null;
    }

    public void viewBalance(Player player) {
        System.out.println("Текущий баланс игрока " + player.getUsername() + " " + player.getBalance() + " руб.");
        player.audit("Просмотр баланса");
    }
    public boolean debit(Player player, double amount, String transactionId) {
        boolean success = player.debit(amount, transactionId);
        if (success) {
            System.out.println("Дебет/Списание на " + amount + " руб. произведено успешно");
//            viewBalance(player);
        } else {
            System.out.println("Дебет/Списание не произведено. " +
                    "Недостаточный баланс или повторяющийся идентификатор транзакции.");
            player.audit("Отказ в списании " + amount + " руб.");
        }
        return success;
    }

    public boolean credit(Player player, double amount, String transactionId) {
        boolean success = player.credit(amount, transactionId);
        if (success) {
            System.out.println("Кредит/Пополнение на " + amount + " руб. произведено успешно");
//            viewBalance(player);
        } else {
            System.out.println("Кредит/Пополнение не произведено. " +
                    "Повторяющийся идентификатор транзакции.");
            player.audit("Отказ в пополнении " + amount + " руб.");
        }
        return success;
    }

    public void viewTransactionHistory(Player player) {
        List<String> history = player.getTransactionHistory();
        System.out.println("История операций для игрока " + player.getUsername() + ":");
        for (String entry : history) {
            System.out.println(entry);
        }
        player.audit("Просмотр истории операций");
    }

    public void viewAuditHistory(Player player) {
        List<String> history = player.getAuditHistory();
        System.out.println("История событий для игрока " + player.getUsername() + ":");
        for (String entry : history) {
            System.out.println(entry);
        }
        player.audit("Просмотр истории событий");
    }
}
