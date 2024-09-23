package com.THA.cronparser.fieldhandler;

import java.util.*;


public interface FieldHandler {
    Set<Integer> parse(String expression, int min, int max);
}

