package data.interfaces;
import data.Goal;
import exceptions.WrongGoalTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public interface GoalTestInterface {
    @Test
    void getGoalTest();

    @Test
    default void getNullPointerGoalTest() {
        assertThrows(NullPointerException.class,
                () -> new Goal(null,null,null,null,null,null)); //si pasamos path NULL,  nos da un NullPointerException

    }
    @Test
    default void getEmptyGoalTest() {
        assertThrows(NullPointerException.class,
                () -> new Goal("","","","","",""));

    }
    @Test
    default void wrongtypeGoalTest(){
        assertThrows(WrongGoalTypeException.class,
                () -> new Goal("WRONGTYPE","test","3","in progress","Education","Education integration"));
    }
    @Test
    //wrong priority not in range
    default void wrongPriorityGoalTest(){
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS","test","20","in progress","Education","Education integration"));
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS","test","0","in progress","Education","Education integration"));
        assertThrows(IllegalArgumentException.class,
                () -> new Goal("WORKWITHMINORS","test","-5","in progress","Education","Education integration"));
    }
    }


