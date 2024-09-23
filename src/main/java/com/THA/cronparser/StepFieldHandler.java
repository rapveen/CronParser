package com.THA.cronparser;

import java.util.*;

public class StepFieldHandler extends AbstractFieldHandler {

    private final WildcardFieldHandler wildcardFieldHandler;
    private final RangeFieldHandler rangeFieldHandler;

    public StepFieldHandler(WildcardFieldHandler wildcardFieldHandler, RangeFieldHandler rangeFieldHandler) {
        this.wildcardFieldHandler = wildcardFieldHandler;
        this.rangeFieldHandler = rangeFieldHandler;
    }

    @Override
    public Set<Integer> parse(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] parts = expression.split("/");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid step expression: " + expression);
        }

        int step = Integer.parseInt(parts[1]);

        if (step <= 0 || step > (max - min + 1)) {
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
            baseValues = new SingleValueFieldHandler().parse(base, min, max);
        }

        for (int value : baseValues) {
            if ((value - min) % step == 0) {
                values.add(value);
            }
        }

        return values;
    }
}
