package org.arachnis.numess;

/**
 * Search Menu helper class. [NOT IN USE]
 */

public class Search_MenuClass {
    private String item_name;
    private String day;
    private String meal_type;

    // Empty constructor
    public Search_MenuClass() {

    }

    public Search_MenuClass(String item_name,String day,String meal_type) {
        this.day=day;
        this.meal_type=meal_type;
        this.item_name=item_name;
    }


    // Getter Methods
    public String getItem_name() {
        return this.item_name;
    }

    public String getMeal_type() {
        return this.meal_type;
    }

    public String getDay() {
        return this.day;
    }


    // Setter Methods
    public void setItem_name(String name) {
        this.item_name=name;
    }

    public void setDay(String d) {
        this.day=d;
    }

    public void setMeal_type(String mt) {
        this.meal_type=mt;
    }
}
