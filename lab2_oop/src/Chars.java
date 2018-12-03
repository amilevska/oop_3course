public class Chars {
    private int revolNumber;
    private int transparency;
    private Boolean filtered;
    private int nutritionalValue;
    private String spillMethodMaterial;
    private int spillMethodCapacity;
    private String beerType;

    public Chars(String type){
        this.beerType = type;
    }

    public int getRevolNumber() {
        return revolNumber;
    }

    public int getTransparency() {
        return transparency;
    }

    public Boolean getFiltered() {
        return filtered;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public int getSpillMethodCapacity() {
        return spillMethodCapacity;
    }

    public String getSpillMethodMaterial() {
        return spillMethodMaterial;
    }

    public void setRevolNumber(int revolNumber) {
        if (this.beerType == "light" && revolNumber >= 10 && revolNumber <= 15)
            this.revolNumber = revolNumber;
        else if (this.beerType == "dark" && revolNumber >= 10 && revolNumber <= 20)
            this.revolNumber = revolNumber;
        else if (this.beerType == "alive" && revolNumber >= 4 && revolNumber <= 15)
            this.revolNumber = revolNumber;
        else if (this.beerType == "camp" && revolNumber >= 5 && revolNumber <= 15)
            this.revolNumber = revolNumber;
    }

    public void setTransparency(int transparency) {
        if (transparency >= 5 && transparency <= 20)
            this.transparency = transparency;
    }

    public void setFiltered(Boolean filtered) {
        this.filtered = filtered;
    }

    public void setNutritionalValue(int nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public void setSpillMethodMaterial(String spillMethodMaterial) {
        if (spillMethodMaterial == "method1" || spillMethodMaterial == "method2" || spillMethodMaterial == "method3")
            this.spillMethodMaterial = spillMethodMaterial;
    }

    public void setSpillMethodCapacity(int spillMethodCapacity) {
        this.spillMethodCapacity = spillMethodCapacity;
    }
}
