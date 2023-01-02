package data;

import data.exceptions.WrongGoalTypeException;

public class Goal {
    private String type;
    private String description;
    private int priority;


    public Goal(String name, String description, String priority) throws WrongGoalTypeException {
        checkGoal(name, description, priority);
        this.type = name;
        this.description = description;
        this.priority = Integer.parseInt(priority);
    }
    public Goal() {
        this.type = "";
        this.description = "";
        this.priority = 0;
    }

    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public int getPriority() {
        return priority;
    }


    public void setType(String type) {
        this.type = type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void checkGoal(String name, String description, String priority) throws WrongGoalTypeException {
        if (name == null || name.equals("")){throw new NullPointerException("El nombre de la meta es NULL ");}
        else{
            enum goalTypes {WORKWITHMINORS, GAMESECTOR, PUBLICWORKERS, PUBLICADMINCONSORTIUM}
            if (!name.equals(goalTypes.WORKWITHMINORS.toString()) && !name.equals(goalTypes.GAMESECTOR.toString()) && !name.equals(goalTypes.PUBLICWORKERS.toString()) && !name.equals(goalTypes.PUBLICADMINCONSORTIUM.toString())) {
                throw new WrongGoalTypeException("El tipo de meta no es correcto");
            }
        }
        if (description == null || description.equals("")) throw new NullPointerException("La descripción de la meta es NULL ");
        if (priority == null || priority.equals("")) {
            throw new NullPointerException("La prioridad de la meta es NULL ");
        }else {
            if (Integer.parseInt(priority) < 1 || Integer.parseInt(priority) > 5) throw new IllegalArgumentException("La prioridad no es válida");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return type.equals(goal.type) &&
                description.equals(goal.description) &&
                priority==(goal.priority);
    }

    @Override
    public int hashCode () { return type.hashCode(); }
    @Override
    public String toString() {
        return "Goal{" + "name=" + type + ", description=" + description + ", priority=" + priority + '}';
    }


}
