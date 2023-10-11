package org.koltunov;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    /**
     * Создает экземпляр TransactionService и обрабатывает пользовательские действия через консольный интерфейс.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        HistoryService historyService = new HistoryService();
        AuthorizationService authorizationService = new AuthorizationService(new HashMap<>());
        Scanner scanner = new Scanner(System.in);
        Player currentUser = null;

        while (true) {
            System.out.println("""
                    Выберите действие:
                    1. Регистрация
                    2. Авторизация
                    3. Баланс
                    4. Снятие
                    5. Пополнение
                    6. История операций
                    7. История действий
                    8. Выход из системы
                    9. Закрыть программу""");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя игрока: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String regPassword = scanner.nextLine();
                    authorizationService.registerPlayer(regUsername, regPassword);
                    break;
                case 2:
                    if (currentUser == null) {
                        System.out.print("Введите имя игрока: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Введите пароль: ");
                        String loginPassword = scanner.nextLine();
                        currentUser = authorizationService.login(loginUsername, loginPassword);
                    } else {
                        System.out.println("Выйдите из системы чтобы авторизоваться другим игроком");
                    }
                    break;
                case 3:
                    if (currentUser != null) {
                        transactionService.viewBalance(currentUser);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        System.out.print("Введите сумму снятия: ");
                        double debitAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Введите идентификатор операции: ");
                        String debitTransactionId = scanner.nextLine();
                        transactionService.debit(currentUser, debitAmount, debitTransactionId);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 5:
                    if (currentUser != null) {
                        System.out.print("Введите сумму пополнения: ");
                        double creditAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Введите идентификатор операции: ");
                        String creditTransactionId = scanner.nextLine();
                        transactionService.credit(currentUser, creditAmount, creditTransactionId);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 6:
                    if (currentUser != null) {
                        historyService.viewTransactionHistory(currentUser);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 7:
                    if (currentUser != null) {
                        historyService.viewAuditHistory(currentUser);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 8:
                    if (currentUser != null) {
                        currentUser.addAudit("Выход из системы");
                        currentUser = null;
                        System.out.println("Выход из системы выполнен успешно");
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Не верный выбор. Попробуйте еще раз");
                    break;
            }
        }
    }
}