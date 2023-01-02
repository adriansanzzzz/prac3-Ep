package data.interfaces;
import data.Goal;
import data.exceptions.WrongGoalTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public interface GoalTestInterface {
    @Test
    void getGoalTest();

    @Test
    default void getNullPointerGoalTest() {
        assertThrows(NullPointerException.class,
                () -> new Goal(null,null,null));

    }
    @Test
    default void getEmptyGoalTest() {
        assertThrows(NullPointerException.class,
                () -> new Goal("","",""));

    }
    @Test
    default void wrongtypeGoalTest(){
        assertThrows(WrongGoalTypeException.class,
                () -> new Goal("WRONGTYPE", "citizenmanagementplatform","3"));
    }
    @Test
    //wrong priority not in range
    default void wrongPriorityGoalTest(){
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS", "citizenmanagementplatform","20"));
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS", "citizenmanagementplatform","0"));
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS", "citizenmanagementplatform","-5"));
    }
    }


