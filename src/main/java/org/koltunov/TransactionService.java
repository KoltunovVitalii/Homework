package org.koltunov;

/**
 * Класс, представляющий сервис для управления транзакциями и игроками.
 * Этот класс обеспечивает регистрацию игроков, аутентификацию, просмотр баланса,
 * а также выполнение дебетовых и кредитных операций.
 */

public class TransactionService {
    /**
     * Выводит текущий баланс игрока в консоль.
     *
     * @param player объект игрока
     */
    public void viewBalance(Player player) {
        System.out.println("Текущий баланс игрока " + player.getUsername() + " " + player.getBalance() + " руб.");
        player.addAudit("Просмотр баланса");
    }

    /**
     * Выполняет дебет/списание средств с баланса игрока.
     *
     * @param player        объект игрока
     * @param amount        сумма для списания
     * @param transactionId уникальный идентификатор транзакции
     */
    public void debit(Player player, double amount, String transactionId) {
        if (player.getBalance() - amount >= 0 && !player.getTransactionIds().contains(transactionId) && amount > 0) {
            player.setBalance((player.getBalance() - amount));
            player.addTransactionHistory("Дебет/снятие: " + amount + " руб.");
            player.addTransactionIds(transactionId);
            System.out.println("Дебет/Списание на " + amount + " руб. произведено успешно");
        } else {
            System.out.println("Дебет/Списание не произведено. " +
                    "Недостаточный баланс, повторяющийся идентификатор транзакции или не верная сумма.");
            player.addAudit("Отказ в списании " + amount + " руб.");
        }
    }

    /**
     * Выполняет кредит/пополнение баланса игрока.
     *
     * @param player        объект игрока
     * @param amount        сумма для пополнения
     * @param transactionId уникальный идентификатор транзакции
     */
    public void credit(Player player, double amount, String transactionId) {
        if (!player.getTransactionIds().contains(transactionId) && amount > 0) {
            player.setBalance(player.getBalance() + amount);
            player.addTransactionHistory("Кредит/пополнение: " + amount + " руб.");
            player.addTransactionIds(transactionId);
            System.out.println("Кредит/Пополнение на " + amount + " руб. произведено успешно");
        } else {
            System.out.println("Кредит/Пополнение не произведено. " +
                    "Повторяющийся идентификатор транзакции или не верная сумма.");
            player.addAudit("Отказ в пополнении " + amount + " руб.");
        }
    }
}
