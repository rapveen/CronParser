package com.THA.cronparser.fieldhandler;

import java.util.*;

public class RangeFieldHandler extends AbstractFieldHandler {
    @Override
    public Set<Integer> parse(String expression, int min, int max) {
        return parseRange(expression, min, max);
    }
}

