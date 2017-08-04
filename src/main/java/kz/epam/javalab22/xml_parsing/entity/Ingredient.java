package kz.epam.javalab22.xml_parsing.entity;

public abstract class Ingredient {
    private String name;
    private int caloricValue;
    private int weight;
    private String units;

    public Ingredient() {
    }

    public Ingredient(String name, int caloricValue) {
        this.name = name;
        this.caloricValue = caloricValue;
    }

    public Ingredient(String name, int caloricValue, int weight, String units) {
        this.name = name;
        this.caloricValue = caloricValue;
        this.weight = weight;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public Integer getCaloricValue() {
        return caloricValue;
    }

    public int getWeight() {
        return weight;
    }

    public String getUnits() {
        return units;
    }

    public String toString() {
        return getName() + " - " + getWeight() + " " + getUnits() + " Калорийность в 100 г: " + getCaloricValue() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (caloricValue != that.caloricValue) return false;
        if (weight != that.weight) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return units != null ? units.equals(that.units) : that.units == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + caloricValue;
        result = 31 * result + weight;
        result = 31 * result + (units != null ? units.hashCode() : 0);
        return result;
    }
}
