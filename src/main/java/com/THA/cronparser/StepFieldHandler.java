package com.THA.cronparser;

import java.util.*;

public class StepFieldHandler extends AbstractFieldHandler {
    @Override
    public Set<Integer> parse(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] parts = expression.split("/");

        if (parts.length > 2) {
            throw new IllegalArgumentException(
                    String.format("Invalid expression '%s'. Only one '/' is allowed for a range.", expression));
        }

        int step = Integer.parseInt(parts[1]);

        if (step <= 0 || step > max - min + 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid step '%d'. Step value should be within the range %d-%d.", step, min, max));
        }

        String base = parts[0];
        Set<Integer> baseValues;

        if (base.equals("*")) {
            baseValues = new WildcardFieldHandler().parse(base, min, max);
        } else if (base.contains("-")) {
            baseValues = new RangeFieldHandler().parse(base, min, max);
        } else {
            throw new IllegalArgumentException(
                    String.format("Invalid expression '%s'. Argument for step should either be a wildcard or a range.", expression));
        }

        for (int value : baseValues) {
            if ((value - min) % step == 0) {
                values.add(value);
            }
        }

        return values;
    }
}

