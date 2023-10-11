package org.koltunov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class HistoryServiceTest {

    private Player player;
    private HistoryService historyService;

    @BeforeEach
    public void setUp() {
        player = new Player("testUser", "password123");
        historyService = new HistoryService();
    }

    @Test
    @DisplayName("Отображение истории транзакций")
    public void testViewTransactionHistory() {
        List<String> transactionHistory = new ArrayList<>();
        transactionHistory.add("Transaction 1");
        transactionHistory.add("Transaction 2");
        player.setTransactionHistory(transactionHistory);

        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        historyService.viewTransactionHistory(player);

        String expectedOutput = "История операций для игрока testUser:\nTransaction 1\nTransaction 2\n";
        Assertions.assertThat(consoleOutput.toString()).isEqualTo(expectedOutput);

        System.setOut(System.out);
    }

    @Test
    @DisplayName("Отображение истории действий")
    public void testViewAuditHistory() {
        List<String> auditHistory = new ArrayList<>();
        auditHistory.add("Audit 1");
        auditHistory.add("Audit 2");
        player.setAuditHistory(auditHistory);

        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        historyService.viewAuditHistory(player);

        String expectedOutput = "История событий для игрока testUser:\nAudit 1\nAudit 2\n";
        Assertions.assertThat(consoleOutput.toString()).isEqualTo(expectedOutput);

        System.setOut(System.out);
    }
}
