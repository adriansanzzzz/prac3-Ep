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
                () -> new Goal(null,null,null)); //si pasamos path NULL,  nos da un NullPointerException

    }
    @Test
    default void getEmptyGoalTest() {
        assertThrows(NullPointerException.class,
                () -> new Goal("","","")); //si pasamos path vacio,  nos da un NullPointerException

    }
    @Test
    default void wrongtypeGoalTest(){
        assertThrows(WrongGoalTypeException.class,
                () -> new Goal("WRONGTYPE","test","3"));
    }
    @Test
    //wrong priority not in range
    default void wrongPriorityGoalTest(){
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS","test","20"));
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS","test","0"));
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS","test","-5"));
    }
    }


