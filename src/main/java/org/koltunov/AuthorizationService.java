package org.koltunov;

import java.util.Map;

public class AuthorizationService {
    Map<String, Player> players;

    /**
     * Конструктор класса TransactionService.
     * Инициализирует пустой список игроков.
     */
    public AuthorizationService(Map<String, Player> players) {
        this.players = players;
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
            player.addAudit("Регистрация");
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
            player.addAudit("Авторизация");
            System.out.println("Авторизация игрока " + username + " выполнена успешно");
            return player;
        }
        System.out.println("Не верное имя игрока или пароль");
        return null;
    }
}
