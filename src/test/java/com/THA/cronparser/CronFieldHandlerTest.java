package com.THA.cronparser;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CronFieldHandlerTest {

    @Test
    void testHandleWildcardField() {
        Set<Integer> result = CronFieldHandler.handleField("*", 0, 59);
        assertEquals(60, result.size());
        assertTrue(result.contains(0));
        assertTrue(result.contains(59));
    }

    @Test
    void testHandleStepField() {
        Set<Integer> result = CronFieldHandler.handleField("*/10", 0, 59);
        assertEquals(6, result.size());
        assertTrue(result.contains(0));
        assertTrue(result.contains(10));
        assertTrue(result.contains(20));
        assertTrue(result.contains(30));
        assertTrue(result.contains(40));
        assertTrue(result.contains(50));
    }

    @Test
    void testHandleRangeField() {
        Set<Integer> result = CronFieldHandler.handleField("10-20", 0, 59);
        assertEquals(11, result.size());
        assertTrue(result.contains(10));
        assertTrue(result.contains(15));
        assertTrue(result.contains(20));
    }

    @Test
    void testHandleListField() {
        Set<Integer> result = CronFieldHandler.handleField("5,10,15", 0, 59);
        assertEquals(3, result.size());
        assertTrue(result.contains(5));
        assertTrue(result.contains(10));
        assertTrue(result.contains(15));
    }

    @Test
    void testHandleInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            CronFieldHandler.handleField("30-10", 0, 59);
        });
    }

    @Test
    void testHandleInvalidStep() {
        assertThrows(IllegalArgumentException.class, () -> {
            CronFieldHandler.handleField("*/100", 0, 59);
        });
    }

    @Test
    void testHandleDayNames() {
        Set<Integer> result = CronFieldHandler.handleField("MON-FRI", 1, 7);
        assertEquals(5, result.size());
        assertTrue(result.contains(2));  // Monday
        assertTrue(result.contains(3));  // Tuesday
        assertTrue(result.contains(4));  // Wednesday
        assertTrue(result.contains(5));  // Thursday
        assertTrue(result.contains(6));  // Friday
    }

    @Test
    void testHandleMixedDayNamesAndNumbers() {
        Set<Integer> result = CronFieldHandler.handleField("MON,3,FRI", 1, 7);
        assertEquals(3, result.size());
        assertTrue(result.contains(2));  // Monday
        assertTrue(result.contains(3));  // Wednesday (as number)
        assertTrue(result.contains(6));  // Friday
    }

    @Test
    void testHandleInvalidDayName() {
        assertThrows(IllegalArgumentException.class, () -> {
            CronFieldHandler.handleField("MON,TUE,INVALID", 1, 7);
        });
    }

    @Test
    void testHandleStepWithWildcard() {
        Set<Integer> result = CronFieldHandler.handleField("*/15", 0, 59);
        assertEquals(4, result.size());
        assertTrue(result.contains(0));
        assertTrue(result.contains(15));
        assertTrue(result.contains(30));
        assertTrue(result.contains(45));
    }
}

