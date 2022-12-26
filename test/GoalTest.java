import data.Goal;
import exceptions.WrongGoalTypeException;
import interfaces.GoalTestInterface;
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
        String status = "In progress";
        String category = "Education";
        String subcategory = "Education integration";
        goal = new Goal(name, description, priority, status, category, subcategory);

    }
    @Test
    @Override
    public void getGoalTest() {
        String name = "WORKWITHMINORS";
        String description = "aims to promote inclusive and sustainable economic growth, full and productive employment and decent work for all.";
        String priority = "3";
        String status = "In progress";
        String category = "Education";
        String subcategory = "Education integration";
        assertEquals(name, goal.getType());
        assertEquals(description, goal.getDescription());
        assertEquals(priority, goal.getPriority());
        assertEquals(status, goal.getStatus());
        assertEquals(category, goal.getCategory());
        assertEquals(subcategory, goal.getSubcategory());
    }

}
