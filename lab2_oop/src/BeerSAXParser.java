import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BeerSAXParser {
    public static void main(String[] args) throws Exception {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse("Beer.xml", handler);

        //Printing
        handler.beerList.sort(new Sorting());
        for ( Beer b : handler.beerList){
            System.out.println(b);
        }
    }
}

class SAXHandler extends DefaultHandler {
    ArrayList<Beer> beerList = new ArrayList<>();
    Beer b = null;
    Chars ch = null;
    ArrayList<String> ingredients;
    String content = null;
    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        switch(qName){
            //Create a new object or list when the start tag is found
            case "beer":
                b = new Beer();
                break;
            case "chars":
                ch = new Chars(b.getType());
                break;
            case "light":
                b.setType("light");
                break;
            case "dark":
                b.setType("dark");
                break;
            case "camp":
                b.setType("camp");
                break;
            case "alive":
                b.setType("alive");
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
            //Add the beer to list once end tag is found
            case "beer":
                beerList.add(b);
                break;
            case "chars":
                b.setChars(ch);
                break;
            //For all other end tags the beer has to be updated.
            case "name":
                b.setName(content);
                break;
            case "water":
                b.setWater(Integer.parseInt(content));
                break;
            case "hop":
                b.setHop(Integer.parseInt(content));
                break;
            case "malt":
                b.setMalt(Integer.parseInt(content));
                break;
            case "yeast":
                b.setYeast(Integer.parseInt(content));
                break;
            case "sugar":
                b.setSugar(Integer.parseInt(content));
                break;
            case "flour":
                b.setFlour(Integer.parseInt(content));
                break;
            case "al":
                b.setAl(Boolean.parseBoolean(content));
                break;
            case "manufacturer":
                b.setManufacturer(content);
                break;
            case "ingridient":
                ingredients.add(content);
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
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

}
