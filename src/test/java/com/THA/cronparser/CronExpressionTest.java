package com.THA.cronparser;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CronExpressionTest {

    @Test
    public void testPrintCronExpression() {
        String[] cronParts = {
                "*/10",      // minute
                "0",         // hour
                "1-15/3",    // day of month
                "*",         // month
                "2-4",       // day of week
                "/usr/bin/find" // command
        };

        CronExpression cronExpression = new CronExpression(cronParts);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cronExpression.printCronExpression();

        String expectedOutput =
                "minute        0 10 20 30 40 50\n" +
                        "hour          0\n" +
                        "day of month  1 4 7 10 13\n" +
                        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                        "day of week   2 3 4\n" +
                        "command       /usr/bin/find\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void testInvalidCronExpression() {
        String[] cronParts = {
                "*/10",       // minute
                "0",          // hour
                "32",         // invalid day of month
                "*",          // month
                "2-4",        // day of week
                "/usr/bin/find" // command
        };

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronExpression(cronParts);
        });

        String expectedMessage = "Invalid value '32'. Allowed range is 1-31.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyCommand() {
        String[] cronParts = {
                "*/10",       // minute
                "0",          // hour
                "1-15/3",     // day of month
                "*",          // month
                "2-4",        // day of week
                ""            // empty command
        };

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronExpression(cronParts);
        });

        String expectedMessage = "Command field cannot be empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Additional edge case tests
    @Test
    public void testSingleMinute() {
        String[] cronParts = {
                "5",          // single minute
                "0",          // hour
                "*",          // day of month
                "*",          // month
                "1",          // day of week
                "/usr/bin/find" // command
        };

        CronExpression cronExpression = new CronExpression(cronParts);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cronExpression.printCronExpression();

        String expectedOutput =
                "minute        5\n" +
                        "hour          0\n" +
                        "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31\n" +
                        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                        "day of week   1\n" +
                        "command       /usr/bin/find\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());

        System.setOut(System.out);
    }
}
