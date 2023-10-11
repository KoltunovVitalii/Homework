package org.koltunov;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс, представляющий игрока.
 * Класс управляет данными игрока, включая его баланс, историю операций и аудит действий.
 */

public class Player {
    private String username;
    private String password;
    private double balance;
    private List<String> transactionHistory;
    private List<String> auditHistory;
    private Set<String> transactionIds;

    /**
     * Конструктор класса Player.
     *
     * @param username имя игрока
     * @param password пароль игрока
     */
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        this.auditHistory = new ArrayList<>();
        this.transactionIds = new HashSet<>();
    }

    /**
     * Устанавливает текущий баланс игрока.
     *
     * @param balance новый баланс игрока
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Устанавливает историю транзакций игрока.
     *
     * @param transactionHistory новая история транзакций
     */
    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    /**
     * Устанавливает историю аудита действий игрока.
     *
     * @param auditHistory новая история аудита
     */
    public void setAuditHistory(List<String> auditHistory) {
        this.auditHistory = auditHistory;
    }

    /**
     * Добавляет запись в историю транзакций игрока.
     *
     * @param tH запись о транзакции
     */
    public void addTransactionHistory(String tH) {
        transactionHistory.add(tH);
    }

    /**
     * Добавляет уникальный идентификатор транзакции в множество транзакций игрока.
     *
     * @param tIds уникальный идентификатор транзакции
     */
    public void addTransactionIds(String tIds) {
        transactionIds.add(tIds);
    }

    /**
     * Возвращает множество уникальных идентификаторов транзакций игрока.
     *
     * @return множество уникальных идентификаторов транзакций
     */
    public Set<String> getTransactionIds() {
        return transactionIds;
    }

    /**
     * Возвращает имя игрока.
     *
     * @return имя игрока
     */
    public String getUsername() {
        return username;
    }

    /**
     * Возвращает текущий пароль игрока.
     *
     * @return текущий пароль игрока
     */

    public String getPassword() {
        return password;
    }

    /**
     * Возвращает текущий баланс игрока.
     *
     * @return текущий баланс игрока
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Возвращает историю операций игрока.
     *
     * @return список строк с историей операций
     */
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    /**
     * Возвращает историю действий (аудит) игрока.
     *
     * @return список строк с историей действий
     */
    public List<String> getAuditHistory() {
        return auditHistory;
    }

    /**
     * Проверяет авторизацию игрока по паролю.
     *
     * @param password пароль для авторизации
     * @return true, если авторизация успешна; в противном случае - false
     */
    public boolean authorization(String password) {
        return this.password.equals(password);
    }

    /**
     * Добавляет запись об аудите действия игрока.
     *
     * @param action действие, для которого записывается аудит
     */
    public void addAudit(String action) {
        auditHistory.add("Аудит: " + action);
    }

}
