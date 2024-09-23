package com.THA.cronparser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CronFieldHandler {
    private static final Map<String, Integer> dayMap = new HashMap<>();
    private static final Map<String, FieldHandler> parserMap = new HashMap<>();

    static {
        dayMap.put("SUN", 1);
        dayMap.put("MON", 2);
        dayMap.put("TUE", 3);
        dayMap.put("WED", 4);
        dayMap.put("THU", 5);
        dayMap.put("FRI", 6);
        dayMap.put("SAT", 7);

        parserMap.put("*", new WildcardFieldHandler());
        parserMap.put(",", new ListFieldHandler());
        parserMap.put("-", new RangeFieldHandler());
        parserMap.put("/", new StepFieldHandler());
    }

    public static Set<Integer> handleField(String expression, int min, int max) {
        expression = parseDayName(expression);

        if (expression.contains("/")) {
            return parserMap.get("/").parse(expression, min, max);
        }

        for (Map.Entry<String, FieldHandler> entry : parserMap.entrySet()) {
            if (expression.contains(entry.getKey())) {
                return entry.getValue().parse(expression, min, max);
            }
        }

        return new SingleValueFieldHandler().parse(expression, min, max);
    }

    private static String parseDayName(String expression) {
        expression = expression.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); ) {
            char ch = expression.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                String s = expression.substring(i, Math.min(i + 3, expression.length()));
                Integer index = dayMap.get(s);
                if (index != null) {
                    sb.append(index);
                    i += 3;
                } else {
                    sb.append(ch);
                    i++;
                }
            } else {
                sb.append(ch);
                i++;
            }
        }
        return sb.toString();
    }
}

