package main.java.com.THA.cronparser;

import java.util.*;


public interface FieldHandler {
    Set<Integer> parse(String expression, int min, int max);
}

