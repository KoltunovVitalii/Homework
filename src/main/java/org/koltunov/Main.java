package org.koltunov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        Scanner scanner = new Scanner(System.in);
        Player currentUser = null;

        while (true) {
            System.out.println("""
                    1. Регистрация
                    2. Авторизация
                    3. Баланс
                    4. Дебет/Снятие
                    5. Кредит/Пополнение
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
                    transactionService.registerPlayer(regUsername, regPassword);
                    break;
                case 2:
                    if (currentUser == null) {
                        System.out.print("Введите имя игрока: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Введите пароль: ");
                        String loginPassword = scanner.nextLine();
                        currentUser = transactionService.login(loginUsername, loginPassword);
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
                        transactionService.viewTransactionHistory(currentUser);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 7:
                    if (currentUser != null) {
                        transactionService.viewAuditHistory(currentUser);
                    } else {
                        System.out.println("Для выполнения данного действия необходима авторизация");
                    }
                    break;
                case 8:
                    if (currentUser != null) {
                        currentUser.audit("Выход из системы");
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