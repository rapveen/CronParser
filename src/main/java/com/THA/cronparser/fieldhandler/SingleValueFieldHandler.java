package com.THA.cronparser.fieldhandler;

import java.util.*;

public class SingleValueFieldHandler extends AbstractFieldHandler {
    @Override
    public Set<Integer> parse(String expression, int min, int max) {
        return parseSingleValue(expression, min, max);
    }
}

