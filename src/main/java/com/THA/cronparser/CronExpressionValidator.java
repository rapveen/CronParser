package com.THA.cronparser;

public class CronExpressionValidator {

    public String[] validateAndPreprocessExpression(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: java CronParser \"<cron_expression>\"");
        }

        String expression = args[0];
        String[] expressionParts = expression.split(" ", CronParser.EXPRESSION_SIZE);

        if (expressionParts.length != CronParser.EXPRESSION_SIZE) {
            throw new IllegalArgumentException("Invalid cron expression. Expected 5 fields and a command.");
        }

        return expressionParts;
    }
}

