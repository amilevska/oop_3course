import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;


public class BeerStaxParser {
    public static void main(String[] args) throws XMLStreamException {
        List<Beer> beerList = null;
        Beer currentBeer = null;
        Chars ch = null;
        ArrayList<String> ingredients = new ArrayList<>();
        String content = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        StreamSource source = new StreamSource("Beer.xml");
        XMLStreamReader reader = factory.createXMLStreamReader(source);

        while(reader.hasNext()){
            int event = reader.next();
            switch(event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("beer".equals(reader.getLocalName())) {
                        currentBeer = new Beer();
                    }
                    if("beers".equals(reader.getLocalName())) {
                        beerList = new ArrayList<>();
                    }
                    if("chars".equals(reader.getLocalName())) {
                        ch = new Chars(currentBeer.getType());
                    }
                    if("light".equals(reader.getLocalName())) {
                        currentBeer.setType("light");
                    }
                    if("dark".equals(reader.getLocalName())) {
                        currentBeer.setType("dark");
                    }
                    if("camp".equals(reader.getLocalName())) {
                        currentBeer.setType("camp");
                    }
                    if("alive".equals(reader.getLocalName())) {
                        currentBeer.setType("alive");
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    content = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch(reader.getLocalName()){
                        case "beer":
                            beerList.add(currentBeer);
                            break;
                        case "chars":
                            currentBeer.setChars(ch);
                            break;
                        case "name":
                            currentBeer.setName(content);
                            break;
                        case "light":
                            currentBeer.setType("light");
                            break;
                        case "dark":
                            currentBeer.setType("dark");
                            break;
                        case "camp":
                            currentBeer.setType("camp");
                            break;
                        case "alive":
                            currentBeer.setType("alive");
                            break;
                        case "water":
                            currentBeer.setWater(Integer.parseInt(content));
                            break;
                        case "hop":
                            currentBeer.setHop(Integer.parseInt(content));
                            break;
                        case "malt":
                            currentBeer.setMalt(Integer.parseInt(content));
                            break;
                        case "yeast":
                            currentBeer.setYeast(Integer.parseInt(content));
                            break;
                        case "sugar":
                            currentBeer.setSugar(Integer.parseInt(content));
                            break;
                        case "flour":
                            currentBeer.setFlour(Integer.parseInt(content));
                            break;
                        case "al":
                            currentBeer.setAl(Boolean.parseBoolean(content));
                            break;
                        case "manufacturer":
                            currentBeer.setManufacturer(content);
                            break;
                        case "revolNumber":
                            ch.setRevolNumber(Integer.parseInt(content));
                            break;
                        case "transparency":
                            ch.setTransparency(Integer.parseInt(content));
                            break;
                        case "filtered":
                            ch.setFiltered(Boolean.parseBoolean(content));
                            break;
                        case "nutritionalValue":
                            ch.setNutritionalValue(Integer.parseInt(content));
                            break;
                        case "capacity":
                            ch.setSpillMethodCapacity(Integer.parseInt(content));
                            break;
                        case "material":
                            ch.setSpillMethodMaterial(content);
                            break;
                    }
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    beerList = new ArrayList<>();
                    break;
            }

        }

        //Print
        for ( Beer b : beerList){
            System.out.println(b);
        }

    }
}
