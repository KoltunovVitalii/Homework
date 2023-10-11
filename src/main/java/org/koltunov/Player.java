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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void setAuditHistory(List<String> auditHistory) {
        this.auditHistory = auditHistory;
    }

    public void addTransactionHistory(String tH) {
        transactionHistory.add(tH);
    }

    public void addTransactionIds(String tIds) {
        transactionIds.add(tIds);
    }

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
