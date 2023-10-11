package org.koltunov;

import java.util.List;

public class HistoryService {
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
        player.addAudit("Просмотр истории операций");
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
        player.addAudit("Просмотр истории событий");
    }
}
