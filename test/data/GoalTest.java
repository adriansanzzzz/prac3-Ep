package data;

import exceptions.WrongGoalTypeException;
import data.interfaces.GoalTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest implements GoalTestInterface {
    Goal goal;
    @BeforeEach
    public void setUp() throws NullPointerException, WrongGoalTypeException {
        String name = "WORKWITHMINORS";
        String description = "aims to promote inclusive and sustainable economic growth, full and productive employment and decent work for all.";
        String priority = "3";
        goal = new Goal(name, description, priority);

    }
    @Test
    @Override
    public void getGoalTest() {
        String name = "WORKWITHMINORS";
        String description = "aims to promote inclusive and sustainable economic growth, full and productive employment and decent work for all.";
        assertEquals(name, goal.getType());
        var prio = Integer.parseInt("3");
        assertEquals(description, goal.getDescription());
        assertEquals(prio, goal.getPriority());

    }

}
