package kz.epam.javalab22.xml_parsing.entity;

public class Dressing extends Ingredient {
    private boolean lenten = false;

    public Dressing(String name, int caloricValue, int weight, String units, boolean lenten) {
        super(name, caloricValue, weight, units);
        this.lenten = lenten;
    }

    public boolean isLenten() {
        return lenten;
    }

    @Override
    public String toString() {
        return super.toString() + " Постный: " + (isLenten() ? "да" :"нет")  +"\n";
    }
}
