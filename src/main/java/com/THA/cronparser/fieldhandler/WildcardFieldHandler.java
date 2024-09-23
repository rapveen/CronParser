package com.THA.cronparser.fieldhandler;

import java.util.*;

public class WildcardFieldHandler extends AbstractFieldHandler {
    @Override
    public Set<Integer> parse(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        for (int i = min; i <= max; i++) {
            values.add(i);
        }
        return values;
    }
}

