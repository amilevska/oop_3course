public class Beer {
    private String name;
    private String type;
    private Boolean al;
    private String manufacturer;
    private Chars chars;
    private Integer water = 0;
    private int malt = 0;
    private int hop = 0;
    private int yeast = 0;
    private int sugar = 0;
    private int flour = 0;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Boolean getAl() {
        return al;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Chars getChars() {
        return chars;
    }

    public int getHop() {
        return hop;
    }

    public int getMalt() {
        return malt;
    }

    public Integer getWater() {
        return water;
    }

    public int getSugar() {
        return sugar;
    }

    public int getYeast() {
        return yeast;
    }

    public int getFlour() {
        return flour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAl(Boolean al) {
        this.al = al;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHop(int hop) {
        if ((this.type == "light" || this.type == "camp" || this.type == "alive") && hop >= 20 && hop <= 50)
            this.hop = hop;
        else if (this.type == "dark" && hop >= 10 && hop <= 20)
            this.hop = hop;
    }

    public void setMalt(int malt) {
        if ((this.type == "light" || this.type == "alive") && malt >= 250 && malt <= 500)
            this.malt = malt;
        else if (this.type == "dark" && malt >= 100 && malt <= 200)
            this.malt = malt;
        else if (this.type == "camp" && malt >= 100 && malt <= 500)
            this.malt = malt;
    }

    public void setSugar(int sugar) {
        if ((this.type == "light" || this.type == "camp") && sugar >= 7 && sugar <= 10)
            this.sugar = sugar;
    }

    public void setWater(Integer water) {
        if (water >= 1 && water <= 2)
            this.water = water;
    }

    public void setYeast(int yeast) {
        if ((this.type == "light" || this.type == "camp" || this.type == "alive") && yeast >= 7 && yeast <= 10)
            this.yeast = yeast;
        else if (this.type == "dark" && yeast >= 5 && yeast <= 10)
            this.yeast = yeast;
    }

    public void setFlour(int flour) {
        if (this.type == "dark" && flour >= 50 && flour <= 150)
            this.flour = flour;
    }

    @Override
    public String toString() {
        String ing = "";
        ing += "water - " + water;
        if (malt > 0)
            ing += " malt - " + malt;
        if (hop > 0)
            ing += " hop - " + hop;
        if (flour > 0)
            ing += " flour - " + flour;
        if (sugar > 0)
            ing += " sugar - " + sugar;
        return "name:" + name + " type:" + type + " al:" + al + " manufacturer: " + manufacturer + " ingredients: " + ing +
                " revolNumber: " + chars.getRevolNumber() + " transparency: " + chars.getTransparency() +
                " filtered: " + chars.getFiltered() + " nutritionalValue: " + chars.getNutritionalValue() +
                " spillMethod: " + chars.getSpillMethodCapacity() + chars.getSpillMethodMaterial();
    }
}
