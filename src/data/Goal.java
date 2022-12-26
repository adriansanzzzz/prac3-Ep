package data;

import exceptions.WrongGoalTypeException;

public class Goal {
private String type;
    private String description;
    private String priority;
    private String status;
    private String category;
    private String subcategory;


    public Goal(String name, String description, String priority, String status, String category, String subcategory) throws WrongGoalTypeException {
        checkGoal(name, description, priority, status, category, subcategory);
        this.type = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.subcategory = subcategory;

    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void checkGoal(String name, String description, String priority, String status, String category, String subcategory) throws WrongGoalTypeException {
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
        if (status == null || status.equals("")) throw new NullPointerException("El estado de la meta es NULL ");
        if (category == null || category.equals("")) throw new NullPointerException("La categoría de la meta es NULL ");
        if (subcategory == null || subcategory.equals("")) throw new NullPointerException("La subcategoría de la meta es NULL ");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return type.equals(goal.type) &&
                description.equals(goal.description) &&
                priority.equals(goal.priority) &&
                status.equals(goal.status) &&
                category.equals(goal.category) &&
                subcategory.equals(goal.subcategory);
    }

    @Override
    public int hashCode () { return type.hashCode(); }
    @Override
    public String toString() {
        return "Goal{" + "name=" + type + ", description=" + description + ", priority=" + priority + ", status=" + status + ", category=" + category + ", subcategory=" + subcategory + '}';
    }


}
