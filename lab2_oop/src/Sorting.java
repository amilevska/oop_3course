import java.util.Comparator;

public class Sorting implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Beer b1 = (Beer) o1;
        Beer b2 = (Beer) o2;
        return  b1.getWater().compareTo(b2.getWater());
    }
}
