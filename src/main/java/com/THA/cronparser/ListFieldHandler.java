package main.java.com.THA.cronparser;

import java.util.*;

public class ListFieldHandler extends AbstractFieldHandler {
    @Override
    public Set<Integer> parse(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] parts = expression.split(",");
        for (String part : parts) {
            values.addAll(CronFieldHandler.handleField(part, min, max));
        }
        return values;
    }
}

