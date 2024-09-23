# Cron Expression Parser - Test Cases

This document outlines the test cases written for the Cron Expression Parser project, including unit tests that validate the correctness of the parsing logic and the handling of various cron expressions.

## Unit Test Files:

### 1. **`CronExpressionTest.java`**
#### Test Cases:
- **`testPrintCronExpression()`**:
    - **Purpose**: Verifies that a cron expression with step, range, and list expansions prints correctly.
    - **Input**: `"*/10 0 1-15/3 * 2-4 /usr/bin/find"`
    - **Expected Output**:
      ```plaintext
      minute        0 10 20 30 40 50
      hour          0
      day of month  1 4 7 10 13
      month         1 2 3 4 5 6 7 8 9 10 11 12
      day of week   2 3 4
      command       /usr/bin/find
      ```

- **`testInvalidCronExpression()`**:
    - **Purpose**: Checks that an invalid day of the month (e.g., 32) throws an `IllegalArgumentException`.
    - **Expected Exception Message**: `"Invalid value '32'. Allowed range is 1-31."`

- **`testEmptyCommand()`**:
    - **Purpose**: Ensures that an empty command throws an `IllegalArgumentException`.
    - **Expected Exception Message**: `"Command field cannot be empty."`

### 2. **`CronFieldHandlerTest.java`**
#### Test Cases:
- **`testHandleWildcardField()`**: Verifies that the wildcard (`*`) expands to all possible values within the range.
- **`testHandleStepField()`**: Validates that step expressions like `*/10` expand correctly.
- **`testHandleRangeField()`**: Ensures that range expressions like `10-20` expand to include all values in the range.
- **`testHandleListField()`**: Checks that list expressions like `5,10,15` expand correctly.
- **`testHandleDayNames()`**: Tests that day name abbreviations (e.g., `MON-FRI`) are parsed and expanded properly.
- **`testHandleInvalidRange()`**: Verifies that an invalid range (e.g., `30-10`) throws an `IllegalArgumentException`.
- **`testHandleInvalidStep()`**: Ensures that invalid step sizes (e.g., `*/100`) throw an `IllegalArgumentException`.

### 3. **`CronParserTest.java`**
#### Test Cases:
- **`testValidCronExpression()`**:
    - **Purpose**: Ensures that the parser correctly parses and expands a valid cron expression.
    - **Input**: `"*/10 0 1-15/3 * 2-4 /usr/bin/find"`
    - **Expected Output**:
      ```plaintext
      minute        0 10 20 30 40 50
      hour          0
      day of month  1 4 7 10 13
      month         1 2 3 4 5 6 7 8 9 10 11 12
      day of week   2 3 4
      command       /usr/bin/find
      ```

- **`testInvalidCronExpressionFieldCount()`**: Verifies that missing cron expression fields throw an `IllegalArgumentException`.

- **`testEmptyCommand()`**: Ensures that an empty command throws an exception.