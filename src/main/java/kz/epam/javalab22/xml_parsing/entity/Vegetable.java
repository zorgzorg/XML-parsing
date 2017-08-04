package kz.epam.javalab22.xml_parsing.entity;

public class Vegetable extends Ingredient {
    private boolean preliminaryProcessing = false;

    public Vegetable(String name, int caloricValue, int weight, String units, Boolean preliminaryProcessing) {
        super(name, caloricValue, weight, units);
        this.preliminaryProcessing = preliminaryProcessing;
    }


    public Boolean isPreliminaryProcessing() {
        return preliminaryProcessing;
    }

    @Override
    public String toString() {
        return super.toString() + " Предварительная обработка: " + (isPreliminaryProcessing() ? "требуется":"не требуется")  +"\n";
    }
}
