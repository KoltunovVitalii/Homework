package org.koltunov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;


import java.util.HashMap;
import java.util.Map;

public class AuthorizationServiceTest {

    private AuthorizationService authorizationService;
    private Map<String, Player> players;

    @BeforeEach
    public void setUp() {
        players = new HashMap<>();
        authorizationService = new AuthorizationService(players);
    }

    @Test
    @DisplayName("Регистрация нового игрока")
    public void testRegisterPlayer() {
        String username = "testUser";
        String password = "password123";

        authorizationService.registerPlayer(username, password);

        Assertions.assertThat(players).containsKey(username);
        Player player = players.get(username);
        Assertions.assertThat(player.getUsername()).isEqualTo(username);
        Assertions.assertThat(player.authorization(password)).isTrue();
        Assertions.assertThat(player.getAuditHistory()).contains("Аудит: Регистрация");
        Assertions.assertThat(player.getBalance()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Регистрация существующего игрока")
    public void testRegisterExistingPlayer() {
        String username = "testUser";
        String password = "password123";

        authorizationService.registerPlayer(username, password);

        authorizationService.registerPlayer(username, "newPassword");

        Player player = players.get(username);
        Assertions.assertThat(player.getPassword()).isEqualTo("password123");
    }

    @Test
    @DisplayName("Вход с верным логином и паролем")
    public void testLoginWithCorrectCredentials() {
        String username = "testUser";
        String password = "password123";
        authorizationService.registerPlayer(username, password);

        Player player = authorizationService.login(username, password);

        Assertions.assertThat(player).isNotNull();
        Assertions.assertThat(player.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("Вход с неверным паролем")
    public void testLoginWithIncorrectPassword() {
        String username = "testUser";
        String password = "password123";
        authorizationService.registerPlayer(username, password);

        Player player = authorizationService.login(username, "wrongPassword");

        Assertions.assertThat(player).isNull();
    }

    @Test
    @DisplayName("Вход с несуществующим именем пользователя")
    public void testLoginWithNonExistentUsername() {
        String username = "testUser";
        String password = "password123";

        Player player = authorizationService.login(username, password);

        Assertions.assertThat(player).isNull();
    }
}
