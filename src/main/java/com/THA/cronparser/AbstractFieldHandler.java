package main.java.com.THA.cronparser;

import java.util.*;

abstract class AbstractFieldHandler implements FieldHandler {
    protected Set<Integer> parseRange(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] rangeParts = expression.split("-");
        int start = Integer.parseInt(rangeParts[0]);
        int end = Integer.parseInt(rangeParts[1]);

        if (start > end || start < min || end > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid range '%s'. Allowed range is %d-%d.", expression, min, max));
        }

        for (int i = start; i <= end; i++) {
            values.add(i);
        }
        return values;
    }

    protected Set<Integer> parseSingleValue(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        int value = Integer.parseInt(expression);

        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid value '%d'. Allowed range is %d-%d.", value, min, max));
        }
        values.add(value);
        return values;
    }
}
