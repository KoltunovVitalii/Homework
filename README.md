# Homework


# Класс Player
Класс `Player` представляет игрока и управляет его данными, включая баланс, историю транзакций и аудит действий.
## Описание
Этот класс предоставляет методы для работы с игровым аккаунтом игрока, такие как:
- Аутентификация игрока по паролю.
- Дебет и кредит на балансе игрока.
- Просмотр текущего баланса и истории операций.
- Просмотр истории действий
## Методы
- `authenticate(String password)`: Проверяет аутентификацию игрока по паролю.
- `debit(double amount, String transactionId)`: Выполняет дебет/списание средств с баланса игрока.
- `credit(double amount, String transactionId)`: Выполняет кредит/пополнение баланса игрока.
- `viewBalance()`: Возвращает текущий баланс игрока.
- `getTransactionHistory()`: Возвращает историю операций игрока.
- `getAuditHistory()`: Возвращает историю аудита действий игрока.

# Класс TransactionService
Класс `TransactionService` представляет сервис для управления транзакциями и игроками .
## Описание
Этот класс предоставляет методы для выполнения следующих действий:
- Регистрация новых игроков.
- Авторизация игроков.
- Просмотр баланса и истории операций игроков.
- Просмотр истории действий игроков (аудит)
- Выполнение дебетовых и кредитных операций.
## Методы
- `registerPlayer(String username, String password)`: Регистрирует нового игрока.
- `login(String username, String password)`: Аутентифицирует игрока по имени пользователя и паролю.
- `viewBalance(Player player)`: Просматривает текущий баланс игрока.
- `debit(Player player, double amount, String transactionId)`: Выполняет дебет/списание средств с баланса игрока.
- `credit(Player player, double amount, String transactionId)`: Выполняет кредит/пополнение баланса игрока.
- `viewTransactionHistory(Player player)`: Просматривает историю операций игрока.
- `viewAuditHistory(Player player)`: Просматривает историю аудита действий игрока.