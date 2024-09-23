package com.THA.cronparser;

public class CronParser {

    public static final int EXPRESSION_SIZE = 6;

    public static void main(String[] args) {
        try {
            CronExpressionValidator validator = new CronExpressionValidator();
            String[] expressionParts = validator.validateAndPreprocessExpression(args);
            CronExpression cronExpression = new CronExpression(expressionParts);
            cronExpression.printCronExpression();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

