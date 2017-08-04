package kz.epam.javalab22.xml_parsing.entity;

public class Seasoning extends Ingredient {
    private int heatLevel;

    public Seasoning(String name, int caloricValue, int weight, String units, int heatLevel) {
        super(name, caloricValue, weight, units);
        this.heatLevel = heatLevel;
    }

    public int getHeatLevel() {
        return heatLevel;
    }

    @Override
    public String toString() {
        return super.toString() + " Острота: " + getHeatLevel()  +"\n";
    }
}
